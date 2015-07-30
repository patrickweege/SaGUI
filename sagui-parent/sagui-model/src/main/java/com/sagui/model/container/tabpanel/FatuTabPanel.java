package com.sagui.model.container.tabpanel;

import java.awt.Insets;

import com.sagui.model.FatuComponent;
import com.sagui.model.FatuContainer;
import com.sagui.model.feature.IFatuPaddingFeature;
import com.sagui.model.feature.IFatuTitleFeature;

public class FatuTabPanel<T extends FatuContainer<FatuComponent> & IFatuTitleFeature> extends FatuContainer<T> implements IFatuPaddingFeature {

    private Insets padding;

    public FatuTabPanel() {
    }

    public void addChild(T child) {
        super.addChild(child);
    };

    @Override
    public void setPadding(Insets padding) {
    	Object oldValue = this.padding;
        this.padding = padding;
        this.getPropertyChangeSupport().firePropertyChange("padding", oldValue, this.padding);
    }

    @Override
    public Insets getPadding() {
        return this.padding;
    }

}
