package com.sagui.ext.render.container.menu;

import com.sagui.ext.common.render.RenderException;
import com.sagui.ext.common.render.generic.FatuAbstractRender;
import com.sagui.ext.common.render.util.RenderWriter;
import com.sagui.model.container.menu.FatuMenuItem;

public class JextMenuItemRender extends FatuAbstractRender<FatuMenuItem> {

    public JextMenuItemRender() {
		super("Ext.menu.Item");
		// Properties
		super.composite.addRender(new JextMenuItemTextPropertyRender());
		// Layout and Layout Rules
        //super.composite.addRender(new FatuExtLayoutRender());
		// Children
		//super.composite.addRender(new JextChildrenRender<FatuMenu>());
	}
	
	@Override
	public boolean render(FatuMenuItem component, RenderWriter out) throws RenderException {
		return super.render(component, out);
	}
	
	
	
}
