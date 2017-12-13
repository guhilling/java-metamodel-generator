package de.hilling.lang.metamodel;

import java.util.List;

/**
 * Base class for generated static meta models.
 */
public interface MetaModel {

    List<Attribute> getAttributes();
}
