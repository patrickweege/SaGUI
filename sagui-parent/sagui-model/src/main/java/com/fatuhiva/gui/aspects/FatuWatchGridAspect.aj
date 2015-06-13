package com.fatuhiva.gui.aspects;

import java.util.Collection;

import org.slf4j.Logger;

import com.fatuhiva.model.datamodel.FatuTableModelEvent;
import com.fatuhiva.model.datamodel.IFatuTableModel;
import com.fatuhiva.model.datamodel.IFatuTableModelListener;
import com.fatuhiva.model.grid.FatuGrid;
import com.fatuhiva.model.selection.IFatuSelectionListener;
import com.fatuhiva.model.selection.IFatuSelectionModel;
import com.tuamotu.commons.dataset.IBookmark;
import com.tuamotu.commons.log.FatuLoggerFactory;

public aspect FatuWatchGridAspect {

    private static final Logger log = FatuLoggerFactory.create(FatuWatchGridAspect.class);

    @SuppressWarnings({ "rawtypes", "unchecked" })
    after(com.fatuhiva.model.grid.FatuGrid grid) returning: this(grid) && initialization(com.fatuhiva.model.grid.FatuGrid.new(com.fatuhiva.model.datamodel.IFatuTableModel+, com.fatuhiva.model.selection.IFatuSelectionModel+)) {
        if (log.isTraceEnabled()) log.trace("Intercept new {}(...)", grid.getClass());
        Object[] args = thisJoinPoint.getArgs();
        final FatuGrid theGrid = (FatuGrid) grid;
        IFatuTableModel model = (IFatuTableModel) args[0];
        IFatuTableModelListener l = new IFatuTableModelListener() {

            @Override
            public void tableChanged(FatuTableModelEvent evt) {
                theGrid.getPropertyChangeSupport().firePropertyChange("rows", null, null);
            }
        };
        model.addTableModelListener(l);

        IFatuSelectionModel selModel = theGrid.getSelectionModel();
        if (selModel != null) {
            IFatuSelectionListener<IBookmark> selListener = new IFatuSelectionListener<IBookmark>() {

                @Override
                public void selectionChanged(Collection<IBookmark> newSelection, Collection<IBookmark> oldSelection) {
                    theGrid.getPropertyChangeSupport().firePropertyChange("selectedRows", null, null);
                }
            };
            selModel.addSelectionListener(selListener);
        }

    }
}
