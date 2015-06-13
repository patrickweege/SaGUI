package com.fatuhiva.model.datamodel.impl;

import com.fatuhiva.model.datamodel.FatuAbstractDatasetTableModel;
import com.tuamotu.commons.dataset.DatasetIndex;
import com.tuamotu.commons.dataset.IDataset;
import com.tuamotu.commons.field.IField;

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
