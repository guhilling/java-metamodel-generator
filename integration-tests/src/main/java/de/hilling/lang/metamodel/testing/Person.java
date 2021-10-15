package de.hilling.lang.metamodel.testing;

import java.time.LocalDate;

import de.hilling.lang.metamodel.GenerateModel;

/**
 * Example for standard java bean with different data types.
 */
@GenerateModel
public class Person {
    private String    firstName;
    private String    lastName;
    private LocalDate birthDate;

    /**
     * Should be ignored by processor (no getter).
     *
     * @param uppercase whether the name should be rendered in uppercase.
     * @return first and last name.
     */
    public String getName(boolean uppercase) {
        final String name = firstName + " " + lastName;
        if (uppercase) {
            return name.toUpperCase();
        } else {
            return name;
        }
    }

    /**
     * Should be ignored by processor (no setter).
     * @param value value to set.
     * @param uppercase whether the name should be rendered in uppercase.
     */
    public void setName(String value, boolean uppercase) {
        // only for processor-testing.
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
