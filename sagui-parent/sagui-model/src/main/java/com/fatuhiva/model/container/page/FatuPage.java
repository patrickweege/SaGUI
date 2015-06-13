package com.fatuhiva.model.container.page;

import com.fatuhiva.model.container.FatuLayoutContainer;
import com.fatuhiva.model.container.form.FatuForm;
import com.fatuhiva.model.layout.IFatuLayoutManager;
import com.fatuhiva.model.layout.IFatuLayoutRule;

@SuppressWarnings("rawtypes")
public abstract class FatuPage<MGR extends IFatuLayoutManager<? extends IFatuLayoutRule<MGR>>> extends FatuLayoutContainer<FatuForm, MGR> {

    private String title;

    public FatuPage(MGR manager) {
        super(manager);
        init();
    }

    protected abstract void init();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
    	Object oldValue = this.title;
        this.title = title;
        this.getPropertyChangeSupport().firePropertyChange("title", oldValue, this.title);
    }

}
