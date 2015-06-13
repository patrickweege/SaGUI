package com.fatuhiva.model.list.combo.editable;

import com.fatuhiva.model.FatuElement;
import com.fatuhiva.model.list.IFatuListModel;
import com.fatuhiva.model.list.editable.impl.FatuAbstractListComponent;
import com.fatuhiva.model.selection.IFatuSelectionModel;
import com.tuamotu.commons.dataset.IBookmark;


public class FatuComboBox<T extends FatuElement> extends FatuAbstractListComponent<T> {

	public FatuComboBox(IFatuListModel<T> model, IFatuSelectionModel<IBookmark<T>> selectionModel) {
		super(model, selectionModel);
	}
	
}


