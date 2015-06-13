package com.fatuhiva.touch.controller.checkbox;

import com.fatuhiva.model.editable.list.checkbox.FatuCheckbox;
import com.fatuhiva.touch.controller.IActionResult;
import com.fatuhiva.touch.controller.IJextAction;
import com.fatuhiva.touch.controller.IJextController;

public class JextCheckboxController implements IJextController<FatuCheckbox> {

    @Override
    public IActionResult execute(IJextAction<FatuCheckbox> action) {
        if (action.getTarget() instanceof FatuCheckbox) {
            String newValue = action.getParameter("NEW_VALUE");
            FatuCheckbox target = action.getTarget();
            if (target != null) {
                Boolean checked = Boolean.valueOf(newValue);
                target.setValue(checked);
                return null;
            }
        }
        throw new IllegalStateException("Cannot execute Action");
    }

}
