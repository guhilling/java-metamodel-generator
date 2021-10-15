package de.hilling.lang.metamodel;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

/**
 * Creates the initializers for the attributes.
 * <p>
 *     This class generates the code for the getter and setter access.
 * </p>
 */
class InitializerBuilder {
    private static final String BEAN_PARAMETER_NAME = "object";

    private final String        name;
    private final TypeName      attributeTypeName;
    private final TypeName      classTypeName;
    private final AttributeInfo info;

    InitializerBuilder(TypeElement beanType, String name, AttributeInfo info) {
        this.name = name;
        this.info = info;
        attributeTypeName = TypeName.get(info.getType());
        classTypeName = TypeName.get(beanType.asType());
    }

    CodeBlock invoke() {
        final CodeBlock.Builder builder = CodeBlock.builder();
        builder.addStatement("$N = $L", name, createAttributeImplementation());
        return builder.build();
    }

    private TypeSpec createAttributeImplementation() {
        final ParameterizedTypeName superinterface = implementationTypeName();
        final String camelName = Utils.firstToUpper(name);
        final List<MethodSpec> methods = new ArrayList<>();
        methods.add(
        MethodSpec.methodBuilder("readAttribute").addAnnotation(Override.class).addModifiers(Modifier.PUBLIC)
                  .addParameter(classTypeName, BEAN_PARAMETER_NAME).returns(attributeTypeName)
                  .addStatement("return $N.get$L()", BEAN_PARAMETER_NAME, camelName).build());
        if (info.isWritable()) {
            methods.add(
            MethodSpec.methodBuilder("writeAttribute").addAnnotation(Override.class).addModifiers(Modifier.PUBLIC)
                      .addParameter(classTypeName, BEAN_PARAMETER_NAME).addParameter(attributeTypeName, "value")
                      .addStatement("$N.set$L($N)", BEAN_PARAMETER_NAME, camelName, "value").build());
        }
        return TypeSpec.anonymousClassBuilder("$S, $T.class, $T.class", name, classTypeName, attributeTypeName)
                       .addSuperinterface(superinterface).addMethods(methods).build();
    }

    private ParameterizedTypeName implementationTypeName() {
        final Class<?> implementationClass;
        if (info.isWritable()) {
            implementationClass = MutableAttributeImplementation.class;
        } else {
            implementationClass = AttributeImplementation.class;
        }
        return ParameterizedTypeName.get(ClassName.get(implementationClass), classTypeName, attributeTypeName);
    }
}
