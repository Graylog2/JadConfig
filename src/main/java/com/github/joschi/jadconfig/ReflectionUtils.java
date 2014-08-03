package com.github.joschi.jadconfig;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Reflection related helper class.
 *
 * @see java.lang.reflect
 */
public final class ReflectionUtils {

    private ReflectionUtils() {
    }

    public static Object getFieldValue(final Object instance, final Field field) throws IllegalAccessException {
        field.setAccessible(true);
        return field.get(instance);
    }

    public static Field[] getAllFields(final Class<?> klass) {
        final List<Field> fields = new ArrayList<Field>();
        for (Class<?> c = klass; c != null; c = c.getSuperclass()) {
            fields.addAll(Arrays.asList(c.getDeclaredFields()));
        }

        final Field[] result = new Field[fields.size()];
        return fields.toArray(result);
    }

    public static Method[] getAllMethods(final Class<?> klass) {
        final List<Method> methods = new ArrayList<Method>();
        for (Class<?> c = klass; c != null; c = c.getSuperclass()) {
            methods.addAll(Arrays.asList(c.getDeclaredMethods()));
        }

        final Method[] result = new Method[methods.size()];
        return methods.toArray(result);
    }

    public static Object[] invokeMethodsWithAnnotation(final Object instance,
                                                       final Class<? extends Annotation> annotationClass,
                                                       final Method[] methods) throws Exception {
        final Object[] results = new Object[methods.length];
        int i = 0;

        for (Method method : methods) {
            if (method.isAnnotationPresent(annotationClass)) {
                results[i] = method.invoke(instance);
            }
        }

        return results;
    }
}
