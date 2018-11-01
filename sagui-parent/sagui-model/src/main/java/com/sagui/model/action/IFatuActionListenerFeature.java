package com.sagui.model.action;

import java.util.Collection;


public interface IFatuActionListenerFeature<EVT extends IFatuActionEvent> {

    public void addActionListener(IFatuActionListener<EVT> listener);

    public boolean removeActionListener(IFatuActionListener<EVT> listener);

    public Collection<IFatuActionListener<EVT>> getActionListeners();
    
    public void fireActionListeners(EVT evt);
    
}
