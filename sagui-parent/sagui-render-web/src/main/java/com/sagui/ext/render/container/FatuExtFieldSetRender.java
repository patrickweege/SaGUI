package com.sagui.ext.render.container;

import com.sagui.ext.common.render.generic.FatuAbstractRender;
import com.sagui.ext.common.render.generic.FatuGenericPropertyRender;
import com.sagui.ext.render.generic.BodyPaddingFeatureRender;
import com.sagui.ext.render.generic.FatuMarginFeatureRender;
import com.sagui.ext.render.generic.JextSizeFeatureRender;
import com.sagui.ext.render.layout.FatuExtLayoutRender;
import com.sagui.model.container.field.FatuFieldSet;

@SuppressWarnings("rawtypes")
public class FatuExtFieldSetRender extends FatuAbstractRender<FatuFieldSet> {

    private static final FatuGenericPropertyRender<FatuFieldSet> TITLE_PROP_RENDER;

    static {
        TITLE_PROP_RENDER = new FatuGenericPropertyRender<FatuFieldSet>(FatuFieldSet.class, "title", "title", null, String.class);
        TITLE_PROP_RENDER.setRenderIffNull(false);
    }

    public FatuExtFieldSetRender() {
        super("Ext.form.FieldSet");
        composite.addRender(TITLE_PROP_RENDER);
        
        super.composite.addRender(new FatuExtLayoutRender());
        super.composite.addRender(new FatuMarginFeatureRender<FatuFieldSet>());
        super.composite.addRender(new BodyPaddingFeatureRender<FatuFieldSet>());
        super.composite.addRender(new JextSizeFeatureRender<FatuFieldSet>());
        super.composite.addRender(new JextChildrenRender<FatuFieldSet>());

        //super.composite.addRender(new AnyLayoutRender<FatuFieldSet>());
    }

}
