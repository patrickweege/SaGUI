package com.fatuhiva.touch.controller.grid;

import com.fatuhiva.model.datamodel.IFatuTableModel;
import com.fatuhiva.touch.controller.IActionResult;

public class GetdataResult implements IActionResult {

    @SuppressWarnings("rawtypes")
    private final IFatuTableModel tableModel;
    private final int from;
    private final int to;

    public GetdataResult(int from, int to, IFatuTableModel<?> tableModel) {
        this.tableModel = tableModel;
        this.from = from;
        this.to = to;
    }

    public int getTotalCount() {
        return tableModel.getRowCount();
    }

    public int getPageCount() {
        if(from > tableModel.getRowCount()) {
          return 0;  
        } else if(to > tableModel.getRowCount()) {
            return tableModel.getRowCount() - from; 
        } else {
            return to - from;
        }
    }

    public int getColumnCount() {
        return tableModel.getColumnCount();
    }
    
    public String getColumnName(int index) {
        return tableModel.getColumnName(index);
    }

    public Object getData(int row, int col) {
        int abs = absolute(row);
        return tableModel.getValueAt(abs, col);
    }
    
    public boolean isSelected(int row) {
        return false;
    }

    private int absolute(int relative) {
        return relative + from;
    }

    @Override
    public String toString() {
        return "from: " + from + "to: " + to + " pageCount:" + getPageCount() + " totalCount: " + getTotalCount();
    }
    
    
}
