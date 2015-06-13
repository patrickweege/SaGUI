package com.fatuhiva.ext.controller.textbox;

import com.fatuhiva.ext.controller.IActionResult;
import com.fatuhiva.ext.controller.IJextAction;
import com.fatuhiva.ext.controller.IJextController;
import com.fatuhiva.model.editable.editbox.FatuTextBox;

public class JextTextBoxController implements IJextController<FatuTextBox> {

    @Override
	public IActionResult execute(IJextAction<FatuTextBox> action) {
		if (action.getTarget() instanceof FatuTextBox) {
		    String newValue = action.getParameter("NEW_VALUE");
		    FatuTextBox target = action.getTarget();
		    if(target != null) {
	            target.setValue(newValue);
	            return null;
		    }
		}
        throw new IllegalStateException("Cannot execute Action");
	}

}
