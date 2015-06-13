package com.fatuhiva.model.feature;

import com.fatuhiva.model.datamodel.IFatuTableModel;

public interface IFatuTableModelFeature {

    public <T> IFatuTableModel<T> getTableModel();
}
