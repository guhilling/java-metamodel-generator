package de.hilling.lang.metamodel;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

/**
 * Create meta model class for given Type.
 */
class MetaClassWriter {

    private static final String SUFFIX           = "__Metamodel";
    private static final String ATTRIBUTES_FIELD = "ATTRIBUTES";

    private final TypeElement beanType;
    private final ClassModel  classModel;
    private final String      metaClassName;

    /**
     * Initialize class with {@link TypeElement} and {@link ClassModel} containing attributes.
     * @param beanType the bean class.
     * @param classModel attribute informations about the bean class.
     */
    MetaClassWriter(TypeElement beanType, ClassModel classModel) {
        this.beanType = beanType;
        this.classModel = classModel;
        metaClassName = beanType.getSimpleName() + SUFFIX;
    }

    /**
     * Create the Metamodel class.
     * @throws IOException if source file cannot be written.
     */
    void invoke() throws IOException {
        TypeSpec.Builder classBuilder = TypeSpec.classBuilder(metaClassName)
                                                .addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT);
        classBuilder.addField(createAttributeListField());
        classModel.attributes().forEach((name, type) -> classBuilder.addField(createFieldSpec(name, type)));

        classBuilder.addMethod(MethodSpec.methodBuilder("attributes")
                                         .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                                         .returns(ParameterizedTypeName.get(List.class, Attribute.class))
                                         .addStatement("return $L", ATTRIBUTES_FIELD)
                                         .build());
        classBuilder.addStaticBlock(createStaticInitializerBlock());

        JavaFile javaFile = JavaFile.builder(ClassName.get(beanType).packageName(), classBuilder.build()).indent("    ")
                                    .build();
        javaFile.writeTo(classModel.getEnvironment().getFiler());
    }

    private FieldSpec createAttributeListField() {
        return FieldSpec.builder(ParameterizedTypeName.get(List.class, Attribute.class), ATTRIBUTES_FIELD)
                        .addModifiers(Modifier.PRIVATE, Modifier.STATIC, Modifier.FINAL).build();
    }

    private CodeBlock createStaticInitializerBlock() {
        final CodeBlock.Builder builder = CodeBlock.builder();
        classModel.attributes().forEach((name, type) -> builder.add(new InitializerBuilder(beanType, name, type).invoke()));
        builder.addStatement("$T<$T> attributesList = new $T<>()", List.class, Attribute.class, LinkedList.class);
        for (String attributeName : classModel.names()) {
            builder.addStatement("attributesList.add($L)", attributeName);
        }
        builder.addStatement("$L = $T.unmodifiableList(attributesList)", ATTRIBUTES_FIELD, Collections.class);
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
        final Class<?> declaredClass;
        if (info.isWritable()) {
            declaredClass = MutableAttribute.class;
        } else {
            declaredClass = Attribute.class;
        }
        return ParameterizedTypeName.get(ClassName.get(declaredClass), classTypeName, attributeTypeName);
    }
}
