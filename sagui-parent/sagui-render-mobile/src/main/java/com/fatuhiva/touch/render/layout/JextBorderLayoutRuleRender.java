package com.fatuhiva.touch.render.layout;

import com.fatuhiva.ext.common.render.IComponentRender;
import com.fatuhiva.ext.common.render.RenderException;
import com.fatuhiva.ext.common.render.util.RenderWriter;
import com.fatuhiva.model.FatuContainer;
import com.fatuhiva.model.container.FatuLayoutContainer;
import com.fatuhiva.model.layout.IFatuLayoutManager;
import com.fatuhiva.model.layout.border.FatuBorderLayout;
import com.fatuhiva.model.layout.border.FatuBorderLayoutRule;

@SuppressWarnings("rawtypes")
public class JextBorderLayoutRuleRender<T extends FatuLayoutContainer> implements IComponentRender<T> {

    @Override
    public boolean render(T component, RenderWriter out) throws RenderException {
        boolean rendered = false;
        try {
            FatuContainer<?> parent = component.getParent();
            if (parent != null && parent instanceof FatuLayoutContainer) {
                IFatuLayoutManager<?> layout = ((FatuLayoutContainer)parent).getLayout();
                if (layout != null && layout instanceof FatuBorderLayout) {
                    FatuBorderLayout borderLayout = (FatuBorderLayout) layout;
                    FatuBorderLayoutRule rule = borderLayout.getRule(component.getId());
                    if (rule != null) {
                        String region = rule.getRegion().toString().toLowerCase();
                        out.tab().writeConfigAsString("region", region).ln();
                        out.pushComma();
                        out.tab().writeConfig("split", rule.isSplit()).ln();
                        rendered = true;
                    }
                }
            }
        } catch (Exception e) {
            throw new RenderException(e);
        }
        return rendered;
    }

    @Override
    public void update(T component, RenderWriter out) throws RenderException {
    }

}
