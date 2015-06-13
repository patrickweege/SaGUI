package com.fatuhiva.touch.render.container.window;

import com.fatuhiva.ext.common.render.generic.FatuExtFixedValuePropertyRender;
import com.fatuhiva.model.container.window.FatuWindow;
import com.fatuhiva.touch.render.container.JextAbstractLayoutContainerRender;
import com.fatuhiva.touch.render.generic.JextGenericPropertyRender;
import com.fatuhiva.touch.render.generic.JextSizeFeatureRender;

public class JextWindowRender extends JextAbstractLayoutContainerRender<FatuWindow> {

    public JextWindowRender() {
        super("Ext.window.Window");

        JextGenericPropertyRender<FatuWindow> render;

        render = new JextGenericPropertyRender<FatuWindow>(FatuWindow.class, "title", "title", null, String.class);
        render.setRenderIffNull(false);
        super.composite.addRender(render);

        render = new JextGenericPropertyRender<FatuWindow>(FatuWindow.class, "showBorder", "border", null, Boolean.class);
        render.setRenderIffNull(false);
        super.composite.addRender(render);

        render = new JextGenericPropertyRender<FatuWindow>(FatuWindow.class, "collapsible", "collapsible", null, Boolean.class);
        render.setRenderIffNull(false);
        super.composite.addRender(render);

        render = new JextGenericPropertyRender<FatuWindow>(FatuWindow.class, "scrollable", "autoScrool", null, Boolean.class);
        render.setRenderIffNull(false);
        super.composite.addRender(render);

        render = new JextGenericPropertyRender<FatuWindow>(FatuWindow.class, "visible", "autoShow", "setVisible", Boolean.class);
        render.setRenderIffNull(false);
        super.composite.addRender(render);
        
        render = new JextGenericPropertyRender<FatuWindow>(FatuWindow.class, "modal", "modal", null , Boolean.class);
        render.setRenderIffNull(false);
        super.composite.addRender(render);
        
        super.composite.addRender(new FatuExtFixedValuePropertyRender<FatuWindow>("constrain",true));

        
        // Properties
        super.composite.addRender(new JextSizeFeatureRender<FatuWindow>());
    }


}
