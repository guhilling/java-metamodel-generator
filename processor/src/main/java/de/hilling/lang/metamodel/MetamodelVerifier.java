package de.hilling.lang.metamodel;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

@SupportedAnnotationTypes({"de.hilling.lang.metamodel.GenerateModel"})
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
            processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, ERROR_MESSAGE);
        }
    }
}
