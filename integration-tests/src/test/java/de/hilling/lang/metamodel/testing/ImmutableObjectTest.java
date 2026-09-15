package de.hilling.lang.metamodel.testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ImmutableObjectTest {

    private ImmutableObject object;

    @BeforeEach
    public void setUp() {
        object = new ImmutableObject("Duke");
    }

    @Test
    public void readAttributes() {
        assertEquals("Duke", ImmutableObject__Metamodel.name.readAttribute(object));
    }
}
