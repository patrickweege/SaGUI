package com.fatuhiva.touch.render.button;

import com.fatuhiva.ext.common.render.IComponentRender;
import com.fatuhiva.model.button.FatuButton;
import com.fatuhiva.touch.render.generic.JextAbstractRender;

public class JextButtonRender extends JextAbstractRender<FatuButton> implements IComponentRender<FatuButton> {

	public JextButtonRender() {
	    super("Ext.Button");
	    super.composite.addRender(new JextButtonLabelRender());
//        super.composite.addRender(new JextEnabledFeatureRender<JextButton>());
//        super.composite.addRender(new JextVisibleFeatureRender<JextButton>());
	    super.composite.addRender(new JextButtonClickListenerRender());
	}

}
