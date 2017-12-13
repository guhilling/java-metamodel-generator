package de.hilling.lang.metamodel;

/**
 * Stub implementation for {@link Attribute}.
 *
 * @param <T> Declaring class of the attribute.
 * @param <A> Type of the attribute.
 */
public abstract class AttributeImplementation<T, A> implements Attribute<T, A> {
    protected final String   name;
    private final   Class<T> declaringType;
    private final   Class<A> javaType;

    protected AttributeImplementation(String name, Class<T> declaringType, Class<A> javaType) {
        this.javaType = javaType;
        this.name = name;
        this.declaringType = declaringType;
    }

    public final String getName() {
        return name;
    }

    public final Class<T> getDeclaringType() {
        return declaringType;
    }

    public final Class<A> getJavaType() {
        return javaType;
    }
}
