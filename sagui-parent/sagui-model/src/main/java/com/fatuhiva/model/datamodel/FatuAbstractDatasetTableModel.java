package com.fatuhiva.model.datamodel;

import java.util.Collection;
import java.util.Collections;

import com.sagui.dataset.commons.dataset.DatasetIndex;
import com.sagui.dataset.commons.dataset.IBookmark;
import com.sagui.dataset.commons.dataset.IDataset;
import com.sagui.dataset.commons.field.IField;

public abstract class FatuAbstractDatasetTableModel<T> extends FatuAbstractTableModel<T> implements IFatuTableModel<T> {

    protected abstract IDataset<T> getDataset();
    protected abstract DatasetIndex<T> getDatasetIndex();

    public FatuAbstractDatasetTableModel() {
        super();
    }

    
    @Override
    public final int getRowCount() {
        IDataset<T> theDataset = this.getDataset();
        return theDataset.getRecordCount();
    }
    
    @Override
    public final void remove(IBookmark<T> toRemove) {
        if (toRemove == null) return;
        IDataset<T> theDataset = this.getDataset();
        DatasetIndex<T> theIndex = this.getDatasetIndex();
        int rowIndex = theDataset.indexOf(toRemove, theIndex);
        if (rowIndex >= 0) {
            T removed = theDataset.remove(toRemove);
            if (removed != null) {
                FatuTableModelEvent evt = new FatuTableModelEvent(this, -1, rowIndex, rowIndex, Collections.singleton(removed), FatuTableModelEvent.FatuTableModelEventType.REMOVE);
                this.fireTableModelListeners(evt);
            }
        }
    }

    @Override
    public final IBookmark<T> insert(T toInsert) {
        if (toInsert == null) return null;
        IDataset<T> theDataset = this.getDataset();
        IBookmark<T> added = theDataset.add(toInsert);
        DatasetIndex<T> theIndex = this.getDatasetIndex();
        int insertedIndex = theDataset.indexOf(added, theIndex);

        FatuTableModelEvent evt = new FatuTableModelEvent(this, -1, insertedIndex, insertedIndex, Collections.singleton(toInsert), FatuTableModelEvent.FatuTableModelEventType.INSERT);
        this.fireTableModelListeners(evt);

        return added;
    }

    @Override
    public final IBookmark<T> getBookmark(int index) {
        IDataset<T> theDataset = this.getDataset();
        DatasetIndex<T> theIndex = this.getDatasetIndex();
        IBookmark<T> bkm = theDataset.getBookmark(index, theIndex);
        return bkm;
    }

    @Override
    public final IBookmark<T> getBookmark(String bookmarkUUID) {
        IDataset<T> dataset = this.getDataset();
        return dataset.getBookmark(bookmarkUUID);
    }

    @Override
    public final int getRowIndex(IBookmark<T> bookmark) {
        IDataset<T> theDataset = this.getDataset();
        DatasetIndex<T> theIndex = this.getDatasetIndex();
        return theDataset.indexOf(bookmark, theIndex);
    }

    @Override
    public final <V> V getValueAt(int row, IField<T> field) {
        IBookmark<T> bookmark = this.getBookmark(row);
        return getValueAt(bookmark, field);
    }

    @Override
    public final <V> V getValueAt(IBookmark<T> bookmark, IField<T> field) {
        IDataset<T> dataset = getDataset();
        return dataset.getValue(bookmark, field);
    }
    
    protected final void setData(Collection<T> newData, boolean silent) {
        IDataset<T> dataset = this.getDataset();
        dataset.clear();
        for (T bean : newData) {
            dataset.add(bean);
        }

        if (!silent) {
            FatuTableModelEvent evt = new FatuTableModelEvent(this, -1, 0, dataset.getRecordCount() - 1, newData, FatuTableModelEvent.FatuTableModelEventType.DATA_RESET);
            super.fireTableModelListeners(evt);
        }
    }

    protected <V> void setValueAt(V value, int row, IField<T> field) {
        IDataset<T> theDataset = this.getDataset();
        DatasetIndex<T> theIndex = this.getDatasetIndex();
        IBookmark<T> bookmark = theDataset.getBookmark(row, theIndex);
        theDataset.setValue(bookmark, field, value);
        int column = getColumnIndex(field);

        int newRowIndex = theDataset.indexOf(bookmark, theIndex);

        FatuTableModelEvent evt = new FatuTableModelEvent(this, column, newRowIndex, newRowIndex, bookmark, FatuTableModelEvent.FatuTableModelEventType.UPDATE);
        super.fireTableModelListeners(evt);
    }

}
