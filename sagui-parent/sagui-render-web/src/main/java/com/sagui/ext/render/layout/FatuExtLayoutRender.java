package com.sagui.ext.render.layout;

import com.sagui.ext.common.render.generic.FatuCompositeRender;
import com.sagui.model.FatuComponent;
import com.sagui.model.feature.IFatuLayoutFeature;

public class FatuExtLayoutRender<T extends FatuComponent & IFatuLayoutFeature<?>> extends FatuCompositeRender<T> {

    public FatuExtLayoutRender() {
        super.addRender(new JextBorderLayoutPropertyRender<T>());
        super.addRender(new JextTableLayoutPropertyRender<T>());
        super.addRender(new JextAutoLayoutPropertyRender<T>());
        super.addRender(new JextBoxLayoutPropertyRender<T>());
        super.addRender(new JextFitLayoutPropertyRender<T>());

    }

}
