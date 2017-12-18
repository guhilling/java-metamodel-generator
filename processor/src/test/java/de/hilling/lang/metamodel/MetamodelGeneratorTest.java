package de.hilling.lang.metamodel;

import static com.google.common.truth.Truth.assertAbout;
import static com.google.common.truth.Truth.assertThat;
import static com.google.testing.compile.CompilationSubject.compilations;
import static com.google.testing.compile.Compiler.javac;

import javax.tools.JavaFileObject;

import org.junit.Before;
import org.junit.Test;

import com.google.testing.compile.Compilation;
import com.google.testing.compile.Compiler;
import com.google.testing.compile.JavaFileObjects;

public class MetamodelGeneratorTest {

    private Compiler compiler;

    @Before
    public void setUpCompiler() {
        compiler = javac().withProcessors(new MetamodelVerifier(), new MetamodelGenerator());
    }

    @Test
    public void generateWritableBean() {
        Compilation compilation = compiler.compile(source(SimpleObject.class));

        assertAbout(compilations()).that(compilation)
                                   .generatedSourceFile("de/hilling/lang/metamodel/SimpleObject__Metamodel")
                                   .hasSourceEquivalentTo(source(SimpleObject__Metamodel.class));
        assertThat(compilation.status()).isEqualTo(Compilation.Status.SUCCESS);
    }

    @Test
    public void generateReadOnlyBean() {
        Compilation compilation = compiler.compile(source(ImmutableObject.class));

        assertAbout(compilations()).that(compilation)
                                   .generatedSourceFile("de/hilling/lang/metamodel/ImmutableObject__Metamodel")
                                   .hasSourceEquivalentTo(source(ImmutableObject__Metamodel.class));
        assertThat(compilation.status()).isEqualTo(Compilation.Status.SUCCESS);
    }

    @Test
    public void failOnIllegalAnnotation() {
        final JavaFileObject illegalSource = source(IllegallyUsedAnnotation.class);
        Compilation compilation = compiler.compile(illegalSource);

        assertAbout(compilations()).that(compilation)
                                   .hadErrorContaining(MetamodelVerifier.ERROR_MESSAGE)
                                   .inFile(illegalSource)
                                   .onLine(3)
                                   .atColumn(1);
        assertThat(compilation.status()).isEqualTo(Compilation.Status.FAILURE);
    }

    private JavaFileObject source(Class<?> clazz) {
        return JavaFileObjects.forResource(clazz.getCanonicalName().replace('.', '/') + ".java");
    }
}
