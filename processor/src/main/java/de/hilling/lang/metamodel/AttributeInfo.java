package de.hilling.lang.metamodel;

import javax.lang.model.type.TypeMirror;

/**
 * Information about found attribute.
 */
public class AttributeInfo {
    private boolean    writable;
    private TypeMirror type;

    public boolean isWritable() {
        return writable;
    }

    public void setWritable(boolean writable) {
        this.writable = writable;
    }

    public void setType(TypeMirror type) {
        this.type = type;
    }

    public TypeMirror getType() {
        return type;
    }
}
