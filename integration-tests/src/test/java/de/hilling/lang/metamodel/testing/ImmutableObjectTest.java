package de.hilling.lang.metamodel.testing;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ImmutableObjectTest {

    private ImmutableObject object;

    @Before
    public void setUp() {
        object = new ImmutableObject("Duke");
    }

    @Test
    public void readAttributes() {
        assertEquals("Duke", ImmutableObject__Metamodel.name.readAttribute(object));
    }
}
