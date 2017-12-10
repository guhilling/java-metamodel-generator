package de.hilling.lang.metamodel;

/**
 * Describes an Attribute of a {@link MetaModel}-annotated class.
 *
 * @param <T> class declaring the attribute.
 * @param <A> type of the attribute.
 */
public abstract class Attribute<T, A> {

    private final String   name;
    private final Class<T> declaringType;
    private final Class<A> javaType;
    private final boolean  readOnly;

    protected Attribute(String name, Class<T> declaringType, Class<A> javaType, boolean readOnly) {
        this.name = name;
        this.declaringType = declaringType;
        this.javaType = javaType;
        this.readOnly = readOnly;
    }

    /**
     * Return the name of the attribute.
     *
     * @return name
     */
    public final String getName() {
        return name;
    }

    /**
     * Return the managed type representing the type in which
     * the attribute was declared.
     *
     * @return declaring type
     */
    public final Class<T> getDeclaringType() {
        return declaringType;
    }

    /**
     * Return the Java type of the represented attribute.
     *
     * @return Java type
     */
    public final Class<A> getJavaType() {
        return javaType;
    }

    /**
     * true if no setter exists.
     */
    public final boolean isReadOnly() {
        return readOnly;
    }

    /**
     * Retrieve the attribute value from declaring object.
     *
     * @param object the object.
     *
     * @return value of the attribute in given object.
     */
    abstract A readAttribute(T object);

    /**
     * Set the attribute value on the given object.
     *
     * @param object the object.
     * @param value  value of attribute to set.
     */
    abstract void writeAttribute(T object, A value);
}
