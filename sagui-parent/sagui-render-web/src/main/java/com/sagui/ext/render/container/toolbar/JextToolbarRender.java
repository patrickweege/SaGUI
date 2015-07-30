package com.sagui.ext.render.container.toolbar;

import com.sagui.ext.common.render.generic.FatuAbstractRender;
import com.sagui.ext.render.container.JextChildrenRender;
import com.sagui.model.container.toolbar.FatuToolbar;


@SuppressWarnings("rawtypes")
public class JextToolbarRender extends FatuAbstractRender<FatuToolbar>{

    public JextToolbarRender() {
        super("Ext.toolbar.Toolbar");
        super.composite.addRender(new JextChildrenRender<FatuToolbar>());
    }

}
