package com.fatuhiva.touch.render.button;

import com.fatuhiva.ext.common.render.IComponentRender;
import com.fatuhiva.model.button.FatuButton;
import com.fatuhiva.touch.render.generic.JextGenericPropertyRender;

public class JextButtonLabelRender extends JextGenericPropertyRender<FatuButton> implements IComponentRender<FatuButton> {

	public JextButtonLabelRender() {
		super(FatuButton.class, "label", "text", "setText", String.class);
	}

}
