package com.github.joschi.jadconfig.guice;

import com.github.joschi.jadconfig.Parameter;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static com.github.joschi.jadconfig.ReflectionUtils.getAllFields;
import static com.github.joschi.jadconfig.ReflectionUtils.getFieldValue;
import static com.google.inject.name.Names.named;

/**
 * A Guice module which registers all fields of the provided objects annotated with {@link Parameter}
 * as named bindings and optionally also adds bindings of the object instances themselves.
 *
 * @see com.google.inject.name.Named
 */
public class NamedConfigParametersModule extends AbstractModule {
    private static final Logger LOG = LoggerFactory.getLogger(NamedConfigParametersModule.class);

    private final Set<Object> beans;
    private final boolean registerBeans;

    /**
     * Create a new {@link NamedConfigParametersModule} instance.
     *
     * @param beans         A {@link Collection} containing all instances of the configuration beans which
     *                      should be registered.
     * @param registerBeans If {@code true}, the module will add bindings for the instances in {@code beans}.
     */
    public NamedConfigParametersModule(final Collection beans, final boolean registerBeans) {
        this.beans = new HashSet<Object>(beans);
        this.registerBeans = registerBeans;
    }

    /**
     * Create a new {@link NamedConfigParametersModule} instance and add bindings for the instances in {@code beans}.
     *
     * @param beans A {@link Collection} containing all instances of the configuration beans which
     *              should be registered.
     */
    public NamedConfigParametersModule(final Collection beans) {
        this(beans, true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void configure() {
        if (registerBeans) {
            registerBeanInstances();
        }

        for (final Object bean : beans) {
            registerParameters(bean);
        }
    }

    @SuppressWarnings("unchecked")
    private void registerParameters(Object bean) {
        final Field[] fields = getAllFields(bean.getClass());

        for (Field field : fields) {
            final Parameter parameter = field.getAnnotation(Parameter.class);

            if (parameter != null) {
                try {
                    final TypeLiteral typeLiteral = TypeLiteral.get(field.getGenericType());
                    final Object value = getFieldValue(bean, field);
                    bind(typeLiteral).annotatedWith(named(parameter.value())).toInstance(value);
                } catch (IllegalAccessException e) {
                    LOG.warn("Couldn't bind \"" + field.getName() + "\"", e);
                }
            } else {
                LOG.debug("Skipping field {}", field.getName());
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void registerBeanInstances() {
        for (final Object bean : beans) {
            final TypeLiteral typeLiteral = TypeLiteral.get(bean.getClass());
            bind(typeLiteral).toInstance(bean);
        }
    }
}
