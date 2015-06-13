package com.fatuhiva.gui.aspects;

import java.beans.PropertyChangeEvent;
import java.util.List;

import com.fatuhiva.model.FatuComponent;
import com.fatuhiva.model.FatuContainer;

public class FatuChildrenPropertyChangedEvent<T extends FatuComponent> extends PropertyChangeEvent {

    private static final long serialVersionUID = 1L;

    public enum FatuTypeOfChange {
        ELEMENT_ADDED, ELEMENT_REMOVED
    }

    private final T element;
    private final FatuTypeOfChange typeOfChange;

    public FatuChildrenPropertyChangedEvent(FatuContainer<?> container, String property, List<T> oldValue, List<T> newValue, T element, FatuTypeOfChange typeOfChange) {
        super(container, property, oldValue, newValue);
        this.element = element;
        this.typeOfChange = typeOfChange;
    }

    public T getElement() {
        return element;
    }
    
    public FatuTypeOfChange getTypeOfChange() {
        return typeOfChange;
    }

}
