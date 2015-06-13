package com.fatuhiva.ext.render.layout;

import com.fatuhiva.ext.common.render.generic.FatuCompositeRender;
import com.fatuhiva.model.FatuComponent;
import com.fatuhiva.model.feature.IFatuLayoutFeature;

public class FatuExtLayoutRender<T extends FatuComponent & IFatuLayoutFeature<?>> extends FatuCompositeRender<T> {

    public FatuExtLayoutRender() {
        super.addRender(new JextBorderLayoutPropertyRender<T>());
        super.addRender(new JextTableLayoutPropertyRender<T>());
        super.addRender(new JextAutoLayoutPropertyRender<T>());
        super.addRender(new JextBoxLayoutPropertyRender<T>());
        super.addRender(new JextFitLayoutPropertyRender<T>());

    }

}
