package de.hilling.lang.metamodel;

/**
 * Stub implementation for {@link MutableAttribute}.
 *
 * @param <T> Declaring class of the attribute.
 * @param <A> Type of the attribute.
 */
public abstract class MutableAttributeImplementation<T, A>
        extends AttributeImplementation<T, A>
        implements MutableAttribute<T, A> {

    protected MutableAttributeImplementation(String name, Class<T> declaringType, Class<A> javaType) {
        super(name, declaringType, javaType);
    }
}
