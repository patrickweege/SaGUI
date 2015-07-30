package com.sagui.ext.controller.button;

import com.sagui.ext.controller.IActionResult;
import com.sagui.ext.controller.IJextAction;
import com.sagui.ext.controller.IJextController;
import com.sagui.model.button.FatuButton;

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
