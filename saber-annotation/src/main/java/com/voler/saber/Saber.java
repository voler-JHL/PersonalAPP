package com.voler.saber;

import com.voler.inject.InjectField;

import java.util.LinkedHashMap;
import java.util.Map;


public final class Saber {
    private Saber() {
        throw new AssertionError("No instances.");
    }

    private static final String TAG = "Saber";
    private static boolean debug = false;

    static final Map<Class<?>, InjectField> BINDERS = new LinkedHashMap<>();
    static final InjectField NOP_VIEW_BINDER = new InjectField() {

        @Override
        public void inject(Object target) {

        }
    };

    /**
     * Control whether debug logging is enabled.
     */
    public static void setDebug(boolean debug) {
        Saber.debug = debug;
    }


    public static void inject(Object target) {
        Class<?> targetClass = target.getClass();
        try {
            InjectField viewBinder = findViewBinderForClass(targetClass);
            viewBinder.inject(target);
        } catch (Exception e) {
            throw new RuntimeException("Unable to bind views for " + targetClass.getName(), e);
        }
    }

    private static InjectField findViewBinderForClass(Class<?> cls)
            throws IllegalAccessException, InstantiationException {
        InjectField viewBinder = BINDERS.get(cls);
        if (viewBinder != null) {
            return viewBinder;
        }
        String clsName = cls.getName();
        if (clsName.startsWith("android.") || clsName.startsWith("java.")) {
            return NOP_VIEW_BINDER;
        }
        try {
            Class<?> viewBindingClass = Class.forName(clsName + "$$FieldInject");
            //noinspection unchecked
            viewBinder = (InjectField) viewBindingClass.newInstance();
        } catch (ClassNotFoundException e) {
            viewBinder = findViewBinderForClass(cls.getSuperclass());
        }
        BINDERS.put(cls, viewBinder);
        return viewBinder;
    }
}
