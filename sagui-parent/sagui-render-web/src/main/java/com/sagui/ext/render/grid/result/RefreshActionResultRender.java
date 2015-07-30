package com.sagui.ext.render.grid.result;

import java.io.IOException;

import com.sagui.ext.common.render.IRender;
import com.sagui.ext.common.render.RenderException;
import com.sagui.ext.common.render.util.RenderWriter;
import com.sagui.ext.controller.grid.GridRefreshActionResult;

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
