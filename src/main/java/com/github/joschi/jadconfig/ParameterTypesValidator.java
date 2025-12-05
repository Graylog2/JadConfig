package com.github.joschi.jadconfig;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.PrimitiveType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Types;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@javax.annotation.processing.SupportedAnnotationTypes("com.github.joschi.jadconfig.Parameter")
@javax.annotation.processing.SupportedSourceVersion(SourceVersion.RELEASE_8)
public class ParameterTypesValidator extends AbstractProcessor {

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Types typeUtils = processingEnv.getTypeUtils();

        for (TypeElement annotation : annotations) {
            // Find elements annotated with MyCustomAnnotation
            for (Element element : roundEnv.getElementsAnnotatedWith(annotation)) {
                if (element.getKind().isField()) {
                    VariableElement field = (VariableElement) element;

                    final String fieldName = getFieldName(field);
                    TypeMirror fieldType = getBoxedType(typeUtils, field.asType());

                    final AnnotationMirror annotationMirror = element.getAnnotationMirrors()
                            .stream()
                            .filter(mirror -> mirror.getAnnotationType().toString().equals(
                                    Parameter.class.getCanonicalName())).findFirst()
                            .orElseThrow(() -> new IllegalStateException("This should not happen"));

                    final String parameterName = annotationMirror.getElementValues().entrySet().stream()
                            .filter(entry -> entry.getKey().getSimpleName().toString().equals("value"))
                            .map(entry -> (String) entry.getValue().getValue())
                            .findFirst()
                            .orElseThrow(() -> new IllegalStateException("Value is mandatory!"));

                    verifyConverterType(annotationMirror, typeUtils, fieldType, parameterName, fieldName);
                    verifyValidators(annotationMirror, typeUtils, fieldType, parameterName, fieldName);
                }
            }
        }
        return false;
    }

    private static String getFieldName(VariableElement field) {
        final String className = getParentClassName(field);
        return className + "#" + field.getSimpleName().toString();
    }

    private static String getParentClassName(VariableElement field) {
        final Element enclosingElement = field.getEnclosingElement();
        if (enclosingElement.getKind() == ElementKind.CLASS) {
            final TypeElement classElement = (TypeElement) enclosingElement;
            return classElement.getQualifiedName().toString();
        } else {
            return enclosingElement.getSimpleName().toString();
        }
    }

    private void verifyConverterType(AnnotationMirror annotationMirror, Types types, TypeMirror fieldType, String parameterName, String fieldName) {
        final Optional<TypeMirror> converter = annotationMirror.getElementValues().entrySet().stream()
                .filter(entry -> entry.getKey().getSimpleName().toString().equals("converter"))
                .map(entry -> (TypeMirror) entry.getValue().getValue())
                .findFirst();

        converter.ifPresent(converterValue -> {
            TypeElement converterType = (TypeElement) types.asElement(converterValue);

            List<? extends Element> members =
                    processingEnv.getElementUtils().getAllMembers(converterType);

            ExecutableElement convertFromMethod = members.stream()
                    .filter(e -> e.getKind() == ElementKind.METHOD)
                    .map(e -> (ExecutableElement) e)
                    .filter(m -> m.getSimpleName().contentEquals("convertFrom"))
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException("This should not happen"));

            final TypeMirror converterReturnType = convertFromMethod.getReturnType();
            if (!types.isSameType(converterReturnType, fieldType)) {
                processingEnv.getMessager().printError("Property " + parameterName + " assigned to field " + fieldName + " has type " + fieldType + " but converter expects " + converterReturnType);
            }
        });
    }

    private void verifyValidators(AnnotationMirror annotationMirror, Types types, TypeMirror fieldType, String parameterName, String fieldName) {
        annotationMirror.getElementValues().entrySet().stream()
                .filter(entry -> entry.getKey().getSimpleName().toString().equals("validators"))
                .flatMap(entry -> {
                    List<? extends AnnotationValue> values =
                            (List<? extends AnnotationValue>) entry.getValue().getValue();
                    return values.stream()
                            .map(v -> (TypeMirror) v.getValue());
                })
                .map(type -> (TypeElement) types.asElement(type))
                .forEach(validatorType -> {
                    List<? extends Element> members =
                            processingEnv.getElementUtils().getAllMembers(validatorType);

                    ExecutableElement validatorMethod = members.stream()
                            .filter(e -> e.getKind() == ElementKind.METHOD)
                            .map(e -> (ExecutableElement) e)
                            .filter(m -> m.getSimpleName().contentEquals("validate"))
                            .findFirst()
                            .orElseThrow(() -> new IllegalStateException("This should not happen"));

                    final TypeMirror acceptedValidatorType = getValidatorType(types, validatorMethod);

                    if (!types.isSameType(acceptedValidatorType, fieldType)) {
                        processingEnv.getMessager().printError("Property " + parameterName + " assigned to field " + fieldName + " has type " + fieldType + " but validator expects " + acceptedValidatorType);
                    }
                });

    }

    private static TypeMirror getValidatorType(Types types, ExecutableElement convertFromMethod) {
        final List<? extends VariableElement> converterReturnType = convertFromMethod.getParameters();
        final TypeMirror acceptedValidatorType = converterReturnType.get(1).asType();
        return getBoxedType(types, acceptedValidatorType);

    }

    private static TypeMirror getBoxedType(Types types, TypeMirror type) {
        if (type.getKind().isPrimitive()) {
            TypeElement boxed = types.boxedClass((PrimitiveType) type);
            return boxed.asType();
        } else {
            return type;
        }
    }
}
