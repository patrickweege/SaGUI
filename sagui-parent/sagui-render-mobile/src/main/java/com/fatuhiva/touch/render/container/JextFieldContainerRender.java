package com.fatuhiva.touch.render.container;

import com.fatuhiva.model.container.field.FatuFieldSet;
import com.fatuhiva.touch.render.generic.JextLabelableFeatureRender;
import com.fatuhiva.touch.render.generic.JextSizeFeatureRender;

@SuppressWarnings("rawtypes")
public class JextFieldContainerRender extends JextAbstractLayoutContainerRender<FatuFieldSet> {

	public JextFieldContainerRender() {
		super("Ext.form.FieldContainer");
        composite.addRender(new JextLabelableFeatureRender<FatuFieldSet>());
        composite.addRender(new JextSizeFeatureRender<FatuFieldSet>());
	}
	
	
	

}
