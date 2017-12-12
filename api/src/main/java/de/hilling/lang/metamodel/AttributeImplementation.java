package de.hilling.lang.metamodel;

/**
 * Stub implementation for {@link Attribute}.
 *
 * @param <T> Declaring class of the attribute.
 * @param <A> Type of the attribute.
 */
public abstract class AttributeImplementation<T, A> extends ReadOnlyAttributeImplementation<T, A> implements Attribute<T, A> {

    protected AttributeImplementation(String name, Class<T> declaringType, Class<A> javaType) {
        super(name, declaringType, javaType);
    }
}
