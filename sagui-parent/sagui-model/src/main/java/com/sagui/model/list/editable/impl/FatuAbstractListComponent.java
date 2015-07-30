package com.sagui.model.list.editable.impl;

import java.util.Collection;

import com.sagui.dataset.commons.dataset.IBookmark;
import com.sagui.model.FatuElement;
import com.sagui.model.editable.FatuEditable;
import com.sagui.model.list.IFatuListModel;
import com.sagui.model.selection.IFatuSelectionModel;


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


