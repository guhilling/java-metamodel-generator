package de.hilling.lang.metamodel;

/**
 * Description for a mutable attribute of a bean-style class.
 * @param <T> Declaring class of the attribute.
 * @param <A> Type of the attribute.
 */
public interface Attribute<T, A> extends ReadOnlyAttribute<T, A> {

    /**
     * Set the attribute value on the given object.
     *
     * @param object the object.
     * @param value  value of attribute to set.
     */
    void writeAttribute(T object, A value);
}
