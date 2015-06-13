package com.fatuhiva.model.selection;

import java.util.Collection;

public interface IFatuSelectionListener<T> {
	
	public void selectionChanged(Collection<T> newSelection, Collection<T> oldSelection);

}
