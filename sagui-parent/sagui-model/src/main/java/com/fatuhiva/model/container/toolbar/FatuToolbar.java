package com.fatuhiva.model.container.toolbar;

import com.fatuhiva.model.FatuComponent;
import com.fatuhiva.model.FatuContainer;


public class FatuToolbar<T extends FatuComponent> extends FatuContainer<T> {

    public void addChild(T child) {
        super.addChild(child);
    }

    public void insertChild(int index, T child) {
        super.insertChild(index, child);
    }

}
