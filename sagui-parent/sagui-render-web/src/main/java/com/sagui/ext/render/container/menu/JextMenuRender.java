package com.sagui.ext.render.container.menu;

import com.sagui.ext.common.render.RenderException;
import com.sagui.ext.common.render.generic.FatuAbstractRender;
import com.sagui.ext.common.render.generic.FatuExtFixedValuePropertyRender;
import com.sagui.ext.common.render.util.RenderWriter;
import com.sagui.ext.render.container.JextChildrenRender;
import com.sagui.model.container.menu.FatuMenu;

@SuppressWarnings({"rawtypes"})
public class JextMenuRender extends FatuAbstractRender<FatuMenu> {

    public JextMenuRender() {
		super("Ext.menu.Menu");
		
		super.composite.addRender(new FatuExtFixedValuePropertyRender<FatuMenu>("floating",false));
		super.composite.addRender(new FatuExtFixedValuePropertyRender<FatuMenu>("width",100));
		super.composite.addRender(new FatuExtFixedValuePropertyRender<FatuMenu>("height",200));
		
		
		
		
		// Properties
		//super.composite.addRender(new JextSizeFeatureRender());
		// Layout and Layout Rules
        //super.composite.addRender(new FatuExtLayoutRender());
		// Children
		super.composite.addRender(new JextChildrenRender<FatuMenu>());
	}
	
	@Override
	public boolean render(FatuMenu component, RenderWriter out) throws RenderException {
		return super.render(component, out);
	}
	
	
	
}
