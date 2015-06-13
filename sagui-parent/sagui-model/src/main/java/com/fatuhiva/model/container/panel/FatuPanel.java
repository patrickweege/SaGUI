package com.fatuhiva.model.container.panel;

import java.awt.Insets;

import com.fatuhiva.model.FatuComponent;
import com.fatuhiva.model.container.FatuLayoutContainer;
import com.fatuhiva.model.container.toolbar.FatuToolbar;
import com.fatuhiva.model.container.toolbar.IFatuSupportToolbar;
import com.fatuhiva.model.feature.FatuSize;
import com.fatuhiva.model.feature.IFatuLayoutFeature;
import com.fatuhiva.model.feature.IFatuMarginFeature;
import com.fatuhiva.model.feature.IFatuPaddingFeature;
import com.fatuhiva.model.feature.IFatuSizeFeature;
import com.fatuhiva.model.feature.IFatuTitleFeature;
import com.fatuhiva.model.layout.IFatuLayoutManager;
import com.fatuhiva.model.layout.IFatuLayoutRule;

@SuppressWarnings("rawtypes")
public class FatuPanel<MGR extends IFatuLayoutManager<? extends IFatuLayoutRule<MGR>>> extends FatuLayoutContainer<FatuComponent, MGR> implements IFatuLayoutFeature<MGR>, IFatuSupportToolbar, IFatuTitleFeature, IFatuSizeFeature, IFatuPaddingFeature, IFatuMarginFeature {

    private Insets padding;
    private Insets margin;
    private String title;
    private boolean showBorder;
    private boolean collapsible;
    private boolean scrollable;
    private FatuSize size;

    private FatuToolbar toolbar;

    public FatuPanel(MGR layoutManager) {
        super(layoutManager);
    }

    public void setTitle(String title) {
    	Object oldValue = this.title;
        this.title = title;
        this.getPropertyChangeSupport().firePropertyChange("title", oldValue, this.title);
    }

    public String getTitle() {
        return title;
    }

    public boolean isShowBorder() {
        return showBorder;
    }

    public void setShowBorder(boolean border) {
    	Object oldValue = this.showBorder;
        this.showBorder = border;
        this.getPropertyChangeSupport().firePropertyChange("showBorder", oldValue, this.showBorder);
    }

    public boolean isCollapsible() {
        return collapsible;
    }

    public void setCollapsible(boolean collapsible) {
    	Object oldValue = this.collapsible;
        this.collapsible = collapsible;
        this.getPropertyChangeSupport().firePropertyChange("collapsible", oldValue, this.collapsible);
    }

    public boolean isScrollable() {
        return scrollable;
    }

    public void setScrollable(boolean scrollable) {
    	Object oldValue = this.scrollable;
        this.scrollable = scrollable;
        this.getPropertyChangeSupport().firePropertyChange("scrollable", oldValue, this.scrollable);
    }

    @Override
    public void setSize(FatuSize size) {
    	Object oldValue = this.size;
        this.size = size;
        this.getPropertyChangeSupport().firePropertyChange("size", oldValue, this.size);
    }

    @Override
    public FatuSize getSize() {
        return this.size;
    }

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

    @Override
    public void setMargins(Insets margin) {
    	Object oldValue = this.margin;
        this.margin = margin;
        this.getPropertyChangeSupport().firePropertyChange("margin", oldValue, this.margin);
    }

    @Override
    public Insets getMargins() {
        return this.margin;
    }

    @Override
    public <TB extends FatuComponent> void setToolbar(FatuToolbar<TB> toolbar) {
    	Object oldValue = this.toolbar;

    	// Remove OLD from Idirect
        if (this.toolbar != null) {
            super.removeIndirect(this.toolbar);
        }

        // ADD new to Idirect
        if (toolbar != null) {
            super.addIndirect(toolbar);
        }
        this.toolbar = toolbar;
        
        this.getPropertyChangeSupport().firePropertyChange("toolbar", oldValue, this.toolbar);

        
    }

    @SuppressWarnings("unchecked")
    @Override
    public <TB extends FatuComponent> FatuToolbar<TB> getToolbar() {
        return toolbar;
    }
}
