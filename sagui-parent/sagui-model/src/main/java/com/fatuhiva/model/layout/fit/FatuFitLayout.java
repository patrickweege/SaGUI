package com.fatuhiva.model.layout.fit;

import com.fatuhiva.model.layout.IFatuLayoutManager;


public class FatuFitLayout implements IFatuLayoutManager<FatuFitLayoutRule> {

    public static final FatuFitLayout FIT_LAYOUT = new FatuFitLayout();
    
    private FatuFitLayout() {
    }

	@Override
	public void setRule(String id, FatuFitLayoutRule rule) {
		if(!(rule instanceof FatuFitLayoutRule)) {
			throw new IllegalArgumentException("Invalid Layout Rule: " + rule);
		}
	}

	@Override
	public FatuFitLayoutRule getRule(String id) {
		return FatuFitLayoutRule.FIT_LAYOUT_RULE;
	}
    

}
