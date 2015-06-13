package com.fatuhiva.ext.render.manager;

import com.fatuhiva.ext.common.render.AbstractRenderManager;
import com.fatuhiva.ext.common.render.IRenderManager;
import com.fatuhiva.ext.controller.grid.GridGetdataActionResult;
import com.fatuhiva.ext.controller.grid.GridRefreshActionResult;
import com.fatuhiva.ext.render.button.JextButtonRender;
import com.fatuhiva.ext.render.checkbox.JextCheckboxRender;
import com.fatuhiva.ext.render.combobox.JextComboRender;
import com.fatuhiva.ext.render.container.FatuExtFieldSetRender;
import com.fatuhiva.ext.render.container.page.JextPageRender;
import com.fatuhiva.ext.render.container.panel.JextPanelRender;
import com.fatuhiva.ext.render.container.tabpanel.JextTabPanelRender;
import com.fatuhiva.ext.render.container.toolbar.JextToolbarRender;
import com.fatuhiva.ext.render.container.window.JextWindowRender;
import com.fatuhiva.ext.render.grid.JextGridRender;
import com.fatuhiva.ext.render.grid.result.GetdataResultRender;
import com.fatuhiva.ext.render.grid.result.RefreshActionResultRender;
import com.fatuhiva.ext.render.label.JextLabelRender;
import com.fatuhiva.ext.render.textbox.JextTextBoxRender;
import com.fatuhiva.model.button.FatuButton;
import com.fatuhiva.model.container.field.FatuFieldSet;
import com.fatuhiva.model.container.form.FatuForm;
import com.fatuhiva.model.container.page.FatuPage;
import com.fatuhiva.model.container.panel.FatuPanel;
import com.fatuhiva.model.container.tabpanel.FatuTabPanel;
import com.fatuhiva.model.container.toolbar.FatuToolbar;
import com.fatuhiva.model.container.window.FatuWindow;
import com.fatuhiva.model.editable.editbox.FatuTextBox;
import com.fatuhiva.model.editable.list.checkbox.FatuCheckbox;
import com.fatuhiva.model.grid.FatuGrid;
import com.fatuhiva.model.label.FatuLabel;
import com.fatuhiva.model.list.combo.editable.FatuComboBox;

public class WebRenderManager extends AbstractRenderManager {

    private static IRenderManager instance = new WebRenderManager();

    public static IRenderManager getInstance() {
        return instance;
    }

    public WebRenderManager() {
        super();
        super.addRender(FatuPage.class, new JextPageRender());
        super.addRender(FatuToolbar.class, new JextToolbarRender());
        super.addRender(FatuForm.class, new com.fatuhiva.ext.render.container.form.JextFormRender());
        super.addRender(FatuFieldSet.class, new FatuExtFieldSetRender());
        super.addRender(FatuPanel.class, new JextPanelRender());

        super.addRender(FatuTabPanel.class, new JextTabPanelRender());

        super.addRender(FatuWindow.class, new JextWindowRender());
        super.addRender(FatuButton.class, new JextButtonRender());
        super.addRender(FatuTextBox.class, new JextTextBoxRender());
        super.addRender(FatuLabel.class, new JextLabelRender());
        super.addRender(FatuComboBox.class, new JextComboRender());
        super.addRender(FatuCheckbox.class, new JextCheckboxRender());
        super.addRender(FatuGrid.class, new JextGridRender());

        super.addRender(GridGetdataActionResult.class, new GetdataResultRender());
        super.addRender(GridRefreshActionResult.class, new RefreshActionResultRender());
    }

}
