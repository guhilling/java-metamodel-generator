package de.hilling.lang.metamodel;

import java.util.Optional;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;

@SupportedAnnotationTypes({"de.hilling.lang.metamodel.GenerateModel"})
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class MetamodelVerifier extends AbstractProcessor {

    public static final String ERROR_MESSAGE = "wrong use of annotation: must be used on class or abstract class.";

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(GenerateModel.class)) {
            verifyNotAnAnnotation(element);
        }
        return false;
    }

    private void verifyNotAnAnnotation(Element element) {
        if(element.getKind() == ElementKind.ANNOTATION_TYPE){
            compilerErrorMessage(element);
        }
    }

    private void compilerErrorMessage(Element element) {
        final Optional<? extends AnnotationMirror> first = element.getAnnotationMirrors().stream()
                                                                  .filter(this::matchGenerateModelAnnotation)
                                                                  .findFirst();
        AnnotationMirror annotationMirror = element.getAnnotationMirrors().get(0);
        processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,
                                                 ERROR_MESSAGE,
                                                 element,
                                                 first.get());
    }

    private boolean matchGenerateModelAnnotation(AnnotationMirror m) {
        final Types typeUtils = processingEnv.getTypeUtils();
        final Elements elementUtils = processingEnv.getElementUtils();
        DeclaredType annotationType = m.getAnnotationType();
       // typeUtils.isSameType(annotationType, typeUtils.);
        return m.getAnnotationType() != null;
    }
}
