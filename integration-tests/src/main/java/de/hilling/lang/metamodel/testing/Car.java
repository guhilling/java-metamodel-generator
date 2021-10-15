package de.hilling.lang.metamodel.testing;

import java.util.ArrayList;
import java.util.List;

import de.hilling.lang.metamodel.GenerateModel;

@GenerateModel
public class Car {
    private       String model;
    private final int    year;
    private final List<String> owners = new ArrayList();

    public Car(int year) {
        this.year = year;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public int getYear() {
        return year;
    }

    public List<String> getOwners() {
        return owners;
    }
}
