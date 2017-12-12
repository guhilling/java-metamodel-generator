package de.hilling.lang.metamodel;

/**
 * Stub implementation for {@link ReadOnlyAttribute}.
 *
 * @param <T> Declaring class of the attribute.
 * @param <A> Type of the attribute.
 */
public abstract class ReadOnlyAttributeImplementation<T, A> implements ReadOnlyAttribute<T, A> {
    protected final String   name;
    private final   Class<T> declaringType;
    private final   Class<A> javaType;

    public ReadOnlyAttributeImplementation(String name, Class<T> declaringType, Class<A> javaType) {
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
