package com.fatuhiva.touch.controller.textbox;

import com.fatuhiva.model.editable.editbox.FatuTextBox;
import com.fatuhiva.touch.controller.IActionResult;
import com.fatuhiva.touch.controller.IJextAction;
import com.fatuhiva.touch.controller.IJextController;

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
