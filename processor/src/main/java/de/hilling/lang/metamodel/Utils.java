package de.hilling.lang.metamodel;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.type.TypeMirror;

import com.squareup.javapoet.TypeName;


class Utils {
    private Utils() {
    }

    private static String firstToLower(String name) {
        return name.substring(0, 1).toLowerCase() + name.substring(1);
    }

    static String firstToUpper(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    static boolean isGetter(ExecutableElement executable) {
        return executable.getSimpleName().toString().startsWith("get") && executable.getParameters().isEmpty();
    }

    static boolean isSetter(ExecutableElement executable) {
        return executable.getSimpleName().toString().startsWith("set") && executable.getParameters().size() == 1;
    }

    static String attributeNameForAccessor(ExecutableElement accessor) {
        String name = accessor.getSimpleName().toString().substring(3);
        return firstToLower(name);
    }

    static TypeName getAttributeTypeName(AttributeInfo info) {
        TypeMirror type = info.getType();
        if(type.getKind().isPrimitive()) {
            return TypeName.get(type).box();
        } else {
            return TypeName.get(type);
        }
    }
}
