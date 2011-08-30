package de.schalanda.jadconfig;

import de.schalanda.jadconfig.converters.NoConverter;
import de.schalanda.jadconfig.converters.StringConverter;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jschalanda
 */
public class JadConfig {

    private static final List<ConverterFactory> CONVERTER_FACTORIES = new ArrayList<ConverterFactory>();

    static {
        CONVERTER_FACTORIES.add(new DefaultConverterFactory());
    }

    private Object configurationBean;
    private Repository repository;

    public void addConverterFactory(ConverterFactory converterFactory) {

        CONVERTER_FACTORIES.add(converterFactory);
    }

    public JadConfig(Object configurationBean, Repository repository) {

        this.configurationBean = configurationBean;
        this.repository = repository;
    }

    public void process() throws ParameterException, RepositoryException, ValidationException {

        repository.open();

        processClassFields(configurationBean.getClass().getDeclaredFields());
        invokeValidatorMethods(configurationBean.getClass().getMethods());
    }

    private void processClassFields(Field[] fields) throws ValidationException {
        for (Field field : fields) {
            Parameter parameter = field.getAnnotation(Parameter.class);

            if (parameter != null) {

                Object fieldValue;
                String name = parameter.value();
                String value = repository.read(name);

                if (value == null && parameter.required()) {
                    throw new ParameterException("Required parameter \"" + name + "\" not found.");
                }

                field.setAccessible(true);

                try {
                    fieldValue = field.get(configurationBean);
                } catch (IllegalAccessException e) {
                    throw new ParameterException("Couldn't obtain value of field " + field.getName(), e);
                }

                if (value != null) {

                    validateParameter(parameter, name, value);

                    Converter converter = getConverter(field.getType(), parameter.converter());

                    try {
                        field.set(configurationBean, converter.convert(value));
                    } catch (IllegalAccessException e) {
                        throw new ParameterException("Couldn't set field " + field.getName(), e);
                    }
                }
            }
        }
    }

    private Converter getConverter(Class<?> fieldType, Class<? extends Converter<?>> converterClass) {

        if (converterClass == null || converterClass == NoConverter.class) {
            converterClass = findConverter(fieldType);
        }

        // Fallback to StringConverter
        if (converterClass == null) {
            converterClass = StringConverter.class;
        }

        try {
            return converterClass.newInstance();
        } catch (Exception e) {
            throw new ParameterException("Couldn't initialize converter class " + converterClass.getCanonicalName(), e);
        }
    }

    private void validateParameter(Parameter parameter, String name, String value) throws ValidationException {
        Validator validator;

        try {
            validator = parameter.validator().newInstance();
        } catch (Exception e) {
            throw new ParameterException("Couldn't initialize validator " + parameter.validator().getCanonicalName(), e);
        }

        validator.validate(name, value);
    }

    private void invokeValidatorMethods(Method[] methods) throws ValidationException {
        for (Method method : methods) {
            ValidatorMethod validatorMethod = method.getAnnotation(ValidatorMethod.class);

            if (validatorMethod != null) {
                try {
                    method.invoke(configurationBean);
                } catch (Exception e) {
                    throw new ValidationException("Couldn't run validator method " + method.getName());
                }
            }
        }
    }

    private <T> Class<? extends Converter<T>> findConverter(Class<T> clazz) {
        for (ConverterFactory factory : CONVERTER_FACTORIES) {
            Class<? extends Converter<T>> result = factory.getConverter(clazz);

            if (result != null) {
                return result;
            }
        }

        return null;
    }
}
