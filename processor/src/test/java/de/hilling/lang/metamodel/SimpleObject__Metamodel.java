package de.hilling.lang.metamodel;

import java.lang.Override;
import java.lang.String;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public abstract class SimpleObject__Metamodel {
    private static final List<Attribute> ATTRIBUTES;

    public static final MutableAttribute<SimpleObject, String> name;

    static {
        name = new MutableAttributeImplementation<SimpleObject, String>("name", SimpleObject.class, String.class) {
            @Override
            public String readAttribute(SimpleObject object) {
                return object.getName();
            }

            @Override
            public void writeAttribute(SimpleObject object, String value) {
                object.setName(value);
            }
        };

        List<Attribute> attributesList = new LinkedList<>();
        attributesList.add(name);
        ATTRIBUTES = Collections.unmodifiableList(attributesList);
    }

    public static List<Attribute> attributes() {
        return ATTRIBUTES;
    }

}
