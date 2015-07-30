package com.sagui.ext.render.container.tabpanel;

import com.sagui.ext.common.render.generic.FatuAbstractRender;
import com.sagui.ext.render.container.JextChildrenRender;
import com.sagui.ext.render.generic.BodyPaddingFeatureRender;
import com.sagui.ext.render.layout.FatuExtLayoutRuleRender;
import com.sagui.model.container.tabpanel.FatuTabPanel;

@SuppressWarnings("rawtypes")
public class JextTabPanelRender extends FatuAbstractRender<FatuTabPanel> {

    public JextTabPanelRender() {
        super("Ext.tab.Panel");
        super.composite.addRender(new FatuExtLayoutRuleRender<FatuTabPanel>() );
        super.composite.addRender(new BodyPaddingFeatureRender<FatuTabPanel>());
        super.composite.addRender(new JextChildrenRender<FatuTabPanel>());
    }

}
