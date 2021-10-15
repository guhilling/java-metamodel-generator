package de.hilling.lang.metamodel.testing;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class CarTest {

    private Car car;
    @Before
    public void setUp() {
        car = new Car(1974);
        car.setModel("Golf");
        car.getOwners().add("Erna");
        car.getOwners().add("Gunnar");
    }

    @Test
    public void readAttributes() {
        assertEquals("Golf", Car__Metamodel.model.readAttribute(car));
        assertEquals((Integer) 1974, Car__Metamodel.year.readAttribute(car));
        List<String> owners = Car__Metamodel.owners.readAttribute(car);
        assertEquals(2, owners.size());
        assertEquals("Erna", owners.get(0));
        assertEquals("Gunnar", owners.get(1));
    }

    @Test
    public void writeAttributes() {
        Car__Metamodel.model.writeAttribute(car, "Polo");
        assertEquals("Polo", car.getModel());
    }
}
