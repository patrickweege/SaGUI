package com.sagui.model.container.menu;

import com.sagui.dataset.commons.i18n.I18n;
import com.sagui.model.FatuComponent;
import com.sagui.model.feature.IFatuLabelFeature;

public class FatuMenuItem extends FatuComponent implements IFatuLabelFeature {

	private I18n label;

	public FatuMenuItem(I18n label) {
		this.label = label;
	}
	
	@Override
	public void setLabel(I18n label) {
		this.label = label; 
	}

	@Override
	public I18n getLabel() {
		return this.label;
	}

}
