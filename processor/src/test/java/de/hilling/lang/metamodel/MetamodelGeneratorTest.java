package de.hilling.lang.metamodel;

import static com.google.testing.compile.Compiler.javac;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Optional;

import javax.tools.Diagnostic;
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

        assertGeneratedSourceEquals(compilation, SimpleObject__Metamodel.class);
        assertEquals(Compilation.Status.SUCCESS, compilation.status());
    }

    @Test
    public void generateReadOnlyBean() {
        Compilation compilation = compiler.compile(source(ImmutableObject.class));

        assertGeneratedSourceEquals(compilation, ImmutableObject__Metamodel.class);
        assertEquals(Compilation.Status.SUCCESS, compilation.status());
    }

    @Test
    public void failOnIllegalAnnotation() {
        final JavaFileObject illegalSource = source(IllegallyUsedAnnotation.class);
        Compilation compilation = compiler.compile(illegalSource);

        Diagnostic<? extends JavaFileObject> error = compilation.errors().stream()
                                                                  .filter(diagnostic -> diagnostic.getMessage(null)
                                                                                                  .contains(MetamodelVerifier.ERROR_MESSAGE))
                                                                  .findFirst()
                                                                  .orElse(null);
        assertNotNull(error);
        assertEquals(illegalSource.toUri(), error.getSource().toUri());
        assertEquals(3, error.getLineNumber());
        assertEquals(1, error.getColumnNumber());
        assertEquals(Compilation.Status.FAILURE, compilation.status());
    }

    private JavaFileObject source(Class<?> clazz) {
        return JavaFileObjects.forResource(clazz.getCanonicalName().replace('.', '/') + ".java");
    }

    private void assertGeneratedSourceEquals(Compilation compilation, Class<?> expectedSourceClass) {
        Optional<JavaFileObject> generatedSource = compilation.generatedSourceFile(expectedSourceClass.getCanonicalName());
        assertEquals(compilation.toString(), true, generatedSource.isPresent());
        assertEquals(normalize(readSource(source(expectedSourceClass))), normalize(readSource(generatedSource.orElseThrow())));
    }

    private String readSource(JavaFileObject file) {
        try {
            return file.getCharContent(false).toString();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private String normalize(String source) {
        return source.replaceAll("\\s+", "");
    }
}
