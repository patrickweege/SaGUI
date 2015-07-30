package com.sagui.model.feature;

import com.sagui.model.selection.IFatuSelectionModel;

public interface IFatuSelectionFeature {

    public <T> IFatuSelectionModel<T> getSelectionModel();

}
