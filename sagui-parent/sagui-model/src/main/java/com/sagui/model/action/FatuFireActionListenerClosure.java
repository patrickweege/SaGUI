package com.sagui.model.action;

import org.apache.commons.collections4.Closure;

public class FatuFireActionListenerClosure<EVT extends IFatuActionEvent> implements Closure<IFatuActionListener<EVT>> {

    private EVT evt;

    public FatuFireActionListenerClosure(EVT evt) {
        this.evt = evt;
    }
    
	@Override
	public void execute(IFatuActionListener<EVT> obj) {
		IFatuActionListener<EVT> l = (IFatuActionListener<EVT>) obj;
		l.actionPerformed(evt);
	}

	
	
	
}
