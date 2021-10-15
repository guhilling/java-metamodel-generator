[![Build CDI Test](https://github.com/guhilling/java-metamodel-generator/actions/workflows/maven.yml/badge.svg)](https://github.com/guhilling/java-metamodel-generator/actions/workflows/maven.yml)
[![CodeQL](https://github.com/guhilling/java-metamodel-generator/actions/workflows/codeql-analysis.yml/badge.svg)](https://github.com/guhilling/java-metamodel-generator/actions/workflows/codeql-analysis.yml)
[![Coverage (Sonar)](https://sonarcloud.io/api/project_badges/measure?project=de.hilling.lang.metamodel%3Ametamodel-generator&metric=coverage)](https://sonarcloud.io/dashboard?id=de.hilling.lang.metamodel%3Ametamodel-generator)
[![Status (Sonar)](https://sonarcloud.io/api/project_badges/measure?project=de.hilling.lang.metamodel%3Ametamodel-generator&metric=alert_status)](https://sonarcloud.io/dashboard?id=de.hilling.lang.metamodel%3Ametamodel-generator)
[![Maintainability (Sonar)](https://sonarcloud.io/api/project_badges/measure?project=de.hilling.lang.metamodel%3Ametamodel-generator&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=de.hilling.lang.metamodel%3Ametamodel-generator)
[![Maven Central](https://img.shields.io/maven-central/v/de.hilling.lang.metamodel/metamodel-generator.svg)](http://search.maven.org/#search|gav|1|g:"de.hilling.lang.metamodel"%20AND%20a:"metamodel-generator")
# Static Metamodel Generator

## Introduction

Sometimes you'd like to have a _real_, typesafe metamodel of you java classes.
This Library (metamodel-generator) provides an annotation-processor that will create such a metamodel.

## Setup

First you'll need to include the api-artifact as a compile-time dependency and the processor as a provided dependency into your project:
```xml
    <dependency>
        <groupId>de.hilling.lang.metamodel</groupId>
        <artifactId>api</artifactId>
        <version>1.5</version>
    </dependency>
    <dependency>
        <groupId>de.hilling.lang.metamodel</groupId>
        <artifactId>processor</artifactId>
        <version>1.5</version>
        <scope>provided</scope>
    </dependency>
```
This will automatically run the annotation-process during compilation.
You may also want to read this [Blog Post](https://jax.de/blog/core-java-jvm-languages/java-annotation-processing-das-koennte-auch-ein-computer-erledigen/)
(in German) and refer to the examples in the ```integration-tests``` module.

## Usage

Just annotate the classes that need a metamodel with the ```@GenerateModel``` annotation:

```java
@GenerateModel
public class Car {
    private       String model;
    private final int    year;

    public Car(int year) {
        this.year = year;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public int getYear() {
        return year;
    }
}
```

For the above example the following metamodel class will be generated:

```java
public abstract class Car__Metamodel {
    private static final List<Attribute> ATTRIBUTES;

    public static final Attribute<Car, Integer> year;

    public static final MutableAttribute<Car, String> model;

    static {
        [...]
    }

    public static List<Attribute> attributes() {
        return ATTRIBUTES;
    }
}
```

And you can query as follows (snippet from ```CarTest.java```:

```java
    @Test
    public void readAttributes() {
        car = new Car(1974);
        car.setModel("Golf");

        assertEquals("Golf", Car__Metamodel.model.readAttribute(car));
        assertEquals((Integer) 1974, Car__Metamodel.year.readAttribute(car));

        Car__Metamodel.model.writeAttribute(car, "Polo");
        assertEquals("Polo", car.getModel());
    }
```

You can also inspect dynamically using the ```getAttributes()``` method which is kind of the point of the whole processor!
The original order of the attributes is retained in the returned attributes list.

## Possible enhancements

* Better support for primites: They are just auto-boxed.
* Access attributes via map.
* Support for generic method-inspection.
* General infos about class structure.

Most of these things would be easy to do. Feel free to create a pull request!