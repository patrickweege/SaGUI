package com.fatuhiva.model.list.editable.impl;

import java.util.Collection;

import com.fatuhiva.model.FatuElement;
import com.fatuhiva.model.editable.FatuEditable;
import com.fatuhiva.model.list.IFatuListModel;
import com.fatuhiva.model.selection.IFatuSelectionModel;
import com.tuamotu.commons.dataset.IBookmark;


public class FatuAbstractListComponent<T extends FatuElement> extends FatuEditable {

	private IFatuListModel<T> listModel;
	private IFatuSelectionModel<IBookmark<T>> selecitonModel;

	public FatuAbstractListComponent(IFatuListModel<T> listModel, IFatuSelectionModel<IBookmark<T>> selectionModel) {
		this.listModel = listModel;
		this.selecitonModel = selectionModel;
	}

    public IFatuListModel<T> getListModel() {
        return listModel;
    }
	
	public Collection<IBookmark<T>> getSelection() {
	    return selecitonModel.getSelection();
	}
	
	public IFatuSelectionModel<IBookmark<T>> getSelectionModel() {
		return this.selecitonModel;
	}
	
}


