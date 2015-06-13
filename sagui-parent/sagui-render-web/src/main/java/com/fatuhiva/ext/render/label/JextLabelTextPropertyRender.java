package com.fatuhiva.ext.render.label;

import org.apache.commons.lang.StringUtils;

import com.fatuhiva.ext.common.render.generic.FatuGenericPropertyRender;
import com.fatuhiva.model.label.FatuLabel;

public class JextLabelTextPropertyRender extends FatuGenericPropertyRender<FatuLabel> {

	public JextLabelTextPropertyRender() {
		super(FatuLabel.class, "label", "text", "setText", String.class);
		super.setRenderIffNull(true);
		super.setDefaultIfNull(StringUtils.EMPTY);
	}

}
