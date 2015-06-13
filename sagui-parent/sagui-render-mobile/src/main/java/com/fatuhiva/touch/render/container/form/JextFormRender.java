package com.fatuhiva.touch.render.container.form;

import com.fatuhiva.ext.common.render.RenderException;
import com.fatuhiva.ext.common.render.util.RenderWriter;
import com.fatuhiva.model.container.form.FatuForm;
import com.fatuhiva.touch.render.container.JextChildrenRender;
import com.fatuhiva.touch.render.generic.JextAbstractRender;
import com.fatuhiva.touch.render.generic.JextSizeFeatureRender;
import com.fatuhiva.touch.render.generic.JextTitleRender;
import com.fatuhiva.touch.render.layout.AnyLayoutRender;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class JextFormRender extends JextAbstractRender<FatuForm> {

    public JextFormRender() {
        super("Ext.form.Panel");
        // Properties
        super.composite.addRender(new JextTitleRender());
        super.composite.addRender(new JextSizeFeatureRender());
        // Layout and Layout Rules
        super.composite.addRender(new AnyLayoutRender<FatuForm>());
        // Children
        super.composite.addRender(new JextChildrenRender<FatuForm>());
    }

    @Override
    public boolean render(FatuForm component, RenderWriter out) throws RenderException {
        return super.render(component, out);
    }

}
