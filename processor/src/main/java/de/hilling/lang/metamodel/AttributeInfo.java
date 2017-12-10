package de.hilling.lang.metamodel;

import javax.lang.model.type.TypeMirror;

public class AttributeInfo {
    private boolean    getterFound;
    private boolean    setterFound;
    private TypeMirror type;

    public boolean isGetterFound() {
        return getterFound;
    }

    public void setGetterFound(boolean getterFound) {
        this.getterFound = getterFound;
    }

    public boolean isSetterFound() {
        return setterFound;
    }

    public void setSetterFound(boolean setterFound) {
        this.setterFound = setterFound;
    }

    public void setType(TypeMirror type) {
        this.type = type;
    }

    public TypeMirror getType() {
        return type;
    }
}
