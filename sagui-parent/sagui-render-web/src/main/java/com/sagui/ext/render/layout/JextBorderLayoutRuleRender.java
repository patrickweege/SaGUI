package com.sagui.ext.render.layout;

import com.sagui.ext.common.render.IComponentRender;
import com.sagui.ext.common.render.RenderException;
import com.sagui.ext.common.render.util.RenderWriter;
import com.sagui.model.FatuComponent;
import com.sagui.model.FatuContainer;
import com.sagui.model.feature.IFatuLayoutFeature;
import com.sagui.model.layout.IFatuLayoutManager;
import com.sagui.model.layout.border.FatuBorderLayout;
import com.sagui.model.layout.border.FatuBorderLayoutRule;

public class JextBorderLayoutRuleRender<T extends FatuComponent> implements IComponentRender<T> {

    @Override
    public boolean render(T component, RenderWriter out) throws RenderException {
        boolean rendered = false;
        try {
            FatuContainer<?> parent = component.getParent();
            if (parent != null && parent instanceof IFatuLayoutFeature) {
                IFatuLayoutFeature<?> feature = ((IFatuLayoutFeature<?>)parent);
                IFatuLayoutManager<?> layout = feature.getLayout();
                if (feature != null && layout instanceof FatuBorderLayout) {
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
