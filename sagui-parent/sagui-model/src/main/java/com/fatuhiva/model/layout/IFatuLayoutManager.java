package com.fatuhiva.model.layout;


public interface IFatuLayoutManager<RULE extends IFatuLayoutRule<?>> {
    
    public void setRule(String id, RULE rule);
    
    public RULE getRule(String id);


}
