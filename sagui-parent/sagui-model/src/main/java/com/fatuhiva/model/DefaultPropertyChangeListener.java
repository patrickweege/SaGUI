package com.fatuhiva.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import com.pw.common.JextContext;


public class DefaultPropertyChangeListener implements PropertyChangeListener {

    public static String PROPERTY_CHANGE_LISTENER_KEY = DefaultPropertyChangeListener.class.getName();
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        PropertyChangeListener l = JextContext.getValue(DefaultPropertyChangeListener.PROPERTY_CHANGE_LISTENER_KEY);
        if(l != null) {
            l.propertyChange(evt);
        }
    }


}
