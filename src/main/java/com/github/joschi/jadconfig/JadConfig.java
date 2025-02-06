package com.github.joschi.jadconfig;

import com.github.joschi.jadconfig.converters.NoConverter;
import com.github.joschi.jadconfig.converters.StringConverter;
import com.github.joschi.jadconfig.response.ProcessingOutcome;
import com.github.joschi.jadconfig.response.ProcessingResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;


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
    private final LinkedList<ConverterFactory> converterFactories = new LinkedList<ConverterFactory>();
    private List<Object> configurationBeans;
    private List<Repository> repositories;

    /**
     * Creates a new (empty) instance of JadConfig.
     * <p/>
     * Configuration beans will have to be added with {@link #addConfigurationBean(Object)} and a {@link Repository}
     * will have to be set with {@link #setRepository(Repository)} or {@link #setRepositories(Collection)}.
     *
     * @see #addConfigurationBean(Object)
     * @see #setRepository(Repository)
     * @see #setRepositories(Collection)
     */
    public JadConfig() {
        this(Collections.<Repository>emptyList());
    }

    /**
     * Creates a new instance of JadConfig backed by the provided {@link Repository} and filling the provided
     * {@literal configurationBeans}.
     *
     * @param repository         A {@link Repository} for interfacing with the configuration data
     * @param configurationBeans One or more objects annotated with JadConfig annotations
     */
    public JadConfig(Repository repository, Object... configurationBeans) {
        this(Collections.singletonList(repository), configurationBeans);
    }

    /**
     * Creates a new instance of JadConfig backed by the provided {@link Repository}s and filling the provided
     * {@literal configurationBeans}.
     *
     * @param repositories       A collection of {@link Repository}s for interfacing with the configuration data
     * @param configurationBeans One or more objects annotated with JadConfig annotations
     */
    public JadConfig(Collection<Repository> repositories, Object... configurationBeans) {
        this.configurationBeans = new ArrayList<Object>(Arrays.asList(configurationBeans));
        this.repositories = new ArrayList<Repository>(repositories);

        converterFactories.add(new DefaultConverterFactory());
    }


    /**
     * Processes the configuration provided by the configured {@link Repository} and filling the provided configuration
     * beans.
     *
     * Stops processing on first encountered exception.
     *
     * @throws RepositoryException If an error occurred while reading from the configured {@link Repository}
     * @throws ValidationException If any parameter couldn't be successfully validated
     */
    public void process() throws RepositoryException, ValidationException {

        for (Repository repository : repositories) {
            LOG.debug("Opening repository {}", repository);
            repository.open();
        }

        for (Object configurationBean : configurationBeans) {
            LOG.debug("Processing configuration bean {}", configurationBean);

            processClassFields(configurationBean, ReflectionUtils.getAllFields(configurationBean.getClass()));
            invokeValidatorMethods(configurationBean, ReflectionUtils.getAllMethods(configurationBean.getClass()));
        }
    }

    /**
     * Processes the configuration provided by the configured {@link Repository} and filling the provided configuration
     * beans.
     * <p>
     * Instead of stopping processing on first encountered exception, tries to collect all validation problems and in
     * case of any problems aggregate them all into single exception, listing all the field and validation issues.
     */
    public void processFailingLazily() throws RepositoryException, LazyValidationException {
        final ProcessingResponse result = doProcessFailingLazily();
        if (!result.isSuccess()) {
            throw new LazyValidationException(result);
        }
    }

    ProcessingResponse doProcessFailingLazily() throws RepositoryException {
        for (Repository repository : repositories) {
            LOG.debug("Opening repository {}", repository);
            repository.open();
        }

        return configurationBeans.stream()
                .peek(bean -> LOG.debug("Processing configuration bean {}", bean))
                .map(this::processBean)
                .collect(Collectors.collectingAndThen(Collectors.toList(), ProcessingResponse::new));
    }

    private ProcessingOutcome processBean(Object bean) {
        final Map<String, Exception> fieldProcessingProblems = processClassFieldsFailingLazily(bean, ReflectionUtils.getAllFields(bean.getClass()));
        final Map<String, Exception> validationMethodsProblems = invokeValidatorMethodsFailingLazily(bean, ReflectionUtils.getAllMethods(bean.getClass()));
        return new ProcessingOutcome(bean, fieldProcessingProblems, validationMethodsProblems);
    }


    private void processClassFields(Object configurationBean, Field[] fields) throws ValidationException {
        for (Field field : fields) {
            processClassField(configurationBean, field);
        }
    }

    private Map<String, Exception> processClassFieldsFailingLazily(Object configurationBean, Field[] fields) {
        final Map<String, Exception> fieldProcessingProblems = new HashMap<>();
        for (Field field : fields) {
            try {
                processClassField(configurationBean, field);
            } catch (Exception ex) {
                fieldProcessingProblems.put(field.getAnnotation(Parameter.class).value(), ex);
            }
        }
        return fieldProcessingProblems;
    }

    private void processClassField(Object configurationBean, Field field) throws ValidationException {
        Parameter parameter = field.getAnnotation(Parameter.class);

        if (parameter != null) {
            LOG.debug("Processing field {}", parameter);

            Object fieldValue = getFieldValue(field, configurationBean);

            String parameterName = parameter.value();
            String parameterValue = lookupParameter(parameterName)
                    .orElseGet(() -> lookupFallbackParameter(parameter));


            if (parameterValue == null && fieldValue == null && parameter.required()) {
                throw new ParameterException("Required parameter \"" + parameterName + "\" not found.");
            }

            if (parameterValue != null) {

                if (parameter.trim()) {
                    LOG.debug("Trimmed parameter value {}", parameterName);
                    parameterValue = Strings.trim(parameterValue);
                }

                LOG.debug("Converting parameter value {}", parameterName);
                try {
                    fieldValue = convertStringValue(field.getType(), parameter.converter(), parameterValue);
                } catch (ParameterException e) {
                    throw new ParameterException("Couldn't convert value for parameter \"" + parameterName + "\"", e);
                }

                LOG.debug("Validating parameter {}", parameterName);
                final List<Class<? extends Validator<?>>> validators =
                        new ArrayList<>(Collections.<Class<? extends Validator<?>>>singleton(parameter.validator()));
                validators.addAll(Arrays.asList(parameter.validators()));
                validateParameter(validators, parameterName, fieldValue);
            }

            LOG.debug("Setting parameter {} to {}", parameterName, fieldValue);

            try {
                field.set(configurationBean, fieldValue);
            } catch (Exception e) {
                throw new ParameterException("Couldn't set field " + field.getName(), e);
            }
        }

        processPrefixParameter(configurationBean, field);
    }

    private void processPrefixParameter(Object configurationBean, Field field) {
        AggregatedParameter parameter = field.getAnnotation(AggregatedParameter.class);
        if (parameter != null) {
            LOG.debug("Processing prefixed field {}", field);
            final Map<String, String> params = repositories.stream().flatMap(r -> collectPrefixedParams(r, parameter.prefix()).entrySet().stream()).collect(Collectors.toMap(entry -> stripPrefix(entry.getKey(), parameter), Map.Entry::getValue));
            try {
                field.setAccessible(true);
                field.set(configurationBean, params);
            } catch (IllegalAccessException e) {
                throw new ParameterException("Couldn't set field " + field.getName(), e);
            }
        }
    }

    private String stripPrefix(String key, AggregatedParameter parameter) {
        if (parameter.stripPrefix()) {
            return Arrays.stream(parameter.prefix())
                    .filter(key::startsWith)
                    .map(prefix -> key.replaceFirst(prefix, ""))
                    .findFirst()
                    .orElse(key);
        } else {
            return key;
        }
    }

    private Map<String, String> collectPrefixedParams(Repository repository, String[] prefixes) {
        final Set<String> names = Arrays.stream(prefixes).flatMap(prefix -> repository.readNames(prefix).stream()).collect(Collectors.toSet());
        return names.stream().collect(Collectors.toMap(Function.identity(), repository::read));
    }

    private String lookupFallbackParameter(Parameter parameter) {
        final Optional<String> fallbackValue = Optional.ofNullable(parameter.fallbackPropertyName())
                .filter(fallbackName -> !fallbackName.trim().isEmpty())
                .flatMap(this::lookupParameter);
        fallbackValue.ifPresent(value -> LOG.warn("Primary parameter {} not found, using the fallback value of {}. Please correct your configuration if possible.",
                parameter.value(), parameter.fallbackPropertyName()));
        return fallbackValue.orElse(null);
    }

    private Optional<String> lookupParameter(String parameterName) {
        return repositories.stream()
                .peek(repository -> LOG.debug("Looking up parameter {} in repository {}", parameterName, repository))
                .map(repository -> repository.read(parameterName))
                .filter(Objects::nonNull)
                .findFirst();
    }

    private Object convertStringValue(Class<?> fieldType, Class<? extends Converter<?>> converterClass, String stringValue) {
        final Converter converter = getConverter(fieldType, converterClass);

        LOG.debug("Loaded converter class for type {}: {}", fieldType, converter);

        return converter.convertFrom(stringValue);
    }

    private Object getFieldValue(Field field, Object bean) {
        try {
            return ReflectionUtils.getFieldValue(bean, field);
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

    @SuppressWarnings("unchecked")
    private void validateParameter(Collection<Class<? extends Validator<?>>> validatorClasses,
                                   String name, Object value) throws ValidationException {
        LOG.debug("Validating parameter {} with value {}", name, value);

        for(Class<? extends Validator<?>> validatorClass : validatorClasses) {
            Validator validator;
            try {
                validator = validatorClass.newInstance();
            } catch (Exception e) {
                throw new ParameterException("Couldn't initialize validator " + validatorClass.getCanonicalName(), e);
            }

            validator.validate(name, value);
        }
    }

    private void invokeValidatorMethods(Object configurationBean, Method[] methods) throws ValidationException {
        try {
            ReflectionUtils.invokeMethodsWithAnnotation(configurationBean, ValidatorMethod.class, methods);
        } catch (InvocationTargetException e) {
            if (e.getTargetException() instanceof ValidationException) {
                throw (ValidationException)e.getTargetException();
            }

            throw new ValidationException("Couldn't run validator method", e);
        } catch (Exception e) {
            throw new ValidationException("Couldn't run validator method", e);
        }
    }

    private Map<String, Exception> invokeValidatorMethodsFailingLazily(Object configurationBean, Method[] methods) {
        final Map<String, Exception> problems = new HashMap<>();

        for (Method method : methods) {
            if (method.isAnnotationPresent(ValidatorMethod.class)) {
                try {
                    method.invoke(configurationBean);
                } catch (InvocationTargetException invEx) {
                    if (invEx.getTargetException() instanceof ValidationException) {
                        problems.put(method.getName(), (ValidationException)invEx.getTargetException());
                    } else {
                        problems.put(method.getName(), invEx);
                    }
                } catch (Exception ex) {
                    problems.put(method.getName(), ex);
                }
            }
        }
        return problems;
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
     * @return the JadConfig instance
     */
    public JadConfig addConverterFactory(ConverterFactory converterFactory) {
        converterFactories.addFirst(converterFactory);

        LOG.info("Added converter factory {}", converterFactory);
        return this;
    }

    /**
     * Adds a configuration bean annotated with JadConfig annotations.
     *
     * @param configurationBean An object annotated with JadConfig annotations
     * @return the JadConfig instance
     */
    public JadConfig addConfigurationBean(Object configurationBean) {
        configurationBeans.add(configurationBean);

        LOG.info("Added configuration bean {}", configurationBean);
        return this;
    }

    /**
     * Dumps all configuration parameters as a {@link java.util.Map} of {@link String}.
     * <p>
     * If being called before {@link #process()} it will return the default values of the configuration beans.
     * </p>
     *
     * @return All configuration parameters as {@link String}
     */
    public Map<String, String> dump() {
        final Map<String, String> configurationDump = new HashMap<String, String>();

        for (Object configurationBean : configurationBeans) {
            for (Field field : ReflectionUtils.getAllFields(configurationBean.getClass())) {
                final Parameter parameter = field.getAnnotation(Parameter.class);

                if (parameter != null) {
                    final Object fieldValue = getFieldValue(field, configurationBean);
                    configurationDump.put(parameter.value(), convertFieldValue(field.getType(), parameter.converter(), fieldValue));
                }
            }
        }

        return configurationDump;
    }

    @SuppressWarnings("unchecked")
    private String convertFieldValue(Class<?> fieldType, Class<? extends Converter<?>> converterClass, Object fieldValue) {
        if (null != fieldValue) {
            final Converter converter = getConverter(fieldType, converterClass);

            LOG.debug("Converting {} to type {} using converter {}", fieldValue, fieldType, converter);
            return converter.convertTo(fieldValue);
        } else {
            return "null";
        }
    }

    /**
     * Set the {@link Repository} to load configuration data from.
     *
     * @param repository A {@link Repository} instance
     * @return the JadConfig instance
     */
    public JadConfig setRepository(final Repository repository) {
        if(repository == null) {
            throw new IllegalArgumentException("The repository must not be null.");
        }

        this.repositories = Collections.singletonList(repository);
        return this;
    }

    /**
     * Set the (sorted) list of {@link Repository}s to load configuration data from.
     *
     * @param repositories A collection of {@link Repository} instances
     * @return the JadConfig instance
     */
    public JadConfig setRepositories(final Collection<Repository> repositories) {
        if(repositories == null || repositories.isEmpty()) {
            throw new IllegalArgumentException("At least 1 repository is required.");
        }
        this.repositories = new ArrayList<Repository>(repositories);
        return this;
    }

    /**
     * Set the (sorted) list of {@link Repository}s to load configuration data from.
     *
     * @param repositories A collection of {@link Repository} instances
     * @return the JadConfig instance
     */
    public JadConfig setRepositories(Repository... repositories) {
        if(repositories == null || repositories.length < 1) {
            throw new IllegalArgumentException("At least 1 repository is required.");
        }

        this.repositories = new ArrayList<Repository>(Arrays.asList(repositories));
        return this;
    }

    /**
     * Get an unmodifiable list of registered converter factories.
     *
     * @return the list of registered converter factories
     */
    public List<ConverterFactory> getConverterFactories() {
        return Collections.unmodifiableList(converterFactories);
    }

    /**
     * Get an unmodifiable list of registered configuration beans.
     *
     * @return the list of registered configuration beans
     */
    public List<Object> getConfigurationBeans() {
        return Collections.unmodifiableList(configurationBeans);
    }

    /**
     * Get an unmodifiable list of registered repositories.
     *
     * @return the list of registered repositories
     */
    public List<Repository> getRepositories() {
        return Collections.unmodifiableList(repositories);
    }
}
