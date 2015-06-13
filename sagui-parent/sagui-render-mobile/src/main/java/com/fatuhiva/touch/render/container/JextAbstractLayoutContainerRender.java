package com.fatuhiva.touch.render.container;

import com.fatuhiva.model.container.FatuLayoutContainer;
import com.fatuhiva.touch.render.layout.JextAutoLayoutPropertyRender;
import com.fatuhiva.touch.render.layout.JextBorderLayoutPropertyRender;
import com.fatuhiva.touch.render.layout.JextBorderLayoutRuleRender;
import com.fatuhiva.touch.render.layout.JextBoxLayoutPropertyRender;
import com.fatuhiva.touch.render.layout.JextFitLayoutPropertyRender;
import com.fatuhiva.touch.render.layout.JextTableLayoutPropertyRender;

@SuppressWarnings("rawtypes")
public abstract class JextAbstractLayoutContainerRender<T extends FatuLayoutContainer> extends JextAbstractContainerRender<T> {

	public JextAbstractLayoutContainerRender(String extType) {
		super(extType);
        // Layout and Layout Rules
        super.composite.addRender(new JextBorderLayoutPropertyRender<T>());
        super.composite.addRender(new JextBorderLayoutRuleRender<T>());

        super.composite.addRender(new JextTableLayoutPropertyRender<T>());
        super.composite.addRender(new JextAutoLayoutPropertyRender<T>());
        super.composite.addRender(new JextBoxLayoutPropertyRender<T>());
        super.composite.addRender(new JextFitLayoutPropertyRender<T>());
	}
	
	
	

}
