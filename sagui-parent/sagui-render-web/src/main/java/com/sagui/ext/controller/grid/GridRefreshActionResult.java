package com.sagui.ext.controller.grid;

import com.sagui.ext.controller.IActionResult;
import com.sagui.model.grid.FatuGrid;


public class GridRefreshActionResult implements IActionResult {
    
    private final FatuGrid grid;

    public GridRefreshActionResult(FatuGrid toRefresh) {
        this.grid = toRefresh;
    }

    public String getComponentID() {
        return this.grid.getId();
    }
}
