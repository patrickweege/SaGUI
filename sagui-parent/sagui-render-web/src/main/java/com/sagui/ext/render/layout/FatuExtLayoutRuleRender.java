package com.sagui.ext.render.layout;

import com.sagui.ext.common.render.generic.FatuCompositeRender;
import com.sagui.model.FatuComponent;

public class FatuExtLayoutRuleRender<T extends FatuComponent> extends FatuCompositeRender<T> {

    public FatuExtLayoutRuleRender() {
        super.addRender(new JextBorderLayoutRuleRender<T>());
    }

}
