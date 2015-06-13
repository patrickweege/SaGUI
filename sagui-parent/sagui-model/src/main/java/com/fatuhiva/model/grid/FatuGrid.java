package com.fatuhiva.model.grid;

import com.fatuhiva.model.FatuComponent;
import com.fatuhiva.model.datamodel.IFatuColumnModel;
import com.fatuhiva.model.datamodel.IFatuTableModel;
import com.fatuhiva.model.feature.FatuSize;
import com.fatuhiva.model.feature.IFatuColumnModelFeature;
import com.fatuhiva.model.feature.IFatuSelectionFeature;
import com.fatuhiva.model.feature.IFatuSizeFeature;
import com.fatuhiva.model.feature.IFatuTableModelFeature;
import com.fatuhiva.model.selection.FatuSimpleSelectionModel;
import com.fatuhiva.model.selection.IFatuSelectionModel;
import com.tuamotu.commons.dataset.IBookmark;

public class FatuGrid extends FatuComponent implements IFatuSizeFeature, IFatuColumnModelFeature, IFatuTableModelFeature, IFatuSelectionFeature {

    private final IFatuTableModel<?> dataModel;
    private final IFatuSelectionModel<IBookmark<?>> selectionModel;
    private final IFatuColumnModel columnModel;
    
    private FatuSize size;

    public <T> FatuGrid(IFatuTableModel<T> model) {
        this(model, new FatuSimpleSelectionModel<IBookmark<T>>());
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public <T> FatuGrid(IFatuTableModel<T> model, IFatuSelectionModel<IBookmark<T>> selectionModel) {
        this.dataModel = model;
        this.selectionModel = (IFatuSelectionModel) selectionModel;
        this.columnModel = new FatuFieldColumnModel(model);
    }


    @Override
    @SuppressWarnings("unchecked")
    public <T> IFatuTableModel<T> getTableModel() {
        return (IFatuTableModel<T>) dataModel;
    }

    @Override
    public IFatuColumnModel getColumnModel() {
        return columnModel;
    }

    @Override
    public void setSize(FatuSize size) {
        this.size = size;
    }

    @Override
    public FatuSize getSize() {
        return this.size;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> IFatuSelectionModel<T> getSelectionModel() {
        return (IFatuSelectionModel<T>) this.selectionModel;
    }

}
