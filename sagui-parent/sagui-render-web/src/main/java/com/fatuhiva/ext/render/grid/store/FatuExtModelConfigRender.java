package com.fatuhiva.ext.render.grid.store;

import com.fatuhiva.ext.common.render.RenderException;
import com.fatuhiva.ext.common.render.generic.FatuObjectPropertyRender;
import com.fatuhiva.ext.common.render.generic.FatuUnmodifiablePropertyRender;
import com.fatuhiva.ext.common.render.util.RenderWriter;
import com.fatuhiva.model.feature.IFatuColumnModelFeature;
import com.fatuhiva.model.feature.IFatuTableModelFeature;

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
