package com.fatuhiva.ext.render.grid.store;

import com.fatuhiva.ext.common.render.IComponentRender;
import com.fatuhiva.ext.common.render.RenderException;
import com.fatuhiva.ext.common.render.util.RenderWriter;
import com.fatuhiva.model.FatuComponent;

public class FatuExtStoreModelNameRender<T extends FatuComponent> implements IComponentRender<T> {

    @Override
    public boolean render(T component, RenderWriter out) throws RenderException {
        try {
            out.writeConfigAsString("model", "ModelFor_" + component.getId()).ln();
        } catch (Exception e) {
            throw new RenderException(e);
        }
        return true;
    }

    @Override
    public void update(T component, RenderWriter out) throws RenderException {
        // TODO Auto-generated method stub

    }

}
