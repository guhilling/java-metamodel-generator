package de.hilling.lang.metamodel;

import java.lang.Override;
import java.lang.String;

public abstract class ImmutableObject__Metamodel {

    public static final ReadOnlyAttribute<ImmutableObject, String> name;

    static {
        name = new ReadOnlyAttributeImplementation<ImmutableObject, String>("name", ImmutableObject.class, String.class) {
            @Override
            public String readAttribute(ImmutableObject object) {
                return object.getName();
            }
        };
    }
}
