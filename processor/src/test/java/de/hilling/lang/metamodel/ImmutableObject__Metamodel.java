package de.hilling.lang.metamodel;

import java.lang.Override;
import java.lang.String;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public abstract class ImmutableObject__Metamodel {
    private static final List<Attribute> ATTRIBUTES;

    public static final Attribute<ImmutableObject, String> name;

    static {
        name = new AttributeImplementation<ImmutableObject, String>("name", ImmutableObject.class, String.class) {
            @Override
            public String readAttribute(ImmutableObject object) {
                return object.getName();
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
