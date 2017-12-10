package de.hilling.lang.metamodel;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

public class ClassHandler {
    private final TypeElement type;
    private final Context     context;

    ClassHandler(TypeElement element, Context context) {
        this.context = context;
        this.type = element;
    }

    void invoke() {
        for (Element enclosed : type.getEnclosedElements()) {
            if (enclosed.getKind() == ElementKind.METHOD) {
                ExecutableElement executable = (ExecutableElement) enclosed;
                if (Utils.isGetter(executable)) {
                    String attributeName = Utils.attributeNameForAccessor(executable);
                    AttributeInfo info = context.getInfo(attributeName);
                    info.setGetterFound(true);
                    info.setType(executable.getReturnType());
                } else if (Utils.isSetter(executable)) {
                    String attributeName = Utils.attributeNameForAccessor(executable);
                    context.getInfo(attributeName).setSetterFound(true);
                }
            }
        }
    }
}
