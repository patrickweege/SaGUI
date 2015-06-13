package com.fatuhiva.ext.render.container.form;

import com.fatuhiva.ext.common.render.RenderException;
import com.fatuhiva.ext.common.render.generic.FatuAbstractRender;
import com.fatuhiva.ext.common.render.util.RenderWriter;
import com.fatuhiva.ext.render.container.JextChildrenRender;
import com.fatuhiva.ext.render.generic.JextSizeFeatureRender;
import com.fatuhiva.ext.render.generic.JextTitleRender;
import com.fatuhiva.ext.render.layout.FatuExtLayoutRender;
import com.fatuhiva.model.container.form.FatuForm;

@SuppressWarnings({"rawtypes","unchecked"})
public class JextFormRender extends FatuAbstractRender<FatuForm> {

    public JextFormRender() {
		super("Ext.panel.Panel");
		// Properties
		super.composite.addRender(new JextTitleRender());
		super.composite.addRender(new JextSizeFeatureRender());
		// Layout and Layout Rules
        super.composite.addRender(new FatuExtLayoutRender());
		// Children
		super.composite.addRender(new JextChildrenRender<FatuForm>());
	}
	
	@Override
	public boolean render(FatuForm component, RenderWriter out) throws RenderException {
		return super.render(component, out);
	}
	
	
	
}
