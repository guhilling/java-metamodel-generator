package de.hilling.lang.metamodel.testing;

import org.junit.Assert;
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
        Assert.assertEquals("Duke", ImmutableObject__Metamodel.name.readAttribute(object));

    }
}
