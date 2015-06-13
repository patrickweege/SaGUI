package com.fatuhiva.touch.controller.grid;

import com.fatuhiva.model.FatuComponent;
import com.fatuhiva.model.datamodel.IFatuTableModel;
import com.fatuhiva.model.grid.FatuGrid;
import com.fatuhiva.touch.controller.IActionResult;
import com.fatuhiva.touch.controller.IJextAction;
import com.fatuhiva.touch.controller.IJextController;

public class JextGridController implements IJextController<FatuGrid> {

    public enum JextGridEvent {
        GETDATA
    }

    @Override
    public IActionResult execute(IJextAction<FatuGrid> action) {
        FatuComponent cmp = action.getTarget();
        if (cmp instanceof FatuGrid) {
            if ("GETDATA".equalsIgnoreCase(action.getEvent())) {
                return executeGetData(action);
            }
        }
        throw new IllegalStateException("Cannot execute Action");
    }

    private IActionResult executeGetData(IJextAction<FatuGrid> action) {
        FatuGrid target = action.getTarget();
        IFatuTableModel<?> dataModel = target.getTableModel();
        int start = Integer.parseInt((String)action.getParameter("start"));
        int limit = Integer.parseInt((String)action.getParameter("limit"));
        //http://www.sencha.com/forum/remote_topics/index.php?_dc=1360064313625&page=5&start=400&limit=100&sort=threadid&dir=ASC&callback=Ext.data.JsonP.callback5
        
        GetdataResult result = new GetdataResult(start, start + limit, dataModel);
        return result;
    }

}
