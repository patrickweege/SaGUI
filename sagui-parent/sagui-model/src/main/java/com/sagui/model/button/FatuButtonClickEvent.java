package com.sagui.model.button;

import com.sagui.model.IFatuElement;
import com.sagui.model.action.IFatuActionEvent;


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
