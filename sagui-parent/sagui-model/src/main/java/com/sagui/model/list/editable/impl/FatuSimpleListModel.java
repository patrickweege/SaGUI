package com.sagui.model.list.editable.impl;

import java.util.Collection;

import com.sagui.dataset.commons.comparator.BeanComparatorUtil;
import com.sagui.dataset.commons.comparator.IBeanComparator;
import com.sagui.dataset.commons.comparator.IFieldComparatorMetadata;
import com.sagui.dataset.commons.comparator.IBeanComparator.Order;
import com.sagui.dataset.commons.dataset.Dataset;
import com.sagui.dataset.commons.dataset.DatasetIndex;
import com.sagui.dataset.commons.dataset.IDataset;
import com.sagui.dataset.commons.field.AbstractField;
import com.sagui.dataset.commons.field.IField;
import com.sagui.model.FatuElement;
import com.sagui.model.datamodel.FatuAbstractDatasetTableModel;
import com.sagui.model.list.IFatuListModel;

@SuppressWarnings("unchecked")
public class FatuSimpleListModel<T extends FatuElement> extends FatuAbstractDatasetTableModel<T> implements IFatuListModel<T> {

    public final IField<T> keyField;
    public final IField<T> textField;

    private final Dataset<T> dataset;
    private final DatasetIndex<T> datasetIndex;

    public FatuSimpleListModel() {
        this((IField<T>) new InternalJextElementIDField(), (IField<T>) new InternalJextElementToStringField());
    }

    public FatuSimpleListModel(IField<T> keyField, IField<T> textField) {
        this.dataset = new Dataset<T>();
        this.keyField = this.dataset.addField(keyField);
        this.textField = this.dataset.addField(textField);

        IBeanComparator<T> textComparator = BeanComparatorUtil.getBeanComparator(new IFieldComparatorMetadata<T>(this.textField, Order.ASC, true));
        this.datasetIndex = this.dataset.addIndex(textComparator);

        super.addField(this.keyField);
        super.addField(this.textField);
    }

    @Override
    public int getKeyColumn() {
        return getColumnIndex(keyField);
    }

    @Override
    public int getTextColumn() {
        return getColumnIndex(textField);
    }

    @Override
    public String getKey(int row) {
        return getValueAt(row, keyField);
    }

    @Override
    public String getLabel(int row) {
        return getValueAt(row, textField);
    }

    public final void setItems(Collection<T> items) {
        super.setData(items, false);
    }

    @Override
    protected IDataset<T> getDataset() {
        return this.dataset;
    }

    @Override
    protected DatasetIndex<T> getDatasetIndex() {
        return this.datasetIndex;
    }

    private final static class InternalJextElementIDField extends AbstractField<FatuElement> {

        public InternalJextElementIDField() {
            super("FIELD_ID", "JextElement.getId()", "JextElement.getId()", String.class, true);
        }

        @Override
        public <V> V getValue(FatuElement bean) {
            return (V) bean.getId();
        }

        @Override
        public <V> void setValue(FatuElement bean, V value) {
            throw new UnsupportedOperationException("Field " + getName() + " is ReadOnly");
        }

    }

    private final static class InternalJextElementToStringField extends AbstractField<FatuElement> {

        public InternalJextElementToStringField() {
            super("FIELD_TOSTRING", "JextElement.toString()", "JextElement.toString()", String.class, true);
        }

        @Override
        public <V> V getValue(FatuElement bean) {
            return (V) bean.toString();
        }

        @Override
        public <V> void setValue(FatuElement bean, V value) {
            throw new UnsupportedOperationException("Field " + getName() + " is ReadOnly");
        }

    }

}
