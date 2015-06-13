package com.fatuhiva.touch.render.container.toolbar;

import com.fatuhiva.model.container.toolbar.FatuToolbar;
import com.fatuhiva.touch.render.container.JextAbstractContainerRender;


@SuppressWarnings("rawtypes")
public class JextToolbarRender extends JextAbstractContainerRender<FatuToolbar>{

    public JextToolbarRender() {
        super("Ext.toolbar.Toolbar");
        //composite.addRender(new JextUnmodifiablePropertyRender<JextToolbar>("height", 30));
        //composite.addRender(new JextUnmodifiablePropertyRender<JextToolbar>("autoHeight", true));
    }

}
