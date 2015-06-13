package com.fatuhiva.model.editable;

import java.awt.Insets;

import com.fatuhiva.model.FatuComponent;
import com.fatuhiva.model.feature.FatuLabelPosition;
import com.fatuhiva.model.feature.FatuSize;
import com.fatuhiva.model.feature.IFatuEnabledFeature;
import com.fatuhiva.model.feature.IFatuErrorMsgFeature;
import com.fatuhiva.model.feature.IFatuLabelableFeature;
import com.fatuhiva.model.feature.IFatuMarginFeature;
import com.fatuhiva.model.feature.IFatuSizeFeature;
import com.fatuhiva.model.feature.IFatuVisibleFeature;
import com.tuamotu.commons.i18n.I18n;

public abstract class FatuEditable extends FatuComponent implements IFatuErrorMsgFeature, IFatuSizeFeature, IFatuLabelableFeature, IFatuEnabledFeature, IFatuVisibleFeature, IFatuMarginFeature {

    private I18n label;
    private Integer labelWidth;
    private FatuLabelPosition labelPos;

    private I18n errorMsg;
    private boolean enabled;
    private boolean visible;
    private Insets margin;
    private FatuSize size;

    public FatuEditable() {
        this.margin = new Insets(1, 1, 1, 1);
        this.enabled = true;
        this.visible = true;
    }

    @Override
    public void setLabelWidth(Integer labelWidth) {
    	Object oldValue = this.getLabelWidth();
        this.labelWidth = labelWidth;
    	Object newValue = this.getLabelWidth();
    	
    	super.getPropertyChangeSupport().firePropertyChange(IFatuLabelableFeature.LABEL_WIDTH_PROPERTY, oldValue, newValue);
    }

    @Override
    public Integer getLabelWidth() {
        return this.labelWidth;
    }

    @Override
    public I18n getLabel() {
        return this.label;
    }

    @Override
    public void setLabel(I18n label, FatuLabelPosition labelPosition) {
    	Object oldValue = this.getLabel();
        this.label = label;
        this.labelPos = labelPosition;
    	Object newValue = this.getLabel();
    	
    	super.getPropertyChangeSupport().firePropertyChange(IFatuLabelableFeature.LABEL_PROPERTY, oldValue, newValue);
    }

    public void setLabel(I18n label) {
    	Object oldValue = this.getLabel();
        this.label = label;
        this.labelPos = FatuLabelPosition.LEFT;
    	Object newValue = this.getLabel();
    	
    	super.getPropertyChangeSupport().firePropertyChange(IFatuLabelableFeature.LABEL_PROPERTY, oldValue, newValue);
    }

    @Override
    public FatuLabelPosition getLabelPosition() {
        return this.labelPos;
    }

    @Override
    public void setErrorMsg(I18n errorMsg) {
    	Object oldValue = this.getErrorMsg();
        this.errorMsg = errorMsg;
    	Object newValue = this.getErrorMsg();
    	
    	super.getPropertyChangeSupport().firePropertyChange(IFatuErrorMsgFeature.ERROR_MSG_PROPERTY, oldValue, newValue);
    }

    @Override
    public I18n getErrorMsg() {
        return this.errorMsg;
    }


    @Override
    public void setEnabled(boolean enabled) {
    	Object oldValue = this.isEnabled();
        this.enabled = enabled;
    	Object newValue = this.isEnabled();
    	
    	super.getPropertyChangeSupport().firePropertyChange(IFatuEnabledFeature.ENABLED_PROPERTY, oldValue, newValue);
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }


    @Override
    public void setVisible(boolean visible) {
    	Object oldValue = this.isVisible();
        this.visible = visible;
    	Object newValue = this.isVisible();
    	
    	super.getPropertyChangeSupport().firePropertyChange(IFatuVisibleFeature.VISIBLE_PROPERTY, oldValue, newValue);
    }

    @Override
    public boolean isVisible() {
        return this.visible;
    }

    @Override
    public void setMargins(Insets margin) {
    	Object oldValue = this.getMargins();
        this.margin = margin;
    	Object newValue = this.getMargins();
    	
    	super.getPropertyChangeSupport().firePropertyChange(IFatuMarginFeature.MARGINS_PROPERTY, oldValue, newValue);
    }

    @Override
    public Insets getMargins() {
        return this.margin;
    }

    @Override
    public void setSize(FatuSize size) {
    	Object oldValue = this.getSize();
        this.size = size;
    	Object newValue = this.getSize();
    	
    	super.getPropertyChangeSupport().firePropertyChange(IFatuSizeFeature.SIZE_PROPERTY, oldValue, newValue);
    }

    @Override
    public FatuSize getSize() {
        return this.size;
    }


}
