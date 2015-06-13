package com.fatuhiva.ext.controller.button;

import com.fatuhiva.ext.controller.IActionResult;
import com.fatuhiva.ext.controller.IJextAction;
import com.fatuhiva.ext.controller.IJextController;
import com.fatuhiva.model.button.FatuButton;

public class JextButtonController implements IJextController<FatuButton> {

	@Override
	public IActionResult execute(IJextAction<FatuButton> action) {
		if (action.getTarget() instanceof FatuButton) {
		    action.getTarget().click();
			return null;
		}
		throw new IllegalStateException("Cannot execute Action");
	}

}
