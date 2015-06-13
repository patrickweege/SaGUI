package com.fatuhiva.ext.render.button;

import com.fatuhiva.ext.common.render.IComponentRender;
import com.fatuhiva.ext.common.render.generic.FatuAbstractRender;
import com.fatuhiva.ext.render.generic.FatuMarginFeatureRender;
import com.fatuhiva.ext.render.generic.JextEnabledFeatureRender;
import com.fatuhiva.ext.render.generic.JextVisibleFeatureRender;
import com.fatuhiva.model.button.FatuButton;

public class JextButtonRender extends FatuAbstractRender<FatuButton> implements IComponentRender<FatuButton> {

	public JextButtonRender() {
	    super("Ext.Button");
	    super.composite.addRender(new JextButtonLabelRender());
        super.composite.addRender(new JextEnabledFeatureRender<FatuButton>());
        super.composite.addRender(new JextVisibleFeatureRender<FatuButton>());
        super.composite.addRender(new FatuMarginFeatureRender<FatuButton>());
	    super.composite.addRender(new JextButtonClickListenerRender());
	}

}
