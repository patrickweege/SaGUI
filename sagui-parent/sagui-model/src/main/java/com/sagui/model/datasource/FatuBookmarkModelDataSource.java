package com.sagui.model.datasource;

import com.sagui.dataset.commons.dataset.IBookmark;
import com.sagui.dataset.commons.field.IField;
import com.sagui.model.datamodel.FatuTableModelEvent;
import com.sagui.model.datamodel.IFatuTableModel;
import com.sagui.model.datamodel.IFatuTableModelListener;

@SuppressWarnings("unchecked")
public class FatuBookmarkModelDataSource<BEAN, V> extends FatuAbstractDataSource<V> implements IFatuTableModelListener {

    private final IFatuTableModel<BEAN> tableModel;
    private final IField<BEAN> field;

    private IBookmark<BEAN> current;

    public FatuBookmarkModelDataSource(IFatuTableModel<BEAN> tableModel, IBookmark<BEAN> bookmark,  IField<BEAN> field) {
        this.tableModel = tableModel;
        this.tableModel.addTableModelListener(this);
        this.current = bookmark;
        this.field = field;
    }
    
    public void setCurrent(IBookmark<BEAN> newCurrent) {
        this.current = newCurrent;
        this.setChanged();
        this.notifyObservers();
    }
    
    
    @Override
    public void setValue(V value) {
        if (this.isEnabled()) {
            this.tableModel.setValueAt(value, current, field);
        }
    }

    @Override
    public V getValue() {
        if (this.isEnabled() && this.current != null) {
            return (V) this.tableModel.getValueAt(current, field);
        }
        return null;
    }

    @Override
    public void tableChanged(FatuTableModelEvent evt) {
        int rowIndex = current == null ? 0 : tableModel.getRowIndex(current);
        if(evt.getFirstRow() <= rowIndex && evt.getLastRow() >= rowIndex) {
            this.setChanged();
            this.notifyObservers();
        }
    }
    
    @Override
    public boolean isEditable() {
        return super.isEditable() && current != null;
    }
    

}
