package com.fatuhiva.touch.controller.button;

import com.fatuhiva.model.button.FatuButton;
import com.fatuhiva.touch.controller.IActionResult;
import com.fatuhiva.touch.controller.IJextAction;
import com.fatuhiva.touch.controller.IJextController;

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
