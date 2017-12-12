package de.hilling.lang.metamodel;

import java.io.IOException;

import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

/**
 * Create meta model class for given Type.
 */
class MetaClassWriter {

    private static final String SUFFIX              = "__Metamodel";

    private final TypeElement beanType;
    private final Context     context;
    private final String      metaClassName;

    /**
     * Initialize class with {@link TypeElement} and {@link Context} containing attributes.
     * @param beanType the bean class.
     * @param context attribute informations about the bean class.
     */
    MetaClassWriter(TypeElement beanType, Context context) {
        this.beanType = beanType;
        this.context = context;
        metaClassName = beanType.getSimpleName() + SUFFIX;
    }

    /**
     * Create the Metamodel class.
     * @throws IOException if source file cannot be written.
     */
    void invoke() throws IOException {
        TypeSpec.Builder classBuilder = TypeSpec.classBuilder(metaClassName)
                                                .addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT);
        context.attributes().forEach((name, type) -> classBuilder.addField(createFieldSpec(name, type)));
        classBuilder.addStaticBlock(createStaticInitializerBlock());

        JavaFile javaFile = JavaFile.builder(ClassName.get(beanType).packageName(), classBuilder.build()).indent("    ")
                                    .build();
        javaFile.writeTo(context.getEnvironment().getFiler());
    }

    private CodeBlock createStaticInitializerBlock() {
        final CodeBlock.Builder builder = CodeBlock.builder();
        context.attributes().forEach((name, type) -> builder.add(new InitializerBuilder(beanType, name, type).invoke()));
        return builder.build();
    }

    private FieldSpec createFieldSpec(String attributeName, AttributeInfo info) {
        final ParameterizedTypeName fieldType = declarationTypeName(info);
        return FieldSpec.builder(fieldType, attributeName)
                        .addModifiers(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL).build();
    }

    private ParameterizedTypeName declarationTypeName(AttributeInfo info) {
        final TypeName attributeTypeName = TypeName.get(info.getType());
        final TypeName classTypeName = TypeName.get(beanType.asType());
        final Class declaredClass;
        if (info.isWritable()) {
            declaredClass = Attribute.class;
        } else {
            declaredClass = ReadOnlyAttribute.class;
        }
        return ParameterizedTypeName.get(ClassName.get(declaredClass), classTypeName, attributeTypeName);
    }
}
