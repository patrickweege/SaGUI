package com.sagui.ext.render.manager;

import com.sagui.ext.common.render.AbstractRenderManager;
import com.sagui.ext.common.render.IRenderManager;
import com.sagui.ext.controller.grid.GridGetdataActionResult;
import com.sagui.ext.controller.grid.GridRefreshActionResult;
import com.sagui.ext.render.button.JextButtonRender;
import com.sagui.ext.render.checkbox.JextCheckboxRender;
import com.sagui.ext.render.combobox.JextComboRender;
import com.sagui.ext.render.container.FatuExtFieldSetRender;
import com.sagui.ext.render.container.menu.JextMenuItemRender;
import com.sagui.ext.render.container.menu.JextMenuRender;
import com.sagui.ext.render.container.page.JextPageRender;
import com.sagui.ext.render.container.panel.JextPanelRender;
import com.sagui.ext.render.container.tabpanel.JextTabPanelRender;
import com.sagui.ext.render.container.toolbar.JextToolbarRender;
import com.sagui.ext.render.container.window.JextWindowRender;
import com.sagui.ext.render.grid.JextGridRender;
import com.sagui.ext.render.grid.result.GetdataResultRender;
import com.sagui.ext.render.grid.result.RefreshActionResultRender;
import com.sagui.ext.render.label.JextLabelRender;
import com.sagui.ext.render.textbox.JextTextBoxRender;
import com.sagui.model.button.FatuButton;
import com.sagui.model.container.field.FatuFieldSet;
import com.sagui.model.container.form.FatuForm;
import com.sagui.model.container.menu.FatuMenu;
import com.sagui.model.container.menu.FatuMenuItem;
import com.sagui.model.container.page.FatuPage;
import com.sagui.model.container.panel.FatuPanel;
import com.sagui.model.container.tabpanel.FatuTabPanel;
import com.sagui.model.container.toolbar.FatuToolbar;
import com.sagui.model.container.window.FatuWindow;
import com.sagui.model.editable.editbox.FatuTextBox;
import com.sagui.model.editable.list.checkbox.FatuCheckbox;
import com.sagui.model.grid.FatuGrid;
import com.sagui.model.label.FatuLabel;
import com.sagui.model.list.combo.editable.FatuComboBox;

public class WebRenderManager extends AbstractRenderManager {

    private static IRenderManager instance = new WebRenderManager();

    public static IRenderManager getInstance() {
        return instance;
    }

    public WebRenderManager() {
        super();
        super.addRender(FatuPage.class, new JextPageRender());
        super.addRender(FatuToolbar.class, new JextToolbarRender());
        super.addRender(FatuForm.class, new com.sagui.ext.render.container.form.JextFormRender());
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
        

        super.addRender(FatuMenu.class, new JextMenuRender());
        super.addRender(FatuMenuItem.class, new JextMenuItemRender());
        

    }

}
