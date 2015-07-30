package com.sagui.ext.render.grid.store;

import com.sagui.ext.common.render.RenderException;
import com.sagui.ext.common.render.generic.FatuObjectPropertyRender;
import com.sagui.ext.common.render.generic.FatuUnmodifiablePropertyRender;
import com.sagui.ext.common.render.util.RenderWriter;
import com.sagui.model.feature.IFatuColumnModelFeature;
import com.sagui.model.feature.IFatuTableModelFeature;

public class FatuExtModelConfigRender<T extends IFatuTableModelFeature & IFatuColumnModelFeature> extends FatuObjectPropertyRender<T> {

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public FatuExtModelConfigRender() {
        super("fatuModelConfig");
        super.composite.addRender(new FatuExtModelFieldsRender<T>());
        super.composite.addRender(new FatuUnmodifiablePropertyRender("idProperty","'2'"));
    }

    @Override
    public void update(T component, RenderWriter out) throws RenderException {
    }

}
