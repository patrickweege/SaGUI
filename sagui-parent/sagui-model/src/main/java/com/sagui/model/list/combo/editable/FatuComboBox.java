package com.sagui.model.list.combo.editable;

import com.sagui.dataset.commons.dataset.IBookmark;
import com.sagui.model.FatuElement;
import com.sagui.model.list.IFatuListModel;
import com.sagui.model.list.editable.impl.FatuAbstractListComponent;
import com.sagui.model.selection.IFatuSelectionModel;


public class FatuComboBox<T extends FatuElement> extends FatuAbstractListComponent<T> {

	public FatuComboBox(IFatuListModel<T> model, IFatuSelectionModel<IBookmark<T>> selectionModel) {
		super(model, selectionModel);
	}
	
}


