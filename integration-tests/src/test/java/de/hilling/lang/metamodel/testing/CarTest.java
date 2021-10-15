package de.hilling.lang.metamodel.testing;

import static de.hilling.lang.metamodel.testing.Person__Metamodel.birthDate;
import static de.hilling.lang.metamodel.testing.Person__Metamodel.firstName;
import static de.hilling.lang.metamodel.testing.Person__Metamodel.lastName;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.hilling.lang.metamodel.Attribute;

public class CarTest {

    private Car car;


    @Before
    public void setUp() {
        car = new Car(1974);
        car.setModel("Golf");
    }

    @Test
    public void readAttributes() {
        assertEquals("Golf", Car__Metamodel.model.readAttribute(car));
        assertEquals((Integer) 1974, Car__Metamodel.year.readAttribute(car));
    }

    @Test
    public void writeAttributes() {
        Car__Metamodel.model.writeAttribute(car, "Polo");
        assertEquals("Polo", car.getModel());
    }
}
