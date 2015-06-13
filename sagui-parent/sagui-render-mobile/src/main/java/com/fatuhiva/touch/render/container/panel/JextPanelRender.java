package com.fatuhiva.touch.render.container.panel;

import com.fatuhiva.ext.common.render.RenderException;
import com.fatuhiva.ext.common.render.util.RenderWriter;
import com.fatuhiva.model.container.panel.FatuPanel;
import com.fatuhiva.touch.render.container.JextChildrenRender;
import com.fatuhiva.touch.render.generic.JextAbstractRender;
import com.fatuhiva.touch.render.generic.JextGenericPropertyRender;
import com.fatuhiva.touch.render.layout.AnyLayoutRender;

public class JextPanelRender extends JextAbstractRender<FatuPanel> {

//    private static final JextGenericPropertyRender<JextPanel> TITLE_PROP_RENDER;
//    private static final JextGenericPropertyRender<JextPanel> SHOW_BORDER_PROP_RENDER;
//    private static final JextGenericPropertyRender<JextPanel> COLLAPSIBLE_PROP_RENDER;
//    private static final JextGenericPropertyRender<JextPanel> SCROOLLABLE_PROP_RENDER;
//
//    static {
//        TITLE_PROP_RENDER = new JextGenericPropertyRender<JextPanel>(JextPanel.class, "title", "title", null, String.class);
//        TITLE_PROP_RENDER.setRenderIffNull(false);
//
//        SHOW_BORDER_PROP_RENDER = new JextGenericPropertyRender<JextPanel>(JextPanel.class, "showBorder", "border", null, Boolean.class);
//        SHOW_BORDER_PROP_RENDER.setRenderIffNull(false);
//
//        COLLAPSIBLE_PROP_RENDER = new JextGenericPropertyRender<JextPanel>(JextPanel.class, "collapsible", "collapsible", null, Boolean.class);
//        COLLAPSIBLE_PROP_RENDER.setRenderIffNull(false);
//
//        SCROOLLABLE_PROP_RENDER = new JextGenericPropertyRender<JextPanel>(JextPanel.class, "scrollable", "autoScrool", null, Boolean.class);
//        SCROOLLABLE_PROP_RENDER.setRenderIffNull(false);
//    }

    public JextPanelRender() {
        super("Ext.form.Panel");
        // Properties
        
        JextGenericPropertyRender<FatuPanel> TITLE_PROP_RENDER = new JextGenericPropertyRender<FatuPanel>(FatuPanel.class, "title", "title", null, String.class);
        TITLE_PROP_RENDER.setRenderIffNull(false);
        super.composite.addRender(TITLE_PROP_RENDER);
        
        super.composite.addRender(new AnyLayoutRender<FatuPanel>());
        super.composite.addRender(new JextChildrenRender<FatuPanel>());

        
//        super.composite.addRender(new JextSizeFeatureRender<JextPanel>());
//        super.composite.addRender(SHOW_BORDER_PROP_RENDER);
//        super.composite.addRender(COLLAPSIBLE_PROP_RENDER);
//        super.composite.addRender(SCROOLLABLE_PROP_RENDER);
//        
//        super.composite.addRender(new JextPanelToolbarPropertyRenderRender());

    }

    @Override
    public boolean render(FatuPanel component, RenderWriter out) throws RenderException {
        return super.render(component, out);
    }

}
