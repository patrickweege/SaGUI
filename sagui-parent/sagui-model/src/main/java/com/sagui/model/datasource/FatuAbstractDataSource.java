package com.sagui.model.datasource;

import java.util.Observable;
import java.util.Observer;


public abstract class FatuAbstractDataSource<V> extends Observable {

    private boolean enabled = true; 
    private boolean editable = true;
    
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    
    public boolean isEnabled() {
        return this.enabled;
    }
    
    @Override
    public synchronized void addObserver(Observer observer) {
        super.addObserver(observer);
        observer.update(this, null);
    }
    
    public boolean isEditable() {
        return this.editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
        this.setChanged();
        this.notifyObservers();
    }
    
    public abstract void setValue(V value);
    public abstract V getValue();


}
