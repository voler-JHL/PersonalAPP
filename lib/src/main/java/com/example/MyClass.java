package com.example;

import java.io.File;

public class MyClass {
    public static void main(String[] args) {

        File directory = new File("");
        String absolutePath = directory.getAbsolutePath();

        File file = new File(absolutePath);
        if (file.isDirectory()) {
            for (String s : file.list()) {
                File oldFile = new File(s);

                System.out.println(oldFile.getAbsolutePath());
                String path = oldFile.getPath();


//                String extension = path.substring(index);

                if (!path.contains("@2x")) continue;
                path = path.replace("@2x", "");
                path = camel2Underline(path);

                File uiDir = new File("ui");
                if (!uiDir.exists() || !uiDir.isDirectory()) {
                    uiDir.mkdir();
                }

                File newFile = new File(uiDir, path);

                if (oldFile.renameTo(newFile)) {
                    System.out.println("修改成功!  to：" + newFile.getAbsolutePath());
                } else {
                    System.out.println("修改失败");
                }
            }
        }
    }


    public static String camel2Underline(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c)) {
                if (i != 0) {
                    sb.append("_");
                }
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
