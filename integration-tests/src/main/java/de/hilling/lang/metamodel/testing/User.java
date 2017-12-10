package de.hilling.lang.metamodel.testing;

import java.time.LocalDate;

import de.hilling.lang.metamodel.MetaModel;

@MetaModel
public class User {
    private String    firstName;
    private String    lastName;
    private LocalDate birthDate;

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
