package com.fatuhiva.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;

import com.fatuhiva.model.feature.IFatuNameFeature;
import com.fatuhiva.model.feature.IFatuPropertyChangeListenerFeature;

public abstract class FatuComponent implements IFatuNameFeature, IFatuElement, IFatuPropertyChangeListenerFeature {

    private final java.beans.PropertyChangeSupport support;
    
    private String id;
    private String name;
    private FatuContainer<?> parent;

    public FatuComponent() {
        this.id = "fatu-" + UUID.randomUUID().toString();
        this.support = new PropertyChangeSupport(this);
        this.support.addPropertyChangeListener(new DefaultPropertyChangeListener());
    }

    @Override
    public final String getId() {
        return this.id;
    }

    @Override
    public final String getName() {
        return this.name;
    }

    @Override
    public final void setName(String name) {
    	Object oldValue = this.getName();
    	this.name = name;
    	Object newValue = this.getName();
    	
    	this.support.firePropertyChange(IFatuNameFeature.NAME_PROPERTY, oldValue, newValue);
    }

    protected final void setParent(FatuContainer<?> parent) {
        this.parent = parent;
    }

    public final FatuContainer<?> getParent() {
        return parent;
    }

    @Override
    public boolean equals(Object obj) {
        return StringUtils.equals(this.getId(), ((FatuComponent) obj).getId());
    }
    
    @Override
    public void addPropertyChangeListener(java.beans.PropertyChangeListener listener) {
        this.support.addPropertyChangeListener(listener);
    }

    @Override
    public void addPropertyChangeListener(String propertyName, java.beans.PropertyChangeListener listener) {
        this.support.addPropertyChangeListener(propertyName, listener);
    }

    @Override
    public void removePropertyChangeListener(String propertyName, java.beans.PropertyChangeListener listener) {
        this.support.removePropertyChangeListener(propertyName, listener);
    }
    
    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
    	this.support.removePropertyChangeListener(listener);
    }
    
    protected PropertyChangeSupport getPropertyChangeSupport() {
        return support;
    }
    

}
