package com.fatuhiva.model.datamodel;

import java.util.EventListener;


public interface IFatuTableModelListener extends EventListener {
    
    
    public void tableChanged(FatuTableModelEvent evt);
    

}
