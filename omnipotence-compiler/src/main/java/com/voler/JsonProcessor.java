package com.voler;

import com.voler.annotation.FieldInject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;

/**
 * 三尺春光驱我寒，一生戎马为长安
 * Created by Han on 17/5/18.
 */

public class JsonProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
//        ExecutableElement;
//            PackageElement;
//            UnknownElementException;
//            VariableElement;
        Set<? extends Element> elementsAnnotatedWith = roundEnvironment.getElementsAnnotatedWith(FieldInject.class);
        for (Element element : elementsAnnotatedWith) {

            // 判断元素的类型为Class
            if (element.getKind() == ElementKind.CLASS) {
                // 显示转换元素类型
                TypeElement typeElement = (TypeElement) element;
                // 输出元素名称
                System.out.println(typeElement.getSimpleName());
                // 输出注解属性值
                System.out.println(typeElement.getAnnotation(FieldInject.class).value());
            }
        }
        File dir = new File("f://apt_test");
        if (!dir.exists())
            dir.mkdirs();

        // 创建文件
        File file = new File(dir, "anno.txt");
        try {
            /**
             * 编写json文件内容
             */
            FileWriter fw = new FileWriter(file);
            fw.append("{").append("class:").append("\"" + 666 + "\"")
                    .append(",\n ");
            fw.append("fields:\n {\n");
            fw.append("\n }\n");
            fw.append("}");
            fw.flush();
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
