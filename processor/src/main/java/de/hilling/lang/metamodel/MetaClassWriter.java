package de.hilling.lang.metamodel;

import java.io.IOException;
import java.io.Writer;

import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

public class MetaClassWriter {

    private static final String SUFFIX = "__Metamodel";

    private final TypeElement element;
    private final Context     context;
    private final String      metaClassName;
    private final Types       typeUtils;
    private final Elements    elementUtils;
    private       Writer      writer;

    public MetaClassWriter(TypeElement element, Context context) {
        this.element = element;
        this.context = context;
        metaClassName = element.getSimpleName() + SUFFIX;
        typeUtils = context.getEnvironment().getTypeUtils();
        elementUtils = context.getEnvironment().getElementUtils();
    }

    public void invoke() throws IOException {
        TypeSpec.Builder classBuilder = TypeSpec.classBuilder(metaClassName)
                                                .addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT);
        context.attributes().forEach(a -> classBuilder.addField(createFieldSpec(a)));

        classBuilder.addStaticBlock(createStaticInitializerBlock());

        JavaFile javaFile = JavaFile.builder(ClassName.get(element).packageName(), classBuilder.build()).build();
        javaFile.writeTo(context.getEnvironment().getFiler());
    }

    private CodeBlock createStaticInitializerBlock() {
        final CodeBlock.Builder builder = CodeBlock.builder();
        context.attributes().forEach(a -> builder.add(createInitializerBlock(a)));
        return builder.build();
    }

    private String createInitializerBlock(String attribute) {
        return attribute + " = null;";
    }

    private FieldSpec createFieldSpec(String attributeName) {
        final AttributeInfo info = context.getInfo(attributeName);
        return FieldSpec.builder(TypeName.get(info.getType()), attributeName)
                        .addModifiers(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL).build();
    }
}
