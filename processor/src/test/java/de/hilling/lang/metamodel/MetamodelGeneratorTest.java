package de.hilling.lang.metamodel;

import static com.google.common.truth.Truth.assertAbout;
import static com.google.common.truth.Truth.assertThat;
import static com.google.testing.compile.CompilationSubject.compilations;
import static com.google.testing.compile.Compiler.javac;

import javax.tools.JavaFileObject;

import org.junit.Test;

import com.google.testing.compile.Compilation;
import com.google.testing.compile.Compiler;
import com.google.testing.compile.JavaFileObjects;

public class MetamodelGeneratorTest {

    public static final String EXPECTED_PACKAGE = SimpleObject.class.getPackage().getName();

    @Test
    public void validateValidObject() {
        final Compiler compiler = javac().withProcessors(new MetamodelGenerator());
        Compilation compilation = compiler.compile(source(SimpleObject.class));

        /*
        final Optional<JavaFileObject> generatedFile = compilation.generatedFile(SOURCE_OUTPUT, EXPECTED_PACKAGE,
                                                                                  SimpleObject__Metamodel.class
                                                                                  .getSimpleName());
                                                                                  */
        assertAbout(compilations()).that(compilation)
                                   .generatedSourceFile("de/hilling/lang/metamodel/SimpleObject__Metamodel")
                                   .hasSourceEquivalentTo(JavaFileObjects.forResource
                                                                          ("SimpleObject__Metamodel.java"));
        assertThat(compilation.status()).isEqualTo(Compilation.Status.SUCCESS);
        //assertAbout(compilations()).that(compilation).ha;
        //assertThat(generatedFile).isPresent();
        //assertThat(compilation.generatedSourceFile(SimpleObject__Metamodel.class.getName()))
    }

    private JavaFileObject source(Class<?> clazz) {
        return JavaFileObjects.forResource(clazz.getCanonicalName().replace('.', '/') + ".java");
    }
}
