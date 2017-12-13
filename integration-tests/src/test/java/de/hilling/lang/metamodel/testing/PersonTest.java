package de.hilling.lang.metamodel.testing;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.Month;

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
        assertEquals(BIRTH_DATE, Person__Metamodel.birthDate.readAttribute(person));
        assertEquals("Gunnar", Person__Metamodel.firstName.readAttribute(person));
        assertEquals("Hilling", Person__Metamodel.lastName.readAttribute(person));
    }

    @Test
    public void checkAttributes() {
        assertEquals(Person.class, Person__Metamodel.birthDate.getDeclaringType());
        assertEquals(LocalDate.class, Person__Metamodel.birthDate.getJavaType());
        assertEquals("birthDate", Person__Metamodel.birthDate.getName());
    }

    @Test
    public void writeAttributes() {
        Person__Metamodel.birthDate.writeAttribute(person, LocalDate.MIN);
        Person__Metamodel.firstName.writeAttribute(person, "Dennis");
        Person__Metamodel.lastName.writeAttribute(person, "Ritchie");
        assertEquals(LocalDate.MIN, person.getBirthDate());
        assertEquals("Dennis", person.getFirstName());
        assertEquals("Ritchie", person.getLastName());
    }
}
