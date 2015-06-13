package com.fatuhiva.model.feature;

import com.fatuhiva.model.layout.IFatuLayoutManager;

public interface IFatuLayoutFeature<MGR extends IFatuLayoutManager<?>> {

    public MGR getLayout();
    
}
