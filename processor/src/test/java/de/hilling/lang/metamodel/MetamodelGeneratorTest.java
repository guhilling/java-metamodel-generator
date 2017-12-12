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

    @Test
    public void generateWritableBean() {
        final Compiler compiler = javac().withProcessors(new MetamodelGenerator());
        Compilation compilation = compiler.compile(source(SimpleObject.class));

        assertAbout(compilations()).that(compilation)
                                   .generatedSourceFile("de/hilling/lang/metamodel/SimpleObject__Metamodel")
                                   .hasSourceEquivalentTo(source(SimpleObject__Metamodel.class));
        assertThat(compilation.status()).isEqualTo(Compilation.Status.SUCCESS);
    }

    @Test
    public void generateReadOnlyBean() {
        final Compiler compiler = javac().withProcessors(new MetamodelGenerator());
        Compilation compilation = compiler.compile(source(ImmutableObject.class));

        assertAbout(compilations()).that(compilation)
                                   .generatedSourceFile("de/hilling/lang/metamodel/ImmutableObject__Metamodel")
                                   .hasSourceEquivalentTo(source(ImmutableObject__Metamodel.class));
        assertThat(compilation.status()).isEqualTo(Compilation.Status.SUCCESS);
    }

    private JavaFileObject source(Class<?> clazz) {
        return JavaFileObjects.forResource(clazz.getCanonicalName().replace('.', '/') + ".java");
    }
}
