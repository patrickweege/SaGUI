package com.sagui.ext.render.label;

import com.sagui.ext.common.render.generic.FatuAbstractRender;
import com.sagui.model.label.FatuLabel;

public class JextLabelRender extends  FatuAbstractRender<FatuLabel> {

    public JextLabelRender() {
        super("Ext.form.Label");
        super.composite.addRender(new JextLabelTextPropertyRender());
    }

}
