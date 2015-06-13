package com.fatuhiva.model.feature;

import java.util.List;

import com.fatuhiva.model.FatuComponent;


public interface IFatuChildrenFeature<T extends FatuComponent> {

	public static final String CHILDREN_PROPERTY = "children";

    public List<T> getChildren();

    public T getChidAt(int index);

    public int getChildCount();

    public void removeChild(T child);
    
}
