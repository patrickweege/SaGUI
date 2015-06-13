package com.fatuhiva.touch.render.grid.store;

import com.fatuhiva.ext.common.render.IRender;
import com.fatuhiva.ext.common.render.RenderException;
import com.fatuhiva.ext.common.render.util.RenderWriter;
import com.fatuhiva.model.datamodel.IFatuTableModel;


@SuppressWarnings("rawtypes")
public class JextStoreProxyPropertyRender implements IRender<IFatuTableModel> {

    @Override
    public boolean render(IFatuTableModel component, RenderWriter out) throws RenderException {
        return false;
    }

    @Override
    public void update(IFatuTableModel component, RenderWriter out) throws RenderException {
        // TODO Auto-generated method stub
    }


}
