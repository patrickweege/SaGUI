package com.fatuhiva.model.feature;

public interface IFatuPropertyChangeListenerFeature {

    public void addPropertyChangeListener(java.beans.PropertyChangeListener listener);

    public void addPropertyChangeListener(String propertyName, java.beans.PropertyChangeListener listener);

    public void removePropertyChangeListener(String propertyName, java.beans.PropertyChangeListener listener);

    public void removePropertyChangeListener(java.beans.PropertyChangeListener listener);

}
