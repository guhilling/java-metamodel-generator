package de.hilling.lang.metamodel;

/**
 * Description for a read-only attribute of a bean-style class.
 * @param <T> Declaring class of the attribute.
 * @param <A> Type of the attribute.
 */
public interface ReadOnlyAttribute<T, A> {
    /**
     * Return the name of the attribute.
     *
     * @return name
     */
    String getName();

    /**
     * Return the managed type representing the type in which
     * the attribute was declared.
     *
     * @return declaring type
     */
    Class<T> getDeclaringType();

    /**
     * Return the Java type of the represented attribute.
     *
     * @return Java type
     */
    Class<A> getJavaType();

    /**
     * Retrieve the attribute value from declaring object.
     *
     * @param object the object.
     *
     * @return value of the attribute in given object.
     */
    A readAttribute(T object);
}
