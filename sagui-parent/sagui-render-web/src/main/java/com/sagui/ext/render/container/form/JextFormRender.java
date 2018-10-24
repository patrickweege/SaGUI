package com.sagui.ext.render.container.form;

import com.sagui.ext.common.render.RenderException;
import com.sagui.ext.common.render.generic.FatuAbstractRender;
import com.sagui.ext.common.render.util.RenderWriter;
import com.sagui.ext.render.container.JextChildrenRender;
import com.sagui.ext.render.generic.JextSizeFeatureRender;
import com.sagui.ext.render.generic.JextTitleRender;
import com.sagui.ext.render.layout.FatuExtLayoutRender;
import com.sagui.ext.render.layout.FatuExtLayoutRuleRender;
import com.sagui.model.container.form.FatuForm;

@SuppressWarnings({"rawtypes","unchecked"})
public class JextFormRender extends FatuAbstractRender<FatuForm> {

    public JextFormRender() {
		super("Ext.panel.Panel");
		// Properties
		super.composite.addRender(new JextTitleRender());
		super.composite.addRender(new JextSizeFeatureRender());
		super.composite.addRender(new FatuExtLayoutRuleRender<FatuForm>() );
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
