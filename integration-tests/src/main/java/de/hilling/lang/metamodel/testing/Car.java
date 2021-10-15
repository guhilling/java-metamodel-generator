package de.hilling.lang.metamodel.testing;

import de.hilling.lang.metamodel.GenerateModel;

@GenerateModel
public class Car {
    private       String model;
    private final int    year;

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
}
