package com.sagui.model;

import java.util.Collection;

import org.slf4j.Logger;

import com.sagui.commons.log.FatuLoggerFactory;
import com.sagui.dataset.commons.dataset.IBookmark;
import com.sagui.model.datamodel.FatuTableModelEvent;
import com.sagui.model.datamodel.IFatuTableModel;
import com.sagui.model.datamodel.IFatuTableModelListener;
import com.sagui.model.grid.FatuGrid;
import com.sagui.model.selection.IFatuSelectionListener;
import com.sagui.model.selection.IFatuSelectionModel;

public aspect FatuWatchGridAspect {

    private static final Logger log = FatuLoggerFactory.create(FatuWatchGridAspect.class);

    @SuppressWarnings({ "rawtypes", "unchecked" })
    after(com.sagui.model.grid.FatuGrid grid) returning: this(grid) && initialization(com.sagui.model.grid.FatuGrid.new(com.sagui.model.datamodel.IFatuTableModel+, com.sagui.model.selection.IFatuSelectionModel+)) {
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
