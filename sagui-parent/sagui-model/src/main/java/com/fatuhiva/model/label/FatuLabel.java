package com.fatuhiva.model.label;

import com.fatuhiva.model.FatuComponent;
import com.tuamotu.commons.i18n.I18n;

public class FatuLabel extends FatuComponent {

	public static final String LABEL_PROPERTY = "label";
	public static final String HINT_PROPERTY = "hint";
	
	private I18n label;
	private I18n hint;

	public I18n getLabel() {
		return label;
	}

	public void setLabel(I18n label) {
    	Object oldValue = this.getLabel();
		this.label = label;
    	Object newValue = this.getLabel();
    	
    	super.getPropertyChangeSupport().firePropertyChange(FatuLabel.LABEL_PROPERTY, oldValue, newValue);
	}

	public I18n getHint() {
		return hint;
	}

	public void setHint(I18n hint) {
    	Object oldValue = this.getHint();
		this.hint = hint;
    	Object newValue = this.getHint();
    	
    	super.getPropertyChangeSupport().firePropertyChange(FatuLabel.HINT_PROPERTY, oldValue, newValue);
	}

}
