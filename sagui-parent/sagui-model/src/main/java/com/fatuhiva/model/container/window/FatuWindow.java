package com.fatuhiva.model.container.window;

import com.fatuhiva.model.container.panel.FatuPanel;
import com.fatuhiva.model.container.toolbar.IFatuSupportToolbar;
import com.fatuhiva.model.feature.IFatuVisibleFeature;
import com.fatuhiva.model.layout.IFatuLayoutManager;
import com.fatuhiva.model.layout.IFatuLayoutRule;


public class FatuWindow<MGR extends IFatuLayoutManager<? extends IFatuLayoutRule<MGR>>> extends FatuPanel<MGR> implements IFatuSupportToolbar, IFatuVisibleFeature {

    private boolean visible;
    private boolean modal;
    
    public FatuWindow(MGR layoutManager) {
        super(layoutManager);
        this.visible = false;
    }

    @Override
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    
    @Override
    public boolean isVisible() {
        return visible;
    }
    
    public void setModal(boolean modal) {
        this.modal = modal;
    }
    
    public boolean isModal() {
        return modal;
    }
}
