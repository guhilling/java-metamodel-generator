package de.hilling.lang.metamodel;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

/**
 * Collect information about the given class.
 */
public class ClassHandler {
    private final TypeElement type;
    private final ClassModel  classModel;

    /**
     * @param element the class to check for attributes.
     * @param processingEnvironment environment.
     */
    ClassHandler(TypeElement element, ProcessingEnvironment processingEnvironment) {
        this.classModel = new ClassModel(processingEnvironment);
        this.type = element;
    }

    /**
     * Collect information about class attributes.
     */
    ClassModel invoke() {
        type.getEnclosedElements().stream()
            .filter(element -> element.getKind() == ElementKind.METHOD)
            .map(element -> (ExecutableElement) element)
            .forEach(this::collectAccessorInfo);
        return classModel;
    }

    private void collectAccessorInfo(ExecutableElement methodRef) {
        if (Utils.isGetter(methodRef)) {
            String attributeName = Utils.attributeNameForAccessor(methodRef);
            AttributeInfo info = classModel.getInfo(attributeName);
            info.setType(methodRef.getReturnType());
        } else if (Utils.isSetter(methodRef)) {
            String attributeName = Utils.attributeNameForAccessor(methodRef);
            classModel.getInfo(attributeName).setWritable(true);
        }
    }
}
