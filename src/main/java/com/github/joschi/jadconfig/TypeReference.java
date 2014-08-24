package com.github.joschi.jadconfig;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * References a generic type.
 *
 * @author crazybob@google.com (Bob Lee)
 * @see <a href="http://gafter.blogspot.de/2006/12/super-type-tokens.html">Super Type Tokens</a>
 */
public abstract class TypeReference<T> implements Comparable<TypeReference<T>> {
    private final Type type;
    private volatile Constructor<?> constructor;

    protected TypeReference() {
        final Type superClass = getClass().getGenericSuperclass();

        if (superClass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }

        this.type = ((ParameterizedType) superClass).getActualTypeArguments()[0];
    }

    /**
     * Instantiates a new instance of {@code T} using the default, no-arg
     * constructor.
     */
    @SuppressWarnings("unchecked")
    public T newInstance() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        if (constructor == null) {
            Class<?> rawType = type instanceof Class<?>
                    ? (Class<?>) type
                    : (Class<?>) ((ParameterizedType) type).getRawType();
            constructor = rawType.getConstructor();
        }
        return (T) constructor.newInstance();
    }

    /**
     * Gets the referenced type.
     */
    public Type getType() {
        return this.type;
    }

    /**
     * Dummy implementation to prevent constructing a reference without type information.
     *
     * @see <a href="http://gafter.blogspot.de/2006/12/super-type-tokens.html?showComment=1171980720000#c2954512480577635806">Super Type Tokens</a>
     */
    @Override
    public int compareTo(final TypeReference<T> obj) {
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        return (this == obj) || ((obj instanceof TypeReference) && ((TypeReference) obj).type.equals(type));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return type.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "TypeReference{" + type.toString() + "}";
    }
}