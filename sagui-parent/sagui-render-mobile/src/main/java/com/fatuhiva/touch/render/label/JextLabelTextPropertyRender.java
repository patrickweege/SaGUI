package com.fatuhiva.touch.render.label;

import org.apache.commons.lang.StringUtils;

import com.fatuhiva.model.label.FatuLabel;
import com.fatuhiva.touch.render.generic.JextGenericPropertyRender;

public class JextLabelTextPropertyRender extends JextGenericPropertyRender<FatuLabel> {

	public JextLabelTextPropertyRender() {
		super(FatuLabel.class, "label", "html", "setHtml", String.class);
		super.setRenderIffNull(true);
		super.setDefaultIfNull(StringUtils.EMPTY);
	}

}
