package com.fatuhiva.ext.controller.grid;

import java.awt.Color;

import com.fatuhiva.ext.controller.IActionResult;
import com.fatuhiva.model.datamodel.IFatuCellRender;
import com.fatuhiva.model.datamodel.IFatuColumnModel;
import com.fatuhiva.model.datamodel.IFatuTableModel;
import com.fatuhiva.model.grid.FatuGrid;
import com.fatuhiva.model.selection.IFatuSelectionModel;
import com.tuamotu.commons.dataset.IBookmark;
import com.tuamotu.commons.field.IField;

public class GridGetdataActionResult implements IActionResult {

    private final IFatuTableModel<?> tableModel;
    private final IFatuSelectionModel<IBookmark<?>> selectionModel;
    private final IFatuColumnModel columnModel;
    private final FatuGrid grid;
    private final int from;
    private final int to;

    public <T> GridGetdataActionResult(int from, int to, FatuGrid grid) {
        this.tableModel = grid.getTableModel();
        this.selectionModel = grid.getSelectionModel();
        this.columnModel = grid.getColumnModel();
        this.grid = grid;
        this.from = from;
        this.to = to;
    }

    public int getTotalCount() {
        return tableModel.getRowCount();
    }

    public int getPageCount() {
        if (from > tableModel.getRowCount()) {
            return 0;
        } else if (to > tableModel.getRowCount()) {
            return tableModel.getRowCount() - from;
        } else {
            return to - from;
        }
    }

    public int getColumnCount() {
        return columnModel.getColumnCount();
    }

    public String getColumnName(int index) {
        this.assertColumnVisible(index);
        return columnModel.getColumnName(index);
    }
    
    public Object getData(int row, int col) {
        this.assertColumnVisible(col);
        int abs = absolute(row);
        IField iField = tableModel.getColumn(col);
        return tableModel.getValueAt(abs, iField);
    }

    public boolean isSelected(int row) {
        int abs = absolute(row);
        IBookmark<?> bookmark = tableModel.getBookmark(abs);
        boolean selected = selectionModel.isSelected(bookmark);
        return selected;
    }

    public String getRowUUID(int row) {
        int abs = absolute(row);
        IBookmark<?> bookmark = tableModel.getBookmark(abs);
        return bookmark.getUUID();
    }

    public Color getBackgroundColor(int row, int col) {
        Color color = null;
        int abs = absolute(row);
        IFatuCellRender cellRender = this.getCellRender(col);
        if (cellRender != null) {
            color = cellRender.getbackgroundColor(grid, abs, col, getData(row, col));
        }
        return color;
    }

    public Color getColor(int row, int col) {
        Color color = null;
        int abs = absolute(row);
        IFatuCellRender cellRender = this.getCellRender(col);
        if (cellRender != null) {
            color = cellRender.getColor(grid, abs, col, getData(row, col));
        }
        return color;
    }

    public boolean isVisible(int index) {
        return columnModel.isVisible(index);
    }
    
    private IFatuCellRender getCellRender(int col) {
        this.assertColumnVisible(col);
        return columnModel.getCellRenderer(col);
    }

    private int absolute(int relative) {
        return relative + from;
    }

    private void assertColumnVisible(int index) {
        if(!columnModel.isVisible(index)) {
            throw new RuntimeException(String.format("Column %1$s os nor Visile", index));
        }
    }
    
    @Override
    public String toString() {
        return "from: " + from + "to: " + to + " pageCount: " + getPageCount() + " totalCount: " + getTotalCount();
    }

}
