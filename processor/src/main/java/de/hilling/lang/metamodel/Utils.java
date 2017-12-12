package de.hilling.lang.metamodel;

import javax.lang.model.element.ExecutableElement;


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
}
