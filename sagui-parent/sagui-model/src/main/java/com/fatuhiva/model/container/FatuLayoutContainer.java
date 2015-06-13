package com.fatuhiva.model.container;

import com.fatuhiva.model.FatuComponent;
import com.fatuhiva.model.FatuContainer;
import com.fatuhiva.model.feature.IFatuLayoutFeature;
import com.fatuhiva.model.layout.IFatuLayoutManager;
import com.fatuhiva.model.layout.IFatuLayoutRule;

public abstract class FatuLayoutContainer<T extends FatuComponent, MGR extends IFatuLayoutManager<? extends IFatuLayoutRule<MGR>>> extends FatuContainer<T> implements IFatuLayoutFeature<MGR> {

    private final IFatuLayoutManager<IFatuLayoutRule<MGR>> layout;

    @SuppressWarnings("unchecked")
    public FatuLayoutContainer(MGR layoutManager) {
        super();
        this.layout = (IFatuLayoutManager<IFatuLayoutRule<MGR>>) layoutManager;
    }

    public void addChild(T child, IFatuLayoutRule<MGR> layoutRule) {
        super.addChild(child);
        this.layout.setRule(child.getId(), layoutRule);
    }

    public void insertChild(int index, T child, IFatuLayoutRule<MGR> layoutRule) {
        super.insertChild(index, child);
        this.layout.setRule(child.getId(), layoutRule);
    }

    public void removeChild(T child) {
        // TODO patrick.weege - Remove Rulle from Layout
        super.removeChild(child);
    }

    @Override
    @SuppressWarnings("unchecked")
    public MGR getLayout() {
        return (MGR) this.layout;
    }

}
