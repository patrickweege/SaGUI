package com.fatuhiva.model.datamodel.impl;

import com.fatuhiva.model.datamodel.FatuAbstractDatasetTableModel;
import com.tuamotu.commons.dataset.DatasetIndex;
import com.tuamotu.commons.dataset.IDataset;
import com.tuamotu.commons.field.IField;

public class FatuDefaultDatasetTableModel<T> extends FatuAbstractDatasetTableModel<T> {

    private final IDataset<T> dataset;
    private DatasetIndex<T> index;

    @SafeVarargs
    public FatuDefaultDatasetTableModel(IDataset<T> dataset, DatasetIndex<T> index, IField<T>... fields) {
        this.dataset = dataset;
        this.index = index;
        super.addField(fields);
    }

    @Override
    protected IDataset<T> getDataset() {
        return this.dataset;
    }

    @Override
    protected DatasetIndex<T> getDatasetIndex() {
        return this.index;
    }
    
    protected void setDatasetIndex(DatasetIndex<T> newIndex) {
        this.index = newIndex;
    }

}
