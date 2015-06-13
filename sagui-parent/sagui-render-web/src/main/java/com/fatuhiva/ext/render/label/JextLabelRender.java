package com.fatuhiva.ext.render.label;

import com.fatuhiva.ext.common.render.generic.FatuAbstractRender;
import com.fatuhiva.model.label.FatuLabel;

public class JextLabelRender extends  FatuAbstractRender<FatuLabel> {

    public JextLabelRender() {
        super("Ext.form.Label");
        super.composite.addRender(new JextLabelTextPropertyRender());
    }

}
