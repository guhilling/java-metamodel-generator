package de.hilling.lang.metamodel;

public abstract class SimpleObject__Metamodel {

    public static final Attribute<SimpleObject, String> name;

    static {
        name = new Attribute<SimpleObject, String>("name", SimpleObject.class, String.class, false) {
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
