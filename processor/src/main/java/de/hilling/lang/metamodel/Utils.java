package de.hilling.lang.metamodel;

import javax.lang.model.element.ExecutableElement;

class Utils {
    private Utils() {
    }

    /**
     * Liefert z.B. zu "Name" "name".
     *
     * @param name nicht-leere Zeichenkette.
     *
     * @return Wert mit erstem Zeichen als Kleinbuchstaben.
     */
    static String firstToLower(String name) {
        return name.substring(0, 1).toLowerCase() + name.substring(1);
    }

    static boolean isGetter(ExecutableElement executable) {
        return executable.getSimpleName().toString().startsWith("get") && executable.getParameters().isEmpty();
    }

    static boolean isSetter(ExecutableElement executable) {
        return executable.getSimpleName().toString().startsWith("set") && executable.getParameters().size() == 1;
    }

    /**
     * Liefert für einen getter oder setter den Attributnamen
     *
     * @param accessor
     *
     * @return Attributname
     */
    static String attributeNameForAccessor(ExecutableElement accessor) {
        String name = accessor.getSimpleName().toString().substring(3);
        return firstToLower(name);
    }
}
