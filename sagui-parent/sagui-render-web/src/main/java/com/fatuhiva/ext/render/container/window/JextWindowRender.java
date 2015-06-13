package com.fatuhiva.ext.render.container.window;

import com.fatuhiva.ext.common.render.generic.FatuAbstractRender;
import com.fatuhiva.ext.common.render.generic.FatuExtFixedValuePropertyRender;
import com.fatuhiva.ext.common.render.generic.FatuGenericPropertyRender;
import com.fatuhiva.ext.render.container.JextChildrenRender;
import com.fatuhiva.ext.render.generic.BodyPaddingFeatureRender;
import com.fatuhiva.ext.render.generic.FatuMarginFeatureRender;
import com.fatuhiva.ext.render.generic.JextSizeFeatureRender;
import com.fatuhiva.ext.render.layout.FatuExtLayoutRender;
import com.fatuhiva.model.FatuComponent;
import com.fatuhiva.model.container.window.FatuWindow;
import com.fatuhiva.model.layout.IFatuLayoutManager;
import com.fatuhiva.model.layout.IFatuLayoutRule;

public class JextWindowRender extends FatuAbstractRender<FatuWindow> {

    public JextWindowRender() {
        super("Ext.window.Window");

        FatuGenericPropertyRender<FatuWindow> render;
        
        render = new FatuGenericPropertyRender<FatuWindow>(FatuWindow.class, "title", "title", null, String.class);
        render.setRenderIffNull(false);
        super.composite.addRender(render);

        render = new FatuGenericPropertyRender<FatuWindow>(FatuWindow.class, "showBorder", "border", null, Boolean.class);
        render.setRenderIffNull(false);
        super.composite.addRender(render);

        render = new FatuGenericPropertyRender<FatuWindow>(FatuWindow.class, "collapsible", "collapsible", null, Boolean.class);
        render.setRenderIffNull(false);
        super.composite.addRender(render);

        render = new FatuGenericPropertyRender<FatuWindow>(FatuWindow.class, "scrollable", "autoScrool", null, Boolean.class);
        render.setRenderIffNull(false);
        super.composite.addRender(render);

        render = new FatuGenericPropertyRender<FatuWindow>(FatuWindow.class, "visible", "autoShow", "setVisible", Boolean.class);
        render.setRenderIffNull(false);
        super.composite.addRender(render);
        
        render = new FatuGenericPropertyRender<FatuWindow>(FatuWindow.class, "modal", "modal", null , Boolean.class);
        render.setRenderIffNull(false);
        super.composite.addRender(render);
        
        super.composite.addRender(new FatuExtFixedValuePropertyRender<FatuWindow>("constrain",true));

        // Properties
        super.composite.addRender(new JextSizeFeatureRender<FatuWindow>());
        
        super.composite.addRender(new FatuExtLayoutRender());
        super.composite.addRender(new FatuMarginFeatureRender<FatuWindow>());
        super.composite.addRender(new BodyPaddingFeatureRender<FatuWindow>());
        
        super.composite.addRender(new JextChildrenRender<FatuWindow>());

        
    }


}
