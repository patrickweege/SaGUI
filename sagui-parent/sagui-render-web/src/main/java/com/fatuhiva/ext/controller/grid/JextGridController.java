package com.fatuhiva.ext.controller.grid;

import java.util.Collection;

import org.apache.commons.lang.StringUtils;

import com.fatuhiva.ext.controller.IActionResult;
import com.fatuhiva.ext.controller.IJextAction;
import com.fatuhiva.ext.controller.IJextController;
import com.fatuhiva.model.FatuComponent;
import com.fatuhiva.model.datamodel.IFatuTableModel;
import com.fatuhiva.model.grid.FatuGrid;
import com.fatuhiva.model.selection.IFatuSelectionModel;
import com.pw.common.transformer.ToCollectionTransformer;
import com.tuamotu.commons.dataset.IBookmark;

public class JextGridController implements IJextController<FatuGrid> {

    public enum JextGridEvent {
        GETDATA
    }

    @Override
    public IActionResult execute(IJextAction<FatuGrid> action) {
        FatuComponent cmp = action.getTarget();
        String event = action.getEvent();
        if (cmp instanceof FatuGrid) {
            if (StringUtils.equalsIgnoreCase("GETDATA", event)) {
                return executeGetData(action);
            } else if (StringUtils.equalsIgnoreCase("selectionchange", event)) {
                return executeSetSelection(action);
            }
        }
        throw new IllegalStateException("Cannot execute Action");
    }

    private IActionResult executeGetData(IJextAction<FatuGrid> action) {
        FatuGrid target = action.getTarget();
        int start = Integer.parseInt((String) action.getParameter("start"));
        int limit = Integer.parseInt((String) action.getParameter("limit"));
        //http://www.sencha.com/forum/remote_topics/index.php?_dc=1360064313625&page=5&start=400&limit=100&sort=threadid&dir=ASC&callback=Ext.data.JsonP.callback5

        GridGetdataActionResult result = new GridGetdataActionResult(start, start + limit, target);
        return result;
    }

    private IActionResult executeSetSelection(IJextAction<FatuGrid> action) {
        FatuGrid target = action.getTarget();
        IFatuTableModel<?> tableModel = target.getTableModel();

        IFatuSelectionModel<IBookmark<?>> selectionModel = target.getSelectionModel();
        Collection<String> selection = action.getParameter("rowUUIDs", new ToCollectionTransformer<String>());
        selectionModel.clearSelection();
        for (String rowUUID : selection) {
            IBookmark<?> bookmark = tableModel.getBookmark(rowUUID);
            selectionModel.select(bookmark);
        }
        return null;
    }

}
