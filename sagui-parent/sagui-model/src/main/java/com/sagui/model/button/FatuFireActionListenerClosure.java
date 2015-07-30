package com.sagui.model.button;

import org.apache.commons.collections.Closure;

import com.sagui.model.action.IFatuActionEvent;
import com.sagui.model.action.IFatuActionListener;

class FatuFireActionListenerClosure implements Closure {

    private IFatuActionEvent evt;

    public FatuFireActionListenerClosure(IFatuActionEvent evt) {
        this.evt = evt;
    }
    
	@Override
	public void execute(Object obj) {
		IFatuActionListener l = (IFatuActionListener) obj;
		l.actionPerformed(evt);
	}

	
	
	
}
