package com.sagui.model.feature;

import com.sagui.model.datamodel.IFatuTableModel;

public interface IFatuTableModelFeature {

    public <T> IFatuTableModel<T> getTableModel();
}
