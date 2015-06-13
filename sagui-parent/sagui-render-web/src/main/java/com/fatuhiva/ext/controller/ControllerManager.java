package com.fatuhiva.ext.controller;

import java.util.HashMap;
import java.util.Map;

import com.fatuhiva.ext.controller.button.JextButtonController;
import com.fatuhiva.ext.controller.checkbox.JextCheckboxController;
import com.fatuhiva.ext.controller.combobox.JextComboBoxController;
import com.fatuhiva.ext.controller.grid.JextGridController;
import com.fatuhiva.ext.controller.textbox.JextTextBoxController;
import com.fatuhiva.model.FatuComponent;
import com.fatuhiva.model.button.FatuButton;
import com.fatuhiva.model.editable.editbox.FatuTextBox;
import com.fatuhiva.model.editable.list.checkbox.FatuCheckbox;
import com.fatuhiva.model.grid.FatuGrid;
import com.fatuhiva.model.list.combo.editable.FatuComboBox;

public class ControllerManager {

    private final Map<Class<?>, IJextController<?>> controllers;

    public ControllerManager() {
        this.controllers = new HashMap<Class<?>, IJextController<?>>();

        controllers.put(FatuButton.class, new JextButtonController());
        controllers.put(FatuTextBox.class, new JextTextBoxController());
        controllers.put(FatuComboBox.class, new JextComboBoxController());
        controllers.put(FatuCheckbox.class, new JextCheckboxController());
        controllers.put(FatuGrid.class, new JextGridController());
    }

    public Object executeAction(IJextAction<FatuComponent> action) {
        FatuComponent target = action.getTarget();
        IJextController<FatuComponent> controller = getController(target);
        if (controller != null) {
            IActionResult result = controller.execute(action);
            return result;
        }
        throw new RuntimeException("No controller Found");
    }

    @SuppressWarnings("unchecked")
    public <T extends FatuComponent> IJextController<T> getController(Class<T> controllerFor) {
        return (IJextController<T>) this.controllers.get(controllerFor);
    }

    @SuppressWarnings("unchecked")
    public <T extends FatuComponent> IJextController<T> getController(T controllerFor) {
        Class<T> clazz = (Class<T>) controllerFor.getClass();
        while (true) {
            IJextController<T> controller = getController(clazz);
            if (controller != null) {
                return controller;
            }
            clazz = (Class<T>) clazz.getSuperclass();
            if (clazz == null) return null;
        }
    }

}
