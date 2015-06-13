package com.fatuhiva.touch.render.textbox;

import org.apache.commons.lang.StringUtils;

import com.fatuhiva.model.editable.editbox.FatuTextBox;
import com.fatuhiva.touch.render.generic.JextGenericPropertyRender;

public class JextTextBoxValueRender extends JextGenericPropertyRender<FatuTextBox> {

	public JextTextBoxValueRender() {
		super(FatuTextBox.class, "value", "value", "setValue", String.class);
		super.setRenderIffNull(true);
		super.setDefaultIfNull(StringUtils.EMPTY);
	}

}
