package com.sagui.model.datamodel;

import com.sagui.dataset.commons.dataset.IBookmark;
import com.sagui.dataset.commons.field.IField;

public interface IFatuTableModel<T> {

    public int getColumnCount();

    public IField<T> getColumn(int column);
    
    public int getColumnIndex(IField<T> field);
    
    public int getRowCount();

    public void remove(IBookmark<T> toRemove);

    public IBookmark<T> insert(T toAdd);

    public IBookmark<T> getBookmark(int row);

    public IBookmark<T> getBookmark(String bookmarktUUID);

    public int getRowIndex(IBookmark<T> bookmark);

    public <V> void setValueAt(V aValue, int rowIndex, int columnIndex);

    public <V> void setValueAt(V value, IBookmark<T> bookmark, IField<T> field);

    public <V> V getValueAt(int row, int columnIndex);

    public <V> V getValueAt(int row, IField<T> field);

    public <V> V getValueAt(IBookmark<T> bookmark, IField<T> field);
    
    /**
     * Adds a listener to the list that is notified each time a change to the data model occurs.
     * 
     * @param l the IFatuTableModelListener
     */
    public void addTableModelListener(IFatuTableModelListener l);

    /**
     * Removes a listener from the list that is notified each time a change to the data model occurs.
     * 
     * @param l the IFatuTableModelListener
     */
    public void removeTableModelListener(IFatuTableModelListener l);

}
