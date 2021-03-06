package com.voler.utils;

/**
 * Some consts used in processors
 */
public class Consts {
    // Generate
    public static final String SEPARATOR = "$$";
    public static final String PROJECT = "Saber";
    public static final String TAG = PROJECT + "::";
    public static final String WARNING_TIPS = "DO NOT EDIT THIS FILE!!! IT WAS GENERATED BY FieldInject.";
    public static final String METHOD_LOAD_INTO = "loadInto";
    public static final String METHOD_INJECT = "inject";

    // System interface
    public static final String ACTIVITY = "android.app.Activity";
    public static final String FRAGMENT = "android.app.Fragment";
    public static final String FRAGMENT_V4 = "android.support.v4.app.Fragment";
    public static final String SERVICE = "android.app.Service";
    public static final String PARCELABLE = "android.os.Parcelable";
    public static final String SERIALIZABLE = "java.io.Serializable";

    // Java type
    private static final String LANG = "java.lang";
    public static final String BYTE = LANG + ".Byte";
    public static final String SHORT = LANG + ".Short";
    public static final String INTEGER = LANG + ".Integer";
    public static final String LONG = LANG + ".Long";
    public static final String FLOAT = LANG + ".Float";
    public static final String DOUBEL = LANG + ".Double";
    public static final String BOOLEAN = LANG + ".Boolean";
    public static final String STRING = LANG + ".String";
    public static final String LIST = "java.util.List";
    public static final String ARRAYLIST = "java.util.ArrayList";

    // package
    private static final String PACKAGE = "com.voler";

    // Log
    static final String PREFIX_OF_LOGGER = PROJECT + "::Compiler ";


    // Annotation type
    public static final String ANNOTATION_TYPE_REPORTEVENT = PACKAGE + ".annotation.ReportEvent";
    public static final String ANNOTATION_TYPE_REPORTPARAMETER = PACKAGE + ".annotation.ReportParameter";
    public static final String ANNOTATION_TYPE_FIELDINJECT = PACKAGE + ".annotation.FieldInject";
    public static final String ANNOTATION_TYPE_INJECTFIELD = PACKAGE + ".annotation.InjectField";
}