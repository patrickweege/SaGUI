package com.fatuhiva.touch.render.layout;

import com.fatuhiva.model.container.FatuLayoutContainer;
import com.fatuhiva.touch.render.generic.CompositeRender;


@SuppressWarnings({"rawtypes","unchecked"})
public class AnyLayoutRender<T extends FatuLayoutContainer> extends CompositeRender<T> {

    public AnyLayoutRender() {
        super.addRender(new JextBorderLayoutPropertyRender<T>());
        super.addRender(new JextBorderLayoutRuleRender<T>());

        super.addRender(new JextTableLayoutPropertyRender<T>());
        super.addRender(new JextAutoLayoutPropertyRender<T>());
        super.addRender(new JextBoxLayoutPropertyRender<T>());
        super.addRender(new JextFitLayoutPropertyRender<T>());
        
    }
    
    
}
