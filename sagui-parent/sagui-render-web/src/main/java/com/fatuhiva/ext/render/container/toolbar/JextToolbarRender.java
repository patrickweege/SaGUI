package com.fatuhiva.ext.render.container.toolbar;

import com.fatuhiva.ext.common.render.generic.FatuAbstractRender;
import com.fatuhiva.ext.render.container.JextChildrenRender;
import com.fatuhiva.model.container.toolbar.FatuToolbar;


@SuppressWarnings("rawtypes")
public class JextToolbarRender extends FatuAbstractRender<FatuToolbar>{

    public JextToolbarRender() {
        super("Ext.toolbar.Toolbar");
        super.composite.addRender(new JextChildrenRender<FatuToolbar>());
    }

}
