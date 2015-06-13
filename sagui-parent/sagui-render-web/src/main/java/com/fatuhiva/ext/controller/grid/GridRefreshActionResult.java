package com.fatuhiva.ext.controller.grid;

import com.fatuhiva.ext.controller.IActionResult;
import com.fatuhiva.model.grid.FatuGrid;


public class GridRefreshActionResult implements IActionResult {
    
    private final FatuGrid grid;

    public GridRefreshActionResult(FatuGrid toRefresh) {
        this.grid = toRefresh;
    }

    public String getComponentID() {
        return this.grid.getId();
    }
}
