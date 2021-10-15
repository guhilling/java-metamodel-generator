package de.hilling.lang.metamodel;

import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public abstract class SimpleObject__Metamodel {
    private static final List<Attribute> ATTRIBUTES;

    public static final MutableAttribute<SimpleObject, String> name;

    public static final MutableAttribute<SimpleObject, List<String>> friends;

    public static final Attribute<SimpleObject, Integer> age;

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
        friends = new MutableAttributeImplementation<SimpleObject, List<String>>("friends", SimpleObject.class, List.class) {
            @Override
            public List<String> readAttribute(SimpleObject object) {
                return object.getFriends();
            }

            @Override
            public void writeAttribute(SimpleObject object, List<String> value) {
                object.setFriends(value);
            }
        };
        age = new AttributeImplementation<SimpleObject, Integer>("age", SimpleObject.class, Integer.class) {
            @Override
            public Integer readAttribute(SimpleObject object) {
                return object.getAge();
            }
        };
        List<Attribute> attributesList = new LinkedList<>();
        attributesList.add(name);
        attributesList.add(age);
        attributesList.add(friends);
        ATTRIBUTES = Collections.unmodifiableList(attributesList);
    }

    public static List<Attribute> attributes() {
        return ATTRIBUTES;
    }
}
