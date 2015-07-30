package com.sagui.ext.controller.checkbox;

import com.sagui.ext.controller.IActionResult;
import com.sagui.ext.controller.IJextAction;
import com.sagui.ext.controller.IJextController;
import com.sagui.model.editable.list.checkbox.FatuCheckbox;

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
