package com.sagui.model.container.toolbar;

import com.sagui.model.FatuComponent;
import com.sagui.model.FatuContainer;


public class FatuToolbar<T extends FatuComponent> extends FatuContainer<T> {

    public void addChild(T child) {
        super.addChild(child);
    }

    public void insertChild(int index, T child) {
        super.insertChild(index, child);
    }

}
