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

public class ParameterTypesValidator extends AbstractProcessor {

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Set.of(Parameter.class.getCanonicalName());
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.RELEASE_8;
    }

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Types typeUtils = processingEnv.getTypeUtils();

        annotations.stream()
                .flatMap(annotation -> roundEnv.getElementsAnnotatedWith(annotation).stream())
                .map(element -> (VariableElement) element)
                .forEach(element -> {
                    processField(element, typeUtils);
                });
        return false; // do not claim this annotation, let other processors handle it as well
    }

    private void processField(VariableElement field, Types typeUtils) {
        final String fieldName = getFieldName(field);
        TypeMirror fieldType = getBoxedType(typeUtils, field.asType());

        final AnnotationMirror annotationMirror = getParameterAnnotation(field);
        final String parameterName = getParameterValue(annotationMirror);

        verifyConverterType(annotationMirror, typeUtils, field, fieldType, parameterName, fieldName);
        verifyValidators(annotationMirror, typeUtils, field, fieldType, parameterName, fieldName);
    }

    private static String getParameterValue(AnnotationMirror annotationMirror) {
        return annotationMirror.getElementValues().entrySet().stream()
                .filter(entry -> entry.getKey().getSimpleName().toString().equals("value"))
                .map(entry -> (String) entry.getValue().getValue())
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Value is mandatory!"));
    }

    private static AnnotationMirror getParameterAnnotation(Element element) {
        return element.getAnnotationMirrors()
                .stream()
                .filter(mirror -> mirror.getAnnotationType().toString().equals(
                        Parameter.class.getCanonicalName())).findFirst()
                .orElseThrow(() -> new IllegalStateException("This should not happen, the element should be always annotated with Parameter annotation"));
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

    private void verifyConverterType(AnnotationMirror annotationMirror, Types types, VariableElement field, TypeMirror fieldType, String parameterName, String fieldName) {
        getConverter(annotationMirror).ifPresent(converterValue -> {
            TypeElement converterType = (TypeElement) types.asElement(converterValue);
            List<? extends Element> members = processingEnv.getElementUtils().getAllMembers(converterType);
            ExecutableElement convertFromMethod = getConvertFromMethod(members);

            final TypeMirror converterReturnType = convertFromMethod.getReturnType();
            if (!types.isSameType(converterReturnType, fieldType)) {
                processingEnv.getMessager().printError("Property " + parameterName + " assigned to field " + fieldName + " has type " + fieldType + " but converter expects " + converterReturnType, field);
            }
        });
    }

    private static ExecutableElement getConvertFromMethod(List<? extends Element> members) {
        return members.stream()
                .filter(e -> e.getKind() == ElementKind.METHOD)
                .map(e -> (ExecutableElement) e)
                .filter(m -> m.getSimpleName().contentEquals("convertFrom"))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("This should not happen, converter should contain a convertFrom method"));
    }

    private static Optional<TypeMirror> getConverter(AnnotationMirror annotationMirror) {
        return annotationMirror.getElementValues().entrySet().stream()
                .filter(entry -> entry.getKey().getSimpleName().toString().equals("converter"))
                .map(entry -> (TypeMirror) entry.getValue().getValue())
                .findFirst();
    }

    private void verifyValidators(AnnotationMirror annotationMirror, Types types, VariableElement field, TypeMirror fieldType, String parameterName, String fieldName) {
        annotationMirror.getElementValues().entrySet().stream()
                .filter(entry -> entry.getKey().getSimpleName().toString().equals("validators"))
                .flatMap(entry -> {
                    List<? extends AnnotationValue> values = (List<? extends AnnotationValue>) entry.getValue().getValue();
                    return values.stream().map(v -> (TypeMirror) v.getValue());
                })
                .map(type -> (TypeElement) types.asElement(type))
                .forEach(validatorType -> verifyValidator(types, field, fieldType, parameterName, fieldName, validatorType));
    }

    private void verifyValidator(Types types, VariableElement field, TypeMirror fieldType, String parameterName, String fieldName, TypeElement validatorType) {
        final ExecutableElement validatorMethod = getValidateMethod(validatorType);
        final TypeMirror acceptedValidatorType = getValidatorType(types, validatorMethod);

        if (!types.isSameType(acceptedValidatorType, fieldType)) {
            processingEnv.getMessager().printError("Property " + parameterName + " assigned to field " + fieldName + " has type " + fieldType + " but validator expects " + acceptedValidatorType, field);
        }
    }

    private ExecutableElement getValidateMethod(TypeElement validatorType) {
        List<? extends Element> members =
                processingEnv.getElementUtils().getAllMembers(validatorType);

        return members.stream()
                .filter(e -> e.getKind() == ElementKind.METHOD)
                .map(e -> (ExecutableElement) e)
                .filter(m -> m.getSimpleName().contentEquals("validate"))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("This should not happen"));
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
