package de.hilling.lang.metamodel;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

    @Test
    void checkCorrectInfo() {
        assertEquals("name", nameAttribute.getName());
        assertEquals(String.class, nameAttribute.getJavaType());
        assertEquals(SampleBean.class, nameAttribute.getDeclaringType());
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