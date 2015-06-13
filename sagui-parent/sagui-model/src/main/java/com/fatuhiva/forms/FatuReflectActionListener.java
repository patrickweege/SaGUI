package com.fatuhiva.forms;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.reflect.MethodUtils;

import com.fatuhiva.model.IFatuElement;
import com.fatuhiva.model.FatuComponent;
import com.fatuhiva.model.action.IFatuActionEvent;
import com.fatuhiva.model.action.IFatuActionListener;

@SuppressWarnings("rawtypes")
public class FatuReflectActionListener implements IFatuActionListener {

    private final Object handler;
    private final Map<String, String> methodsMap;

    public FatuReflectActionListener(Object actionHandler) {
        this.handler = actionHandler;
        this.methodsMap = new HashMap<String, String>();
        Method[] methods = getAnnotatedDeclaredMethods(handler.getClass(), FatuActionEventHandler.class, true);
        for (Method method : methods) {
            FatuActionEventHandler annotation = method.getAnnotation(FatuActionEventHandler.class);
            String[] cmp = annotation.components();
            for (String cmpName : cmp) {
                this.methodsMap.put(cmpName, method.getName());
            }
        }
    }

    @Override
    public void actionPerformed(IFatuActionEvent evt) {
        try {
            IFatuElement source = evt.getSource();
            if (source instanceof FatuComponent) {
                String name = ((FatuComponent) source).getName();
                String methodName = methodsMap.get(name);
                MethodUtils.invokeMethod(handler, methodName, evt);
                
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private Method[] getDeclaredMethods(Class clazz, boolean recursively) {
        List<Method> methods = new LinkedList<Method>();
        Method[] declaredMethods = clazz.getDeclaredMethods();
        Collections.addAll(methods, declaredMethods);
        
        Class superClass = clazz.getSuperclass();

        if (superClass != null && recursively) {
            Method[] superDeclaredMethods = getDeclaredMethods(superClass, recursively);
            if (superDeclaredMethods.length > 0) Collections.addAll(methods, superDeclaredMethods);
        }

        return methods.toArray(new Method[methods.size()]);
    }

    /**
     * Retrieving fields list of specified class and which are annotated by incoming annotation class If recursively is true, retrieving fields from all class hierarchy
     * 
     * @param clazz - where fields are searching
     * @param annotationClass - specified annotation class
     * @param recursively param
     * @return list of annotated fields
     */
    private Method[] getAnnotatedDeclaredMethods(Class clazz, Class<? extends Annotation> annotationClass, boolean recursively) {
        Method[] allMethods = getDeclaredMethods(clazz, recursively);
        List<Method> annotadedMethods = new LinkedList<Method>();

        for (Method method : allMethods) {
            if (method.isAnnotationPresent(annotationClass)) {
                annotadedMethods.add(method);
            }
        }

        return annotadedMethods.toArray(new Method[annotadedMethods.size()]);
    }

}
