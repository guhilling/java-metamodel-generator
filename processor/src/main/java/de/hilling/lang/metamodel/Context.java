package de.hilling.lang.metamodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.processing.ProcessingEnvironment;

/**
 * Verarbeitungskontext.
 */
public class Context {
    private final ProcessingEnvironment env;
    private final Map<String, AttributeInfo> attributes    = new HashMap<>();
    private final List<String>               attributeList = new ArrayList<>();

    public Context(ProcessingEnvironment env) {
        this.env = env;
    }

    public AttributeInfo getInfo(String attributeName) {
        if(!attributes.containsKey(attributeName)) {
            attributes.put(attributeName, new AttributeInfo());
            attributeList.add(attributeName);
        }
        return attributes.get(attributeName);
    }

    public ProcessingEnvironment getEnvironment() {
        return env;
    }

    public void clear() {
        attributes.clear();
        attributeList.clear();
    }

    public List<String> attributes() {
        return attributeList;
    }

}
