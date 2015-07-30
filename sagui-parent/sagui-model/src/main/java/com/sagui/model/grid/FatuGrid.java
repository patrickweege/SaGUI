package com.sagui.model.grid;

import com.sagui.dataset.commons.dataset.IBookmark;
import com.sagui.model.FatuComponent;
import com.sagui.model.datamodel.IFatuColumnModel;
import com.sagui.model.datamodel.IFatuTableModel;
import com.sagui.model.feature.FatuSize;
import com.sagui.model.feature.IFatuColumnModelFeature;
import com.sagui.model.feature.IFatuSelectionFeature;
import com.sagui.model.feature.IFatuSizeFeature;
import com.sagui.model.feature.IFatuTableModelFeature;
import com.sagui.model.selection.FatuSimpleSelectionModel;
import com.sagui.model.selection.IFatuSelectionModel;

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
