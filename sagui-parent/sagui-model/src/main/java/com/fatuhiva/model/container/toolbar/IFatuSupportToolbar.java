package com.fatuhiva.model.container.toolbar;

import com.fatuhiva.model.FatuComponent;


public interface IFatuSupportToolbar {

    public <TB extends FatuComponent> FatuToolbar<TB> getToolbar();
    
    public <TB extends FatuComponent> void setToolbar(FatuToolbar<TB> toolbar);
    
    
}
