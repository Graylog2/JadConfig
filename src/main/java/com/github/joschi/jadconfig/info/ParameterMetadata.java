package com.github.joschi.jadconfig.info;

import com.github.joschi.jadconfig.Parameter;
import com.github.joschi.jadconfig.documentation.Documentation;
import jakarta.annotation.Nullable;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Objects;

/**
 * Metadata of a configuration parameter derived from the annotations of its field. This is the single place
 * interpreting the annotations of a parameter field, shared by {@link com.github.joschi.jadconfig.JadConfig} and the
 * {@link com.github.joschi.jadconfig.documentation.ConfigurationDocsGenerator documentation generator}.
 *
 * @param name          The parameter name, see {@link Parameter#value()}
 * @param fieldName     The name of the field
 * @param type          The (generic) type of the field, e. g. {@code java.util.List<java.lang.String>}
 * @param required      Whether the parameter is {@link Parameter#required() required}
 * @param nullable      Whether the field may be {@code null} after processing, i. e. it is neither a primitive nor
 *                      required
 * @param sensitive     Whether the parameter has been marked as {@link Parameter#sensitive() sensitive}
 * @param documentation The {@link Documentation#value() documentation} of the parameter, {@code null} if missing or empty
 * @param visible       Whether the parameter is {@link Documentation#visible() visible} to users
 */
public record ParameterMetadata(
        String name,
        String fieldName,
        Type type,
        boolean required,
        boolean nullable,
        boolean sensitive,
        @Nullable String documentation,
        boolean visible
) {

    public ParameterMetadata {
        Objects.requireNonNull(name, "name");
        Objects.requireNonNull(fieldName, "fieldName");
        Objects.requireNonNull(type, "type");
    }

    /**
     * Reads the metadata of a field annotated with {@link Parameter}.
     *
     * @throws IllegalArgumentException if the field isn't annotated with {@link Parameter}
     */
    public static ParameterMetadata of(Field field) {
        final Parameter parameter = field.getAnnotation(Parameter.class);
        if (parameter == null) {
            throw new IllegalArgumentException("Field " + field + " isn't annotated with @Parameter");
        }
        final Documentation documentation = field.getAnnotation(Documentation.class);

        return new ParameterMetadata(
                parameter.value(),
                field.getName(),
                field.getGenericType(),
                parameter.required(),
                !field.getType().isPrimitive() && !parameter.required(),
                parameter.sensitive(),
                documentation == null || documentation.value().isBlank() ? null : documentation.value(),
                documentation == null || documentation.visible());
    }
}
