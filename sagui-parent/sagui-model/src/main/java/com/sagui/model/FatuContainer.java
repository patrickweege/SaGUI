package com.sagui.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.sagui.model.feature.IFatuChildrenFeature;

public abstract class FatuContainer<T extends FatuComponent> extends FatuComponent implements IFatuChildrenFeature<T>  {

    private final List<T> children;
    private final List<T> indirect;

    public FatuContainer() {
        super();
        this.children = new ArrayList<T>();
        this.indirect = new ArrayList<T>();
    }

    @Override
    public List<T> getChildren() {
        return Collections.unmodifiableList(children);
    }

    @Override
    public T getChidAt(int index) {
        return children.get(index);
    }

    @Override
    public int getChildCount() {
        return children.size();
    }

    protected void addChild(T child) {
        this.insertChild(children.size(), child);
    }

    protected void insertChild(int index, T child) {
    	if(this.children.contains(child)) {
    		throw new RuntimeException("The Component with ID: [" + child.getId() + "] already belongs to the Container");
    	}

    	List<T> oldChildren = new ArrayList<T>(this.children);
        this.indirect.remove(child);
        this.children.add(index, child);
        child.setParent(this);
    	List<T> newChildren = new ArrayList<T>(this.children);
        
        this.getPropertyChangeSupport().firePropertyChange("children", oldChildren, newChildren);
    }

    public void removeChild(T child) {
    	int toRemove = this.children.indexOf(child);
    	if(toRemove >= 0) {
    		this.removeChild(toRemove);
    	}
    }
    
    protected void removeChild(int index) {
    	if(index < this.children.size()) {
        	List<T> oldChildren = new ArrayList<T>(this.children);
    		this.children.remove(index);
    		
    		List<T> newChildren = new ArrayList<T>(this.children);
    		
            this.getPropertyChangeSupport().firePropertyChange("children", oldChildren, newChildren);
    	}
    }


    protected void addIndirect(T child) {
        this.children.remove(child);
        this.indirect.add(child);
        child.setParent(this);
    }

    protected void removeIndirect(T child) {
        this.indirect.remove(child);
        child.setParent(null);
    }
    

}
