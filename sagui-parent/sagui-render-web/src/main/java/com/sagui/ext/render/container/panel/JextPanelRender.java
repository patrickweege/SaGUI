package com.sagui.ext.render.container.panel;

import com.sagui.ext.common.render.RenderException;
import com.sagui.ext.common.render.generic.FatuAbstractRender;
import com.sagui.ext.common.render.generic.FatuGenericPropertyRender;
import com.sagui.ext.common.render.util.RenderWriter;
import com.sagui.ext.render.container.JextChildrenRender;
import com.sagui.ext.render.generic.AbstractPaddingFeatureRender;
import com.sagui.ext.render.generic.BodyPaddingFeatureRender;
import com.sagui.ext.render.generic.FatuMarginFeatureRender;
import com.sagui.ext.render.generic.JextSizeFeatureRender;
import com.sagui.ext.render.layout.FatuExtLayoutRender;
import com.sagui.ext.render.layout.FatuExtLayoutRuleRender;
import com.sagui.model.container.panel.FatuPanel;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class JextPanelRender extends FatuAbstractRender<FatuPanel> {

    private static final FatuGenericPropertyRender<FatuPanel> TITLE_PROP_RENDER;
    private static final FatuGenericPropertyRender<FatuPanel> SHOW_BORDER_PROP_RENDER;
    private static final FatuGenericPropertyRender<FatuPanel> COLLAPSIBLE_PROP_RENDER;
    private static final FatuGenericPropertyRender<FatuPanel> SCROOLLABLE_PROP_RENDER;

    static {
        TITLE_PROP_RENDER = new FatuGenericPropertyRender(FatuPanel.class, "title", "title", null, String.class);
        TITLE_PROP_RENDER.setRenderIffNull(false);

        SHOW_BORDER_PROP_RENDER = new FatuGenericPropertyRender(FatuPanel.class, "showBorder", "border", null, Boolean.class);
        SHOW_BORDER_PROP_RENDER.setRenderIffNull(false);

        COLLAPSIBLE_PROP_RENDER = new FatuGenericPropertyRender(FatuPanel.class, "collapsible", "collapsible", null, Boolean.class);
        COLLAPSIBLE_PROP_RENDER.setRenderIffNull(false);

        SCROOLLABLE_PROP_RENDER = new FatuGenericPropertyRender(FatuPanel.class, "scrollable", "autoScrool", null, Boolean.class);
        SCROOLLABLE_PROP_RENDER.setRenderIffNull(false);
    }

    public JextPanelRender() {
        super("Ext.panel.Panel");
        // Properties
        super.composite.addRender(TITLE_PROP_RENDER);
        super.composite.addRender(new JextSizeFeatureRender<FatuPanel>());
        super.composite.addRender(SHOW_BORDER_PROP_RENDER);
        super.composite.addRender(COLLAPSIBLE_PROP_RENDER);
        super.composite.addRender(SCROOLLABLE_PROP_RENDER);
        
        super.composite.addRender(new JextPanelToolbarPropertyRenderRender());
        
        super.composite.addRender(new FatuMarginFeatureRender<FatuPanel>());
        super.composite.addRender(new BodyPaddingFeatureRender<FatuPanel>());
        super.composite.addRender(new FatuExtLayoutRuleRender<FatuPanel>() );
        
        super.composite.addRender(new FatuExtLayoutRender());
        super.composite.addRender(new JextChildrenRender<FatuPanel>());


    }

    @Override
    public boolean render(FatuPanel component, RenderWriter out) throws RenderException {
        return super.render(component, out);
    }

}
