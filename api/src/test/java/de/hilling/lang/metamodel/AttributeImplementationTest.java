package de.hilling.lang.metamodel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.hilling.lang.metamodel.AttributeImplementation;

class AttributeImplementationTest {

    private SampleBean sampleBean;
    private TestImplementation nameAttribute;

    @BeforeEach
    void setup() {
        sampleBean = new SampleBean();
        sampleBean.setName("testname");
        nameAttribute = new TestImplementation();
    }

    @Test
    void createAttirubteImplementation() {
        assertEquals("testname", nameAttribute.readAttribute(sampleBean));
    }

    static class TestImplementation extends AttributeImplementation<SampleBean, String> {

        public TestImplementation() {
            super("name", SampleBean.class, String.class);
        }
        @Override
        public String readAttribute(SampleBean bean) {
            return bean.getName();
        }
    }
}