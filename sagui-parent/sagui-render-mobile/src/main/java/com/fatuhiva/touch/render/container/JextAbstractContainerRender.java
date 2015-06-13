package com.fatuhiva.touch.render.container;

import com.fatuhiva.model.FatuContainer;
import com.fatuhiva.touch.render.generic.JextAbstractRender;

@SuppressWarnings("rawtypes")
public abstract class JextAbstractContainerRender<T extends FatuContainer> extends JextAbstractRender<T> {

	public JextAbstractContainerRender(String extType) {
		super(extType);
        // Other Properties
		composite.addRender(new JextChildrenRender<T>());
	}
	
	
	

}
