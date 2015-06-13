package com.fatuhiva.ext.render.button;

import com.fatuhiva.ext.common.render.IComponentRender;
import com.fatuhiva.ext.common.render.generic.FatuGenericPropertyRender;
import com.fatuhiva.model.button.FatuButton;

public class JextButtonLabelRender extends FatuGenericPropertyRender<FatuButton> implements IComponentRender<FatuButton> {

	public JextButtonLabelRender() {
		super(FatuButton.class, "label", "text", "setText", String.class);
	}

}
