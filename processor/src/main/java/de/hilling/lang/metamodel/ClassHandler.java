package de.hilling.lang.metamodel;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

/**
 * Collect information about the given class.
 */
public class ClassHandler {
    private final TypeElement type;
    private final Context     context;

    /**
     * @param element the class to check for attributes.
     * @param context generator context.
     */
    ClassHandler(TypeElement element, Context context) {
        this.context = context;
        this.type = element;
    }

    /**
     * Collect information about class attributes.
     */
    void invoke() {
        for (Element enclosed : type.getEnclosedElements()) {
            if (enclosed.getKind() == ElementKind.METHOD) {
                ExecutableElement executable = (ExecutableElement) enclosed;
                if (Utils.isGetter(executable)) {
                    String attributeName = Utils.attributeNameForAccessor(executable);
                    AttributeInfo info = context.getInfo(attributeName);
                    info.setType(executable.getReturnType());
                } else if (Utils.isSetter(executable)) {
                    String attributeName = Utils.attributeNameForAccessor(executable);
                    context.getInfo(attributeName).setWritable(true);
                }
            }
        }
    }
}
