package com.fatuhiva.touch.render.container.tabpanel;

import com.fatuhiva.model.container.tabpanel.FatuTabPanel;
import com.fatuhiva.touch.render.container.JextChildrenRender;
import com.fatuhiva.touch.render.generic.JextAbstractRender;

public class JextTabPanelRender extends JextAbstractRender<FatuTabPanel> {

    public JextTabPanelRender() {
        super("Ext.TabPanel");
        composite.addRender(new JextChildrenRender<FatuTabPanel>());
    }


}
