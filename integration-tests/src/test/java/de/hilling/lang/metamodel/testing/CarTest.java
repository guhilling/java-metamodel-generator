package de.hilling.lang.metamodel.testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CarTest {

    private Car car;
    @BeforeEach
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
