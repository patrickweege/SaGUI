package com.fatuhiva.model.container.tabpanel;

import java.awt.Insets;

import com.fatuhiva.model.FatuComponent;
import com.fatuhiva.model.FatuContainer;
import com.fatuhiva.model.feature.IFatuPaddingFeature;
import com.fatuhiva.model.feature.IFatuTitleFeature;

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
