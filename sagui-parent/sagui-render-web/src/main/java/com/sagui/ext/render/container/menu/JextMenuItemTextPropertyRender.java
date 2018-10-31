package com.sagui.ext.render.container.menu;

import org.apache.commons.lang.StringUtils;

import com.sagui.ext.common.render.generic.FatuGenericPropertyRender;
import com.sagui.model.container.menu.FatuMenuItem;

public class JextMenuItemTextPropertyRender extends FatuGenericPropertyRender<FatuMenuItem> {

	public JextMenuItemTextPropertyRender() {
		super(FatuMenuItem.class, "label", "text", "setText", String.class);
		super.setRenderIffNull(true);
		super.setDefaultIfNull(StringUtils.EMPTY);
	}

}
