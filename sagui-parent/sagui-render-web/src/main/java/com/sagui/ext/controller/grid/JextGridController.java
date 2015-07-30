package com.sagui.ext.controller.grid;

import java.util.Collection;

import org.apache.commons.lang.StringUtils;

import com.pw.common.transformer.ToCollectionTransformer;
import com.sagui.dataset.commons.dataset.IBookmark;
import com.sagui.ext.controller.IActionResult;
import com.sagui.ext.controller.IJextAction;
import com.sagui.ext.controller.IJextController;
import com.sagui.model.FatuComponent;
import com.sagui.model.datamodel.IFatuTableModel;
import com.sagui.model.grid.FatuGrid;
import com.sagui.model.selection.IFatuSelectionModel;

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
