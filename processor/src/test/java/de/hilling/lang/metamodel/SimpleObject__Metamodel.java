package de.hilling.lang.metamodel;

import java.lang.Override;
import java.lang.String;

public abstract class SimpleObject__Metamodel {

    public static final Attribute<SimpleObject, String> name;

    static {
        name = new AttributeImplementation<SimpleObject, String>("name", SimpleObject.class, String.class) {
            @Override
            public String readAttribute(SimpleObject object) {
                return object.getName();
            }

            @Override
            public void writeAttribute(SimpleObject object, String value) {
                object.setName(value);
            }
        };
    }
}
