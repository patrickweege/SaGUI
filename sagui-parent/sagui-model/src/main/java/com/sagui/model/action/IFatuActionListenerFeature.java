package com.sagui.model.action;

import java.util.Collection;


public interface IFatuActionListenerFeature {

    public void addActionListener(IFatuActionListener listener);

    public boolean removeActionListener(IFatuActionListener listener);

    public Collection<IFatuActionListener> getActionListeners();
    
    public void fireActionListeners(IFatuActionEvent evt);
    
}
