package com.fatuhiva.model.button;

import com.fatuhiva.model.IFatuElement;
import com.fatuhiva.model.action.IFatuActionEvent;


public class FatuButtonClickEvent implements IFatuActionEvent {

    private final FatuButton btn;

    public FatuButtonClickEvent(FatuButton btn) {
        this.btn = btn;
    }
    
    @Override
    public IFatuElement getSource() {
        return btn;
    }

}
