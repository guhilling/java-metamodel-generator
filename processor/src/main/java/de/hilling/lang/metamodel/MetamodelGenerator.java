package de.hilling.lang.metamodel;

import java.io.IOException;
import java.util.Collections;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

/**
 * Generator for
 */
public class MetamodelGenerator extends AbstractProcessor {

    private Context context;
    private int round;

    @Override
    public void init(ProcessingEnvironment env) {
        context = new Context(env);
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(GenerateModel.class)) {
            TypeElement typeElement = (TypeElement) element;
            messager().printMessage(Diagnostic.Kind.NOTE, "processing " + element);
            new ClassHandler(typeElement, context).invoke();
            writeMetaClass(typeElement, context);
            context.clear();
        }
        if (!roundEnv.processingOver() && round > 20) {
            messager().printMessage(Diagnostic.Kind.ERROR, "possible processing loop detected (21)");
        }
        round++;
        return false;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Collections.singleton(GenerateModel.class.getCanonicalName());
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.RELEASE_8;
    }

    private void writeMetaClass(TypeElement element, Context context) {
        try {
            new MetaClassWriter(element, context).invoke();
        } catch (IOException e) {
            messager().printMessage(Diagnostic.Kind.ERROR, "Writing metaclass failed", element);
        }
    }

    private Messager messager() {
        return context.getEnvironment().getMessager();
    }

}
