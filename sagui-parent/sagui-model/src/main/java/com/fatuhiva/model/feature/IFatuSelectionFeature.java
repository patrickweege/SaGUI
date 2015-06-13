package com.fatuhiva.model.feature;

import com.fatuhiva.model.selection.IFatuSelectionModel;

public interface IFatuSelectionFeature {

    public <T> IFatuSelectionModel<T> getSelectionModel();

}
