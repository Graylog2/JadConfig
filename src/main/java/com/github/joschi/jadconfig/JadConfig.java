package com.github.joschi.jadconfig;

import com.github.joschi.jadconfig.converters.NoConverter;
import com.github.joschi.jadconfig.converters.StringConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * The main class for JadConfig. It's responsible for parsing the configuration bean(s) that contain(s) the annotated
 * fields, use a {@link Repository} to read the raw configuration and assign the fields with the correct values.
 * <p/>
 * The configuration bean(s) you pass in the constructor are expected to have one or more {@link Parameter} annotations
 * on them.
 * <p/>
 * You can pass either a single configuration bean or an array of objects. In the case of an array JadConfig will
 * collect the {@link Parameter} annotations from all the objects passed in.
 *
 * @author jschalanda
 */
public class JadConfig {

    private static final Logger LOG = LoggerFactory.getLogger(JadConfig.class);

    private LinkedList<ConverterFactory> converterFactories;
    private List<Object> configurationBeans;

    private Repository repository;

    /**
     * Creates a new (empty) instance of JadConfig.
     * <p/>
     * Configuration beans will have to be added with {@link #addConfigurationBean(Object)} and a {@link Repository}
     * will have to be set with {@link #setRepository(Repository)}.
     *
     * @see #addConfigurationBean(Object)
     */
    public JadConfig() {
        configurationBeans = new ArrayList<Object>();

        converterFactories = new LinkedList<ConverterFactory>();
        converterFactories.add(new DefaultConverterFactory());
    }

    /**
     * Creates a new instance of JadConfig backed by the provided {@link Repository} and filling the provided
     * {@literal configurationBeans}.
     *
     * @param repository         A {@link Repository} for interfacing with the configuration data
     * @param configurationBeans One or more objects annotated with JadConfig annotations
     */
    public JadConfig(Repository repository, Object... configurationBeans) {
        this();

        this.configurationBeans.addAll(Arrays.asList(configurationBeans));
        this.repository = repository;
    }

    /**
     * Processes the configuration provided by the configured {@link Repository} and filling the provided configuration
     * beans.
     *
     * @throws RepositoryException If an error occurred while reading from the configured {@link Repository}
     * @throws ValidationException If any parameter couldn't be successfully validated
     */
    public void process() throws RepositoryException, ValidationException {

        LOG.info("Opening repository {}", repository);
        repository.open();

        for (Object configurationBean : configurationBeans) {
            LOG.info("Processing configuration bean {}", configurationBean);

            processClassFields(configurationBean, configurationBean.getClass().getDeclaredFields());
            invokeValidatorMethods(configurationBean, configurationBean.getClass().getMethods());
        }
    }

    private void processClassFields(Object configurationBean, Field[] fields) throws ValidationException {
        for (Field field : fields) {
            Parameter parameter = field.getAnnotation(Parameter.class);

            if (parameter != null) {
                LOG.debug("Processing field {}", parameter);

                Object fieldValue = getFieldValue(field, configurationBean);

                String parameterName = parameter.value();
                String parameterValue = repository.read(parameterName);

                if (parameterValue == null && fieldValue == null && parameter.required()) {
                    LOG.warn("Required parameter {} not found", parameterName);
                    throw new ParameterException("Required parameter \"" + parameterName + "\" not found.");
                }

                if (parameterValue != null) {
                    LOG.debug("Validating parameter {}", parameterName);
                    validateParameter(parameter.validator(), parameterName, parameterValue);

                    LOG.debug("Converting parameter value {}: ", parameterName, parameterValue);
                    fieldValue = convertStringValue(field.getType(), parameter.converter(), parameterValue);
                }

                LOG.debug("Setting parameter {} to {}", parameterName, parameterValue);

                try {
                    field.set(configurationBean, fieldValue);
                } catch (Exception e) {
                    throw new ParameterException("Couldn't set field " + field.getName(), e);
                }
            }
        }
    }

    private Object convertStringValue(Class<?> fieldType, Class<? extends Converter<?>> converterClass, String stringValue) {
        Converter converter = getConverter(fieldType, converterClass);

        LOG.debug("Loaded converter class for type {}: {}", fieldType, converter);

        return converter.convertFrom(stringValue);
    }

    private Object getFieldValue(Field field, Object bean) {
        field.setAccessible(true);

        try {
            return field.get(bean);
        } catch (IllegalAccessException e) {
            throw new ParameterException("Couldn't obtain value of field " + field.getName(), e);
        }
    }

    private Converter getConverter(Class<?> fieldType, Class<? extends Converter<?>> converterClass) {

        LOG.debug("Trying to find converter class {} for type {}", converterClass, fieldType);

        Class<? extends Converter<?>> clazz = converterClass;

        if (clazz == null || clazz == NoConverter.class) {
            clazz = findConverter(fieldType);
        }

        // Fallback to StringConverter
        if (clazz == null) {
            clazz = StringConverter.class;

            LOG.debug("Using fallback converter: {}", clazz);
        }

        try {
            return clazz.newInstance();
        } catch (Exception e) {
            throw new ParameterException("Couldn't initialize converter class " + clazz.getCanonicalName(), e);
        }
    }

    private void validateParameter(Class<? extends Validator> validatorClass, String name, String value) throws ValidationException {
        Validator validator;

        LOG.debug("Validating parameter {} with value {}", name, value);

        try {
            validator = validatorClass.newInstance();
        } catch (Exception e) {
            throw new ParameterException("Couldn't initialize validator " + validatorClass.getCanonicalName(), e);
        }

        validator.validate(name, value);
    }

    private void invokeValidatorMethods(Object configurationBean, Method[] methods) throws ValidationException {
        for (Method method : methods) {
            if (method.isAnnotationPresent(ValidatorMethod.class)) {
                LOG.debug("Invoking validator method {} in {}", method, configurationBean);

                try {
                    method.invoke(configurationBean);
                } catch (Exception e) {
                    throw new ValidationException("Couldn't run validator method " + method.getName(), e);
                }
            }
        }
    }

    private <T> Class<? extends Converter<T>> findConverter(Class<T> clazz) {
        for (ConverterFactory factory : converterFactories) {
            Class<? extends Converter<T>> result = factory.getConverter(clazz);

            if (result != null) {
                return result;
            }
        }

        return null;
    }

    /**
     * Adds a {@link ConverterFactory} for processing additional types
     *
     * @param converterFactory The {@link ConverterFactory} to be added
     */
    public void addConverterFactory(ConverterFactory converterFactory) {
        converterFactories.addFirst(converterFactory);

        LOG.info("Added converter factory {}", converterFactory);
    }

    /**
     * Adds a configuration bean annotated with JadConfig annotations.
     *
     * @param configurationBean An object annotated with JadConfig annotations
     */
    public void addConfigurationBean(Object configurationBean) {
        configurationBeans.add(configurationBean);

        LOG.info("Added configuration bean {}", configurationBean);
    }

    /**
     * Saves all configuration parameters to the configured {@link Repository}.
     *
     * @throws RepositoryException If an error occurred while writing to or saving the configured {@link Repository}
     */
    public void save() throws RepositoryException {
        LOG.info("Saving changed configuration parameters");

        for (Object configurationBean : configurationBeans) {
            LOG.debug("Checking declared fields of {}", configurationBean);

            for (Field field : configurationBean.getClass().getDeclaredFields()) {
                Parameter parameter = field.getAnnotation(Parameter.class);

                LOG.debug("Checking declared field {} of {}", parameter, configurationBean);

                if (parameter != null) {

                    Object fieldValue = getFieldValue(field, configurationBean);

                    LOG.debug("Retrieved field value {} = {}", field, fieldValue);

                    if (fieldValue != null) {
                        String stringValue = convertFieldValue(field.getType(), parameter.converter(), fieldValue);

                        LOG.debug("Writing {} = {} to repository", parameter.value(), stringValue);
                        repository.write(parameter.value(), stringValue);
                    }
                }
            }
        }

        LOG.debug("Saving changes to repository {}", repository);
        repository.save();
    }

    private String convertFieldValue(Class<?> fieldType, Class<? extends Converter<?>> converterClass, Object fieldValue) {
        Converter converter = getConverter(fieldType, converterClass);

        LOG.debug("Converting {} to type {} using converter {}", new Object[] { fieldValue, fieldType, converter });
        return converter.convertTo(fieldValue);
    }

    /**
     * Set the {@link Repository} to load configuration data from.
     *
     * @param repository A {@link Repository} instance
     */
    public void setRepository(Repository repository) {
        this.repository = repository;
    }
}
