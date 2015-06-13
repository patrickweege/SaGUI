package com.fatuhiva.ext.render.layout;

import com.fatuhiva.ext.common.render.generic.FatuCompositeRender;
import com.fatuhiva.model.FatuComponent;

public class FatuExtLayoutRuleRender<T extends FatuComponent> extends FatuCompositeRender<T> {

    public FatuExtLayoutRuleRender() {
        super.addRender(new JextBorderLayoutRuleRender<T>());
    }

}
