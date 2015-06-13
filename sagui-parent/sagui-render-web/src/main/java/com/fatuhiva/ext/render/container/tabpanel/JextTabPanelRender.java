package com.fatuhiva.ext.render.container.tabpanel;

import com.fatuhiva.ext.common.render.generic.FatuAbstractRender;
import com.fatuhiva.ext.render.container.JextChildrenRender;
import com.fatuhiva.ext.render.generic.BodyPaddingFeatureRender;
import com.fatuhiva.ext.render.layout.FatuExtLayoutRuleRender;
import com.fatuhiva.model.container.tabpanel.FatuTabPanel;

@SuppressWarnings("rawtypes")
public class JextTabPanelRender extends FatuAbstractRender<FatuTabPanel> {

    public JextTabPanelRender() {
        super("Ext.tab.Panel");
        super.composite.addRender(new FatuExtLayoutRuleRender<FatuTabPanel>() );
        super.composite.addRender(new BodyPaddingFeatureRender<FatuTabPanel>());
        super.composite.addRender(new JextChildrenRender<FatuTabPanel>());
    }

}
