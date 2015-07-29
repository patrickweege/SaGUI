package com.fatuhiva.model.datamodel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.EventListenerList;

import com.sagui.dataset.commons.dataset.IBookmark;
import com.sagui.dataset.commons.field.IField;

public abstract class FatuAbstractTableModel<T> implements IFatuTableModel<T> {

    protected final EventListenerList listeners;
    protected final List<IField<T>> fields;

    public FatuAbstractTableModel() {
        this.listeners = new EventListenerList();
        this.fields = new ArrayList<IField<T>>();
    }

    @Override
    public final <V> void setValueAt(V value, IBookmark<T> bookmark, IField<T> field) {
        int rowIndex = getRowIndex(bookmark);
        this.setValueAt(value, rowIndex, field);
    }
    
    @Override
    public final <V> void setValueAt(V aValue, int rowIndex, int columnIndex) {
        IField<T> field = getField(columnIndex);
        this.setValueAt(aValue, rowIndex, field);
    }

    @Override
    public final <V> V getValueAt(int rowIndex, int columnIndex) {
        IField<T> field = getField(columnIndex);
        return this.getValueAt(rowIndex, field);
    }
    
    @Override
    public final int getColumnCount() {
        return fields == null ? 0 : fields.size();
    }

    @Override
    public final IField<T> getColumn(int column) {
        return getField(column);
    }

    @Override
    public final int getColumnIndex(IField<T> field) {
        return fields.indexOf(field);
    }

    @Override
    public final void addTableModelListener(IFatuTableModelListener l) {
        // Remove if Exists to prevent Multiple Instances
        this.listeners.remove(IFatuTableModelListener.class, l);
        // Add Add The Listener
        this.listeners.add(IFatuTableModelListener.class, l);
    }

    @Override
    public final void removeTableModelListener(IFatuTableModelListener l) {
        this.listeners.remove(IFatuTableModelListener.class, l);
    }

    @SafeVarargs
    protected final void addField(IField<T>... field) {
        for (IField<T> iField : field) {
            fields.add(iField);
        }
    }

    protected abstract <V> void setValueAt(V value, int row, IField<T> field);

    protected final IField<T> getField(int index) {
        return fields.get(index);
    }

    protected final void fireTableModelListeners(FatuTableModelEvent evt) {
        IFatuTableModelListener[] toFire = listeners.getListeners(IFatuTableModelListener.class);
        for (IFatuTableModelListener l : toFire) {
            l.tableChanged(evt);
        }
    }

}
