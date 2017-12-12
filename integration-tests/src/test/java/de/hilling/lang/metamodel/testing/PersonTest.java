package de.hilling.lang.metamodel.testing;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PersonTest {

    private static final LocalDate BIRTH_DATE = LocalDate.of(1971, Month.JUNE, 15);
    private Person person;

    @Before
    public void setUp() {
        person = new Person();
        person.setFirstName("Gunnar");
        person.setLastName("Hilling");
        person.setBirthDate(BIRTH_DATE);
    }

    @Test
    public void readAttributes() {
        Assert.assertEquals(BIRTH_DATE, Person__Metamodel.birthDate.readAttribute(person));
        Assert.assertEquals("Gunnar", Person__Metamodel.firstName.readAttribute(person));
        Assert.assertEquals("Hilling", Person__Metamodel.lastName.readAttribute(person));
    }

    @Test
    public void writeAttributes() {
        Person__Metamodel.birthDate.writeAttribute(person, LocalDate.MIN);
        Person__Metamodel.firstName.writeAttribute(person, "Dennis");
        Person__Metamodel.lastName.writeAttribute(person, "Ritchie");
        Assert.assertEquals(LocalDate.MIN, person.getBirthDate());
        Assert.assertEquals("Dennis", person.getFirstName());
        Assert.assertEquals("Ritchie", person.getLastName());
    }
}
