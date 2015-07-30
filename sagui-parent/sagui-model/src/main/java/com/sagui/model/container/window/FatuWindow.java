package com.sagui.model.container.window;

import com.sagui.model.container.panel.FatuPanel;
import com.sagui.model.container.toolbar.IFatuSupportToolbar;
import com.sagui.model.feature.IFatuVisibleFeature;
import com.sagui.model.layout.IFatuLayoutManager;
import com.sagui.model.layout.IFatuLayoutRule;


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
