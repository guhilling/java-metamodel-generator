package de.hilling.lang.metamodel;

@GenerateModel
public class SimpleObject {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
