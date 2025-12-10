package com.github.joschi.jadconfig;

import com.github.joschi.jadconfig.documentation.Documentation;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Types;
import java.util.Optional;
import java.util.Set;

@javax.annotation.processing.SupportedAnnotationTypes("com.github.joschi.jadconfig.Parameter")
@javax.annotation.processing.SupportedSourceVersion(SourceVersion.RELEASE_8)
public class ParameterDocumentationValidator extends AbstractProcessor {

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

        final AnnotationMirror parameterAnnotationMirror = getAnnotationMirror(field, Parameter.class)
                .orElseThrow(() -> new IllegalStateException("This should not happen, field should be always annotated with @Parameter"));

        final Optional<? extends AnnotationMirror> documentationAnnotationMirror = getAnnotationMirror(field, Documentation.class);

        final String parameterName = getParameterValue(parameterAnnotationMirror);

        documentationAnnotationMirror.ifPresentOrElse(documentationAnnotation -> {
            final boolean isDocumentationVisible = isDocumentationVisible(documentationAnnotation);
            final Optional<String> documentationText = getDocumentationText(documentationAnnotation);

            if (isDocumentationVisible && documentationText.isEmpty()) {
                processingEnv.getMessager().printError("Property " + parameterName + " assigned to field " + fieldName + " has no documentation available. Please, add @Documentation annotation value!", field);
            }
        }, () -> {
            processingEnv.getMessager().printError("Property " + parameterName + " assigned to field " + fieldName + " has no documentation available. Please, add @Documentation annotation!", field);
        });
    }

    private Optional<String> getDocumentationText(AnnotationMirror documentationAnnotationMirror) {
        return documentationAnnotationMirror.getElementValues().entrySet().stream()
                .filter(entry -> entry.getKey().getSimpleName().toString().equals("value"))
                .map(entry -> (String) entry.getValue().getValue())
                .findFirst()
                .filter(value -> !StringUtils.isEmpty(value));

    }

    private static String getParameterValue(AnnotationMirror annotationMirror) {
        return annotationMirror.getElementValues().entrySet().stream()
                .filter(entry -> entry.getKey().getSimpleName().toString().equals("value"))
                .map(entry -> (String) entry.getValue().getValue())
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Value is mandatory!"));
    }

    private static boolean isDocumentationVisible(AnnotationMirror annotationMirror) {
        return annotationMirror.getElementValues().entrySet().stream()
                .filter(entry -> entry.getKey().getSimpleName().toString().equals("visible"))
                .map(entry -> (Boolean) entry.getValue().getValue())
                .findFirst()
                .orElse(true);
    }

    private static Optional<? extends AnnotationMirror> getAnnotationMirror(Element element, Class<?> annotationClass) {
        return element.getAnnotationMirrors()
                .stream()
                .filter(mirror -> mirror.getAnnotationType().toString().equals(
                        annotationClass.getCanonicalName()))
                .findFirst();
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
}
