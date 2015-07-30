package com.sagui.model.layout.table;

import com.sagui.model.layout.IFatuLayoutManager;


public class FatuTableLayout implements IFatuLayoutManager<FatuTableLayoutRule> {

    private final int columns;

    public FatuTableLayout(int columns) {
        this.columns = columns;
    }
    
    public int getColumns() {
        return columns;
    }

	@Override
	public void setRule(String id, FatuTableLayoutRule rule) {
		if(!(rule instanceof FatuTableLayoutRule)) {
			throw new IllegalArgumentException("Invalid Layout Rule: " + rule);
		}

	}

	@Override
	public FatuTableLayoutRule getRule(String id) {
		return FatuTableLayoutRule.TABLE_RULE;
	}
	
	
    
}
