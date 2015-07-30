package com.sagui.ext.render.label;

import org.apache.commons.lang.StringUtils;

import com.sagui.ext.common.render.generic.FatuGenericPropertyRender;
import com.sagui.model.label.FatuLabel;

public class JextLabelTextPropertyRender extends FatuGenericPropertyRender<FatuLabel> {

	public JextLabelTextPropertyRender() {
		super(FatuLabel.class, "label", "text", "setText", String.class);
		super.setRenderIffNull(true);
		super.setDefaultIfNull(StringUtils.EMPTY);
	}

}
