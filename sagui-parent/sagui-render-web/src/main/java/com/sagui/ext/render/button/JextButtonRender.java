package com.sagui.ext.render.button;

import com.sagui.ext.common.render.IComponentRender;
import com.sagui.ext.common.render.generic.FatuAbstractRender;
import com.sagui.ext.render.generic.FatuMarginFeatureRender;
import com.sagui.ext.render.generic.JextEnabledFeatureRender;
import com.sagui.ext.render.generic.JextVisibleFeatureRender;
import com.sagui.model.button.FatuButton;

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
