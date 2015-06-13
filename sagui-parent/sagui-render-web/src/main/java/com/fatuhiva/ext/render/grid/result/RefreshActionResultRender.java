package com.fatuhiva.ext.render.grid.result;

import java.io.IOException;

import com.fatuhiva.ext.common.render.IRender;
import com.fatuhiva.ext.common.render.RenderException;
import com.fatuhiva.ext.common.render.util.RenderWriter;
import com.fatuhiva.ext.controller.grid.GridRefreshActionResult;

public class RefreshActionResultRender implements IRender<GridRefreshActionResult> {

    public boolean render(GridRefreshActionResult component, RenderWriter out) throws RenderException {
        try {
            out.formatLn("Ext.Function.defer(new Fatuhiva.RefreshAction({componentID: '%1$s'}).execute, 1000);");
        } catch (IOException e) {
            throw new RenderException(e);
        }
        return true;
    }

    @Override
    public void update(GridRefreshActionResult component, RenderWriter out) throws RenderException {
    }

}
