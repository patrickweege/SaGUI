package com.fatuhiva.model.selection;

import java.util.Collection;



public interface IFatuSelectionModel<T> {
    
    public boolean isSelected(T toCheck);
    
    @SuppressWarnings("unchecked")
    public void select(T... toSelect);
	
    @SuppressWarnings("unchecked")
	public void unselect(T... toUnselect);
	
	public void clearSelection();
	
	public Collection<T> getSelection();
	
	public int getSelectedCount();
	
	public void addSelectionListener(IFatuSelectionListener<T> listener);
	
	public void removeSelectionListener(IFatuSelectionListener<T> listener);
	
	
}
