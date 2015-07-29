package com.fatuhiva.model.datasource;

import com.fatuhiva.model.datamodel.FatuTableModelEvent;
import com.fatuhiva.model.datamodel.IFatuTableModel;
import com.fatuhiva.model.datamodel.IFatuTableModelListener;
import com.sagui.dataset.commons.dataset.IBookmark;
import com.sagui.dataset.commons.field.IField;

@SuppressWarnings("unchecked")
public class FatuRowIndexDataModelDataSource<BEAN, V> extends FatuAbstractDataSource<V> implements IFatuTableModelListener {

    private final IFatuTableModel<BEAN> tableModel;
    private final IField<BEAN> field;

    private int rowIndex;

    public FatuRowIndexDataModelDataSource(IFatuTableModel<BEAN> tableModel, int rowIndex, IField<BEAN> field) {
        this.tableModel = tableModel;
        this.tableModel.addTableModelListener(this);
        this.rowIndex = rowIndex;
        this.field = field;
    }

    public void setRowIndex(int newRowIndex) {
        this.rowIndex = newRowIndex;
        this.setChanged();
        this.notifyObservers();
    }

    @Override
    public void setValue(V value) {
        if (this.isEnabled()) {
            IBookmark<BEAN> theBkm = this.tableModel.getBookmark(rowIndex);
            this.tableModel.setValueAt(value, theBkm, field);
        }
    }

    @Override
    public V getValue() {
        if (this.isEnabled()) {
            IBookmark<BEAN> theBkm = this.tableModel.getBookmark(rowIndex);
            return (V) this.tableModel.getValueAt(theBkm, field);
        }
        return null;
    }

    @Override
    public void tableChanged(FatuTableModelEvent evt) {
        //int rowIndex = tableModel.getRowIndex(current);
        if (evt.getFirstRow() <= rowIndex && evt.getLastRow() >= rowIndex) {
            this.setChanged();
            this.notifyObservers();
        }
    }

    @Override
    public boolean isEditable() {
        return super.isEditable() && rowIndex >= 0;
    }

}
