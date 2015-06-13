package com.fatuhiva.model.layout.border;

import java.util.HashMap;
import java.util.Map;

import com.fatuhiva.model.layout.IFatuLayoutManager;


public class FatuBorderLayout implements IFatuLayoutManager<FatuBorderLayoutRule> {

    private final Map<String,FatuBorderLayoutRule> rules;
	
    public FatuBorderLayout() {
    	rules = new HashMap<String,FatuBorderLayoutRule>();
    }
    
    @Override
    public void setRule(String id, FatuBorderLayoutRule rule) {
    	rules.put(id, rule);
    }
    
    @Override
    public FatuBorderLayoutRule getRule(String id) {
    	return rules.get(id);
    }

}
