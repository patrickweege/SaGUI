package com.fatuhiva.forms;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Method;
import java.util.List;

import org.apache.commons.collections.map.MultiKeyMap;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.reflect.MethodUtils;

import com.fatuhiva.model.FatuComponent;
import com.sagui.dataset.commons.util.AnnotationUtils;

public class FatuReflectPropertyChangedListener implements PropertyChangeListener {

    private final Object handler;
    private final MultiKeyMap methodsMap;

    public FatuReflectPropertyChangedListener(Object actionHandler) {
        this.handler = actionHandler;
        this.methodsMap = new MultiKeyMap();
        List<Method> methods = AnnotationUtils.getAnnotatedMethods(actionHandler.getClass(), FatuPropertyChangedEventHandler.class);
        for (Method method : methods) {
            FatuPropertyChangedEventHandler annotation = method.getAnnotation(FatuPropertyChangedEventHandler.class);
            String[] cmp = annotation.components();
            for (String cmpName : cmp) {
                if(annotation.properties() != null && annotation.properties().length > 0) {
                    for(String prop : annotation.properties()) {
                        this.methodsMap.put(cmpName, prop, method.getName());
                    }
                } else {
                    this.methodsMap.put(cmpName, "ALL", method.getName());
                }
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        try {
            Object source = evt.getSource();
            if (source instanceof FatuComponent) {
                String name = ((FatuComponent) source).getName();
                String propName = evt.getPropertyName();
                String methodName = (String)methodsMap.get(name,propName);
                if(!StringUtils.isBlank(methodName)) {
                    MethodUtils.invokeMethod(handler, methodName, evt);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
