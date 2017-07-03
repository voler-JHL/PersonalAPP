package com.voler.inject;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.voler.InjectField;
import com.voler.annotation.FieldInject;
import com.voler.utils.Consts;
import com.voler.utils.Logger;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

import static com.voler.utils.Consts.ANNOTATION_TYPE_FIELDINJECT;
import static com.voler.utils.Consts.PARCELABLE;
import static com.voler.utils.Consts.SERIALIZABLE;
import static com.voler.utils.Consts.STRING;
import static com.voler.utils.Consts.WARNING_TIPS;


@AutoService(Processor.class)
@SupportedAnnotationTypes(ANNOTATION_TYPE_FIELDINJECT)
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class FieldProcessor extends AbstractProcessor {


    private Elements elementUtils;
    private Filer filer;
    private Types typeUtils;
    private Logger logger;
    private Map<TypeElement, List<Element>> parentAndChild = new HashMap<>();

    /**
     * init()方法会被注解处理工具调用，并输入ProcessingEnviroment参数。
     * ProcessingEnviroment提供很多有用的工具类Elements, Types 和 Filer
     *
     * @param processingEnv 提供给 processor 用来访问工具框架的环境
     */
    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        elementUtils = processingEnv.getElementUtils();
        filer = processingEnv.getFiler();
        typeUtils = processingEnv.getTypeUtils();
        logger = new Logger(processingEnv.getMessager());
        logger.info(">>>>>>> FieldProcessor init");
    }

    /**
     * 这相当于每个处理器的主函数main()，你在这里写你的扫描、评估和处理注解的代码，以及生成Java文件。
     * 输入参数RoundEnviroment，可以让你查询出包含特定注解的被注解元素
     *
     * @param annotations 请求处理的注解类型
     * @param roundEnv    有关当前和以前的信息环境
     * @return 如果返回 true，则这些注解已声明并且不要求后续 Processor 处理它们；
     * 如果返回 false，则这些注解未声明并且可能要求后续 Processor 处理它们
     */
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (CollectionUtils.isEmpty(annotations)) return false;
        logger.info(">>>>>>>> processor start...");
        try {
            // roundEnv.getElementsAnnotatedWith()返回使用给定注解类型的元素
            categories(roundEnv.getElementsAnnotatedWith(FieldInject.class));
            generateHelper();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
        }
        return true;
    }

    /**
     * 这里必须指定，这个注解处理器是注册给哪个注解的。注意，它的返回值是一个字符串的集合，包含本处理器想要处理的注解类型的合法全称
     *
     * @return 注解器所支持的注解类型集合，如果没有这样的类型，则返回一个空集合
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotataions = new LinkedHashSet<String>();
        annotataions.add(FieldInject.class.getCanonicalName());
        return annotataions;
    }

    /**
     * 指定使用的Java版本，通常这里返回SourceVersion.latestSupported()，默认返回SourceVersion.RELEASE_6
     *
     * @return 使用的Java版本
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    /**
     * Categories field, find his papa.
     *
     * @param elements
     */
    private void categories(Set<? extends Element> elements) throws IllegalAccessException {
        if (CollectionUtils.isNotEmpty(elements)) {
            for (Element element : elements) {
                TypeElement enclosingElement = (TypeElement) element.getEnclosingElement();

                if (element.getModifiers().contains(Modifier.PRIVATE)) {
                    throw new IllegalAccessException("The autowired fields CAN NOT BE 'private'!!! please check field ["
                            + element.getSimpleName() + "] in class [" + enclosingElement.getQualifiedName() + "]");
                }

                if (parentAndChild.containsKey(enclosingElement)) { // Has categries
                    parentAndChild.get(enclosingElement).add(element);
                } else {
                    List<Element> childs = new ArrayList<>();
                    childs.add(element);
                    parentAndChild.put(enclosingElement, childs);
                }
            }

            logger.info("categories finished.");
        }
    }

    private void generateHelper() throws Exception {

        TypeMirror activityTm = elementUtils.getTypeElement(Consts.ACTIVITY).asType();
        TypeMirror fragmentTm = elementUtils.getTypeElement(Consts.FRAGMENT).asType();
        TypeMirror fragmentTmV4 = elementUtils.getTypeElement(Consts.FRAGMENT_V4).asType();

        //inject参数
        ParameterSpec parameterSpec = ParameterSpec.builder(TypeName.OBJECT, "target").build();
        if (MapUtils.isNotEmpty(parentAndChild)) {
            for (Map.Entry<TypeElement, List<Element>> entry : parentAndChild.entrySet()) {
                MethodSpec.Builder method = MethodSpec.methodBuilder("inject")
                        .addAnnotation(Override.class)
                        .addModifiers(Modifier.PUBLIC)
                        .addParameter(TypeName.OBJECT, "target");

                TypeElement classElement = entry.getKey();
                List<Element> fieldList = entry.getValue();
                String qualifiedName = classElement.getQualifiedName().toString();
                String packageName = qualifiedName.substring(0, qualifiedName.lastIndexOf("."));
                String fileName = classElement.getSimpleName() + "$$FieldInject";

                logger.info(">>> Start process " + fieldList.size() + " field in " + classElement.getSimpleName() + " ... <<<");

                TypeSpec.Builder typeSpec = TypeSpec.classBuilder(fileName)
                        .addJavadoc(WARNING_TIPS)
                        .addModifiers(Modifier.PUBLIC)
                        .addSuperinterface(ClassName.get(InjectField.class));

                method.addStatement("$T substitute = ($T)target", ClassName.get(classElement), ClassName.get(classElement));
                boolean isActivity = false;
                if (typeUtils.isSubtype(classElement.asType(), activityTm)) {
                    isActivity = true;
                    method.addStatement("$T injects = substitute.getIntent()",ClassName.get("android.content", "Intent"));
                } else if (typeUtils.isSubtype(classElement.asType(), fragmentTm) || typeUtils.isSubtype(classElement.asType(), fragmentTmV4)) {
                    method.addStatement("$T injects = substitute.getArguments()",ClassName.get("android.os", "Bundle"));
                } else {
                    throw new IllegalAccessException(fileName + "is not activity or fragment! !");
                }
                for (Element element : fieldList) {
                    FieldInject fieldInject = element.getAnnotation(FieldInject.class);
                    String fieldName = element.getSimpleName().toString();
                    String statment = "substitute." + fieldName + " = injects.";


                    statment = buildStatement(statment, element, isActivity);
                    method.addStatement(statment, StringUtils.isEmpty(fieldInject.value()) ? fieldName : fieldInject.value());
                }
                typeSpec.addMethod(method.build());

                JavaFile.builder(packageName, typeSpec.build()).build().writeTo(filer);
            }
            logger.info(">>> FieldInject processor stop. <<<");
        }
    }

    private String buildStatement(String statment, Element element, boolean isActivity) {
        TypeMirror typeMirror = element.asType();
        TypeKind type = typeMirror.getKind();

        TypeMirror parcelableType = elementUtils.getTypeElement(PARCELABLE).asType();
        TypeMirror serializableType = elementUtils.getTypeElement(SERIALIZABLE).asType();
        TypeMirror stringType = elementUtils.getTypeElement(STRING).asType();

        if (type == TypeKind.BOOLEAN) {
            statment += (isActivity ? ("getBooleanExtra($S, false)") : ("getBoolean($S)"));
        } else if (type == TypeKind.BYTE) {
            statment += (isActivity ? ("getByteExtra($S, (byte) 0)") : ("getByte($S)"));
        } else if (type == TypeKind.SHORT) {
            statment += (isActivity ? ("getShortExtra($S, (short) 0)") : ("getShort($S)"));
        } else if (type == TypeKind.INT) {
            statment += (isActivity ? ("getIntExtra($S, 0)") : ("getInt($S)"));
        } else if (type == TypeKind.LONG) {
            statment += (isActivity ? ("getLongExtra($S, 0)") : ("getLong($S)"));
        } else if (type == TypeKind.FLOAT) {
            statment += (isActivity ? ("getFloatExtra($S, 0)") : ("getFloat($S)"));
        } else if (type == TypeKind.DOUBLE) {
            statment += (isActivity ? ("getDoubleExtra($S, 0)") : ("getDouble($S)"));
        } else if (typeUtils.isSameType(typeMirror, stringType)) {
            statment += (isActivity ? ("getStringExtra($S)") : ("getString($S)"));
        } else if (typeUtils.isSubtype(typeMirror, parcelableType)) {
            statment += (isActivity ? ("getParcelableExtra($S)") : ("getParcelable($S)"));
        } else if (typeUtils.isSubtype(typeMirror, serializableType)) {
            statment += (isActivity ? ("getSerializableExtra($S)") : ("getSerializable($S)"));
        }

        return statment;
    }
}
