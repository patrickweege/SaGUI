package com.fatuhiva.touch.render.manager;

import com.fatuhiva.ext.common.render.AbstractRenderManager;
import com.fatuhiva.ext.common.render.IRenderManager;
import com.fatuhiva.model.button.FatuButton;
import com.fatuhiva.model.container.form.FatuForm;
import com.fatuhiva.model.container.page.FatuPage;
import com.fatuhiva.model.container.panel.FatuPanel;
import com.fatuhiva.model.container.tabpanel.FatuTabPanel;
import com.fatuhiva.model.label.FatuLabel;
import com.fatuhiva.touch.render.button.JextButtonRender;
import com.fatuhiva.touch.render.container.page.JextPageRender;
import com.fatuhiva.touch.render.container.panel.JextPanelRender;
import com.fatuhiva.touch.render.container.tabpanel.JextTabPanelRender;
import com.fatuhiva.touch.render.label.JextLabelRender;


public class MobileRenderManager extends AbstractRenderManager  {

    private static IRenderManager instance = new MobileRenderManager();

    public static IRenderManager getInstance() {
        return instance;
    }
    
    public MobileRenderManager() {
        super();
        super.addRender(FatuPage.class, new JextPageRender());
        //        renders.put(JextToolbar.class,new JextToolbarRender());
        super.addRender(FatuForm.class, new com.fatuhiva.touch.render.container.form.JextFormRender());
        //        renders.put(FieldContainer.class,new JextFieldContainerRender());
        super.addRender(FatuPanel.class,new JextPanelRender());

        super.addRender(FatuTabPanel.class,new JextTabPanelRender());
        super.addRender(FatuTabPanelTab.class,new JextTabRender());

        //        renders.put(JextWindow.class,new JextWindowRender());
        super.addRender(FatuButton.class, new JextButtonRender());
        //        renders.put(JextTextBox.class,new JextTextBoxRender());
        super.addRender(FatuLabel.class, new JextLabelRender());
        //        renders.put(JextComboBox.class,new JextComboRender());
        //        renders.put(JextCheckbox.class,new JextCheckboxRender());
        //        renders.put(JextGrid.class,new JextGridRender());
        //        
        //        renders.put(GetdataResult.class, new GetdataResultRender());

    }
    
}
