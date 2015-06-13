package com.fatuhiva.model.selection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class FatuSimpleSelectionModel<T> implements IFatuSelectionModel<T> {

    private final Set<T> selection;
    private final List<IFatuSelectionListener<T>> listeners;

    public FatuSimpleSelectionModel() {
        this.selection = new HashSet<T>();
        this.listeners = new ArrayList<IFatuSelectionListener<T>>();
    }

    public boolean isSelected(T toCheck) {
        return selection.contains(toCheck);
    };

    @Override
    @SafeVarargs
    public final void select(T... toSelect) {
        List<T> oldSelection = new ArrayList<T>(selection);
        for (T elem : toSelect) {
            if (!selection.contains(elem)) {
                selection.add(elem);
            }
        }
        fireSelectionListeners(selection, oldSelection);
    }

    @Override
    @SafeVarargs
    public final void unselect(T... toUnselect) {
        List<T> oldSelection = new ArrayList<T>(selection);
        for (T elem : toUnselect) {
            if (selection.contains(elem)) {
                selection.remove(elem);
            }
        }
        fireSelectionListeners(selection, oldSelection);
    }

    @Override
    public void clearSelection() {
        ArrayList<T> oldSelection = new ArrayList<T>(this.selection);
        this.selection.clear();
        this.fireSelectionListeners(oldSelection, this.selection);

    }

    @Override
    public Collection<T> getSelection() {
        return new ArrayList<T>(selection);
    }

    @Override
    public int getSelectedCount() {
        return selection.size();
    }
    
    @Override
    public void addSelectionListener(IFatuSelectionListener<T> listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    @Override
    public void removeSelectionListener(IFatuSelectionListener<T> listener) {
        listeners.remove(listener);
    }

    private void fireSelectionListeners(Collection<T> newSelection, Collection<T> oldSelection) {
        for (IFatuSelectionListener<T> l : listeners) {
            l.selectionChanged(newSelection, oldSelection);
        }
    }

}
