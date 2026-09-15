package de.hilling.lang.metamodel.testing;

import static de.hilling.lang.metamodel.testing.Person__Metamodel.birthDate;
import static de.hilling.lang.metamodel.testing.Person__Metamodel.firstName;
import static de.hilling.lang.metamodel.testing.Person__Metamodel.lastName;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.hilling.lang.metamodel.Attribute;

class PersonTest {

    private static final LocalDate BIRTH_DATE = LocalDate.of(1971, Month.JUNE, 15);
    private Person person;

    @BeforeEach
    void setUp() {
        person = new Person();
        person.setFirstName("Gunnar");
        person.setLastName("Hilling");
        person.setBirthDate(BIRTH_DATE);
    }

    @Test
    void readAttributes() {
        assertEquals(BIRTH_DATE, birthDate.readAttribute(person));
        assertEquals("Gunnar", firstName.readAttribute(person));
        assertEquals("Hilling", lastName.readAttribute(person));
    }

    @Test
    void checkAttributes() {
        assertEquals(Person.class, birthDate.getDeclaringType());
        assertEquals(LocalDate.class, birthDate.getJavaType());
        assertEquals("birthDate", birthDate.getName());
    }

    @Test
    void verifyAttributeListOrder() {
        final List<Attribute> attributes = Person__Metamodel.attributes();
        assertEquals(3, attributes.size());
        assertEquals(firstName, attributes.get(0));
        assertEquals(lastName, attributes.get(1));
        assertEquals(birthDate, attributes.get(2));
    }

    @Test
    void writeAttributes() {
        birthDate.writeAttribute(person, LocalDate.MIN);
        firstName.writeAttribute(person, "Dennis");
        lastName.writeAttribute(person, "Ritchie");
        assertEquals(LocalDate.MIN, person.getBirthDate());
        assertEquals("Dennis", person.getFirstName());
        assertEquals("Ritchie", person.getLastName());
    }
}
