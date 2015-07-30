package com.sagui.model.datamodel.impl;

import com.sagui.dataset.commons.dataset.DatasetIndex;
import com.sagui.dataset.commons.dataset.IDataset;
import com.sagui.dataset.commons.field.IField;
import com.sagui.model.datamodel.FatuAbstractDatasetTableModel;

public class FatuArrayDatasetDataModel<T> extends FatuAbstractDatasetTableModel<T[]> {

    private final IDataset<T[]> dataset;
    private DatasetIndex<T[]> index;
    
    public FatuArrayDatasetDataModel(IDataset<T[]> dataset, DatasetIndex<T[]> index, IField<T[]>... fields) {
        this.dataset = dataset;
        this.index = index;
        this.addField(fields);
    }

    @Override
    protected IDataset<T[]> getDataset() {
        return this.dataset;
    }

    @Override
    protected DatasetIndex<T[]> getDatasetIndex() {
        return this.index;
    }
    
    
    
    

}
