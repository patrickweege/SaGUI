package com.sagui.model.feature;

import com.sagui.model.layout.IFatuLayoutManager;

public interface IFatuLayoutFeature<MGR extends IFatuLayoutManager<?>> {

    public MGR getLayout();
    
}
