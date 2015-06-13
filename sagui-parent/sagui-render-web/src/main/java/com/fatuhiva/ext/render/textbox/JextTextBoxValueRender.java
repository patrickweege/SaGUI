package com.fatuhiva.ext.render.textbox;

import org.apache.commons.lang.StringUtils;

import com.fatuhiva.ext.common.render.generic.FatuGenericPropertyRender;
import com.fatuhiva.model.editable.editbox.FatuTextBox;

public class JextTextBoxValueRender extends FatuGenericPropertyRender<FatuTextBox> {

	public JextTextBoxValueRender() {
		super(FatuTextBox.class, "value", "value", "setValue", String.class);
		super.setRenderIffNull(true);
		super.setDefaultIfNull(StringUtils.EMPTY);
	}

}
