package com.sagui.ext.render.button;

import com.sagui.ext.common.render.IComponentRender;
import com.sagui.ext.common.render.generic.FatuGenericPropertyRender;
import com.sagui.model.button.FatuButton;

public class JextButtonLabelRender extends FatuGenericPropertyRender<FatuButton> implements IComponentRender<FatuButton> {

	public JextButtonLabelRender() {
		super(FatuButton.class, "label", "text", "setText", String.class);
	}

}
