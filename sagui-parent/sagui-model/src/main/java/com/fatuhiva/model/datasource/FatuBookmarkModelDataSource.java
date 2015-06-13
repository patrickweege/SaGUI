package com.fatuhiva.model.datasource;

import com.fatuhiva.model.datamodel.FatuTableModelEvent;
import com.fatuhiva.model.datamodel.IFatuTableModel;
import com.fatuhiva.model.datamodel.IFatuTableModelListener;
import com.tuamotu.commons.dataset.IBookmark;
import com.tuamotu.commons.field.IField;

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
        if (this.isEnabled()) {
            return (V) this.tableModel.getValueAt(current, field);
        }
        return null;
    }

    @Override
    public void tableChanged(FatuTableModelEvent evt) {
        int rowIndex = tableModel.getRowIndex(current);
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
