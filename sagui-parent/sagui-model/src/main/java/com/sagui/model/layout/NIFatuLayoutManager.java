package com.sagui.model.layout;

public interface NIFatuLayoutManager<RULE extends NIFatuLayoutRule<?>> {

    public void setRule(String id, RULE rule);

    public RULE getRule(String id);

}
