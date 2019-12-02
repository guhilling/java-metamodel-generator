package de.hilling.lang.metamodel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * Activate the Metamodel Processor to generate a static metamodel
 * similar to the JPA 2 Metamodel for the annotated class.
 * </p><p>
 * See documentation for further details.
 * </p><p>
 * Runtime Retention is not necessary for the generator but enables You
 * to use reflection to check for a Metamodel.
 * </p>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface GenerateModel {
}
