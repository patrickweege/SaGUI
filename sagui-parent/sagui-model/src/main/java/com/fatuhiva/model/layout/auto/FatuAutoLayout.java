package com.fatuhiva.model.layout.auto;

import com.fatuhiva.model.layout.IFatuLayoutManager;

public class FatuAutoLayout implements IFatuLayoutManager<FatuAutoLayoutRule> {

    public static final FatuAutoLayout AUTO_LAYOUT = new FatuAutoLayout();
    
    private FatuAutoLayout() {
    }

	@Override
	public void setRule(String id, FatuAutoLayoutRule rule) {
		if(!(rule instanceof FatuAutoLayoutRule)) {
			throw new IllegalArgumentException("Invalid Layout Rule: " + rule);
		}
	}

	@Override
	public FatuAutoLayoutRule getRule(String id) {
		return FatuAutoLayoutRule.AUTO_LAYOUT_RULE;
	}
    

}
