package com.fatuhiva.touch.render.label;

import com.fatuhiva.model.label.FatuLabel;
import com.fatuhiva.touch.render.generic.JextAbstractRender;

public class JextLabelRender extends  JextAbstractRender<FatuLabel> {

    public JextLabelRender() {
        super("Ext.Label");
        super.composite.addRender(new JextLabelTextPropertyRender());
    }

}
