package com.sagui.model.datasource;

import java.math.BigDecimal;
import java.util.Collection;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.lang.math.NumberUtils;

import com.sagui.dataset.commons.dataset.IBookmark;
import com.sagui.dataset.commons.field.IField;
import com.sagui.model.datamodel.FatuTableModelEvent;
import com.sagui.model.datamodel.IFatuTableModel;
import com.sagui.model.datamodel.IFatuTableModelListener;
import com.sagui.model.selection.IFatuSelectionListener;
import com.sagui.model.selection.IFatuSelectionModel;

public class FatuSelectedDataModelDataSource<BEAN, V> extends FatuAbstractDataSource<V> implements IFatuTableModelListener, IFatuSelectionListener<IBookmark<BEAN>> {

    private final IField<BEAN> field;
    private final IFatuTableModel<BEAN> tableModel;
    private final IFatuSelectionModel<IBookmark<BEAN>> selModel;
    private final Converter converter;

    private Class<V> outType;

    public FatuSelectedDataModelDataSource(Class<V> outType, IField<BEAN> field, IFatuTableModel<BEAN> tableModel, IFatuSelectionModel<IBookmark<BEAN>> selectionModel) {
        this.field = field;
        this.tableModel = tableModel;
        this.selModel = selectionModel;
        this.outType = outType;
        ConvertUtils.register(new BigDecimalConverter(), BigDecimal.class);
        this.converter = ConvertUtils.lookup(field.getFieldClass());
        this.selModel.addSelectionListener(this);
        this.tableModel.addTableModelListener(this);

    }


    @Override
    @SuppressWarnings("unchecked")
    public void setValue(V value) {
        if (this.isEnabled()) {
            if (selModel.getSelectedCount() == 1) {
                Collection<IBookmark<BEAN>> selection = selModel.getSelection();
                for (IBookmark<BEAN> selected : selection) {
                    V convertedValue = value;
                    if (converter != null) {
                        convertedValue = (V) converter.convert(field.getFieldClass(), value);
                    }
                    tableModel.setValueAt(convertedValue, selected, field);
                }
            }
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public V getValue() {
        V theValue = null;
        if (this.isEnabled()) {
            Collection<IBookmark<BEAN>> selection = selModel.getSelection();
            if (selection.size() == 1) {
                IBookmark<BEAN> current = selection.iterator().next();
                theValue = this.tableModel.getValueAt(current, field);
                if (converter != null) {
                    theValue = (V) converter.convert(outType, theValue);
                }
            }
        }
        return theValue;
    }

    @Override
    public void tableChanged(FatuTableModelEvent evt) {
        int min = 0;
        int max = 0;
        Collection<IBookmark<BEAN>> selection = selModel.getSelection();
        for (IBookmark<BEAN> bkm : selection) {
            int rowIndex = tableModel.getRowIndex(bkm);
            max = NumberUtils.max(max, rowIndex, 0);
            min = NumberUtils.min(min, rowIndex, max);
        }
    }

    @Override
    public boolean isEditable() {
        return super.isEditable() && !field.isReadOnly() && selModel.getSelectedCount() == 1;
    }

    @Override
    public void selectionChanged(Collection<IBookmark<BEAN>> newSelection, Collection<IBookmark<BEAN>> oldSelection) {
        this.setChanged();
        this.notifyObservers();
    }

}
