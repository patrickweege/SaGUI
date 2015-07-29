package com.fatuhiva.model.datamodel.masterdetail;

import java.util.Collection;
import java.util.Collections;

import com.fatuhiva.model.datamodel.FatuAbstractTableModel;
import com.fatuhiva.model.datamodel.FatuTableModelEvent;
import com.fatuhiva.model.datamodel.IFatuTableModel;
import com.fatuhiva.model.selection.IFatuSelectionListener;
import com.fatuhiva.model.selection.IFatuSelectionModel;
import com.sagui.dataset.commons.comparator.BeanComparatorUtil;
import com.sagui.dataset.commons.comparator.IBeanComparator;
import com.sagui.dataset.commons.comparator.IFieldComparatorMetadata;
import com.sagui.dataset.commons.comparator.IBeanComparator.Order;
import com.sagui.dataset.commons.dataset.Dataset;
import com.sagui.dataset.commons.dataset.DatasetIndex;
import com.sagui.dataset.commons.dataset.IBookmark;
import com.sagui.dataset.commons.dataset.IDataset;
import com.sagui.dataset.commons.dataset.IDatasetField;
import com.sagui.dataset.commons.field.IField;

public class FatuMasterDetailTableModel<M, D> extends FatuAbstractTableModel<D> implements IFatuTableModel<D>, IFatuSelectionListener<IBookmark<M>> {

    private final IFatuTableModel<M> masterTableModel;
    private final IFatuSelectionModel<IBookmark<M>> masterSelectionModel;
    private final IField<M> masterDetailField;
    private final Dataset<D> dataset;
    private final IDatasetField<D> masterBookmarkField;
    private DatasetIndex<D> datasetIndex;

    public FatuMasterDetailTableModel(IFatuTableModel<M> masterTableModel, IFatuSelectionModel<IBookmark<M>> masterSelectionModel, IField<M> masterDetailField, IField<D>[] fields, IField<D> sortField) {
        this.masterTableModel = masterTableModel;
        this.masterSelectionModel = masterSelectionModel;
        this.masterDetailField = masterDetailField;

        this.dataset = new Dataset<D>();
        this.masterBookmarkField = this.dataset.addField("_FatuMasterBookmarkReference_", IBookmark.class);
        if (fields != null) {
            for (IField<D> oneField : fields) {
                IDatasetField<D> datasetField = this.getDataset().addField(oneField);
                super.addField(datasetField);
                if (oneField == sortField) {
                    IBeanComparator<D> detailComparator = BeanComparatorUtil.getBeanComparator(new IFieldComparatorMetadata<D>(datasetField, Order.ASC, true));
                    this.datasetIndex = getDataset().addIndex(detailComparator);

                }
            }
        }
        this.masterSelectionModel.addSelectionListener(this);
    }

    protected void configure() {

    }

    @Override
    public int getRowCount() {
        return dataset.getRecordCount();
    }

    @Override
    public void remove(IBookmark<D> toRemove) {
        IDataset<D> theDataset = this.getDataset();
        DatasetIndex<D> theIndex = this.getDatasetIndex();
        int rowIndex = theDataset.indexOf(toRemove, theIndex);
        if (rowIndex >= 0) {
            IBookmark<M> masterBookmark = this.dataset.getValue(toRemove, this.masterBookmarkField);
            D removed = theDataset.remove(toRemove);
            Collection<M> masterCollection = this.masterTableModel.getValueAt(masterBookmark, this.masterDetailField);
            masterCollection.remove(removed);
            this.masterTableModel.setValueAt(masterCollection, masterBookmark, this.masterDetailField);
            if (removed != null) {
                FatuTableModelEvent evt = new FatuTableModelEvent(this, -1, rowIndex, rowIndex, Collections.singleton(removed), FatuTableModelEvent.FatuTableModelEventType.REMOVE);
                this.fireTableModelListeners(evt);
            }
        }
    }

    @Override
    public IBookmark<D> insert(D toAdd) {
        if (masterSelectionModel.getSelectedCount() > 1) {
            throw new RuntimeException("Canot do INSERT operation when multiple Master-Records is selected");
        } else if (masterSelectionModel.getSelectedCount() == 0) {
            throw new RuntimeException("Canot do INSERT operation no Master-Record is selected");
        }
        Dataset<D> theDataset = getDataset();
        IBookmark<M> masterBkm = this.masterSelectionModel.getSelection().iterator().next();
        IBookmark<D> addedBkm = theDataset.add(toAdd);
        theDataset.setValue(addedBkm, this.masterBookmarkField, masterBkm);

        // Now update the master TableModel
        try {
            Collection<D> masterDetailCollection = masterTableModel.getValueAt(masterBkm, masterDetailField);
            masterDetailCollection.add(toAdd);
            masterTableModel.setValueAt(masterDetailCollection, masterBkm, masterDetailField);
        } catch (Exception e) {
            theDataset.remove(addedBkm);
            throw new RuntimeException(e);
        }
        return addedBkm;
    }

    @Override
    public IBookmark<D> getBookmark(int row) {
        IDataset<D> theDataset = getDataset();
        DatasetIndex<D> theIndex = this.getDatasetIndex();
        IBookmark<D> bkm = theDataset.getBookmark(row, theIndex);
        return bkm;
    }

    @Override
    public IBookmark<D> getBookmark(String bookmarktUUID) {
        return getDataset().getBookmark(bookmarktUUID);
    }

    @Override
    public int getRowIndex(IBookmark<D> bookmark) {
        Dataset<D> theDataset = getDataset();
        DatasetIndex<D> theIndex = getDatasetIndex();
        return theDataset.indexOf(bookmark, theIndex);
    }

    @Override
    public <V> V getValueAt(int row, IField<D> field) {
        Dataset<D> theDataset = getDataset();
        DatasetIndex<D> theIndex = getDatasetIndex();
        IBookmark<D> bookmark = theDataset.getBookmark(row, theIndex);
        V theValue = theDataset.getValue(bookmark, field);
        return theValue;
    }

    @Override
    public <V> V getValueAt(IBookmark<D> bookmark, IField<D> field) {
        Dataset<D> theDataset = getDataset();
        V theValue = theDataset.getValue(bookmark, field);
        return theValue;
    }

    @Override
    protected <V> void setValueAt(V value, int row, IField<D> field) {
        Dataset<D> theDataset = getDataset();
        DatasetIndex<D> theIndex = getDatasetIndex();
        IBookmark<D> bookmark = theDataset.getBookmark(row, theIndex);
        theDataset.setValue(bookmark, field, value);
    }

    /*
     * (non-Javadoc)
     * @see com.fatuhiva.model.datamodel.IFatuSelectionListener#selectionChanged(java.util.Collection, java.util.Collection)
     */
    @Override
    public void selectionChanged(Collection<IBookmark<M>> newSelection, Collection<IBookmark<M>> oldSelection) {
        Dataset<D> theDataset = getDataset();
        theDataset.clear();
        if (newSelection != null && !newSelection.isEmpty()) {
            for (IBookmark<M> selected : newSelection) {
                Collection<D> detail = this.masterTableModel.getValueAt(selected, masterDetailField);
                for (D aDetail : detail) {
                    IBookmark<D> added = theDataset.add(aDetail);
                    theDataset.setValue(added, masterBookmarkField, selected);
                }
            }
            FatuTableModelEvent evt = new FatuTableModelEvent(this, -1, 0, theDataset.getRecordCount() - 1, null, FatuTableModelEvent.FatuTableModelEventType.DATA_RESET);
            super.fireTableModelListeners(evt);
        }
    }

    private Dataset<D> getDataset() {
        return this.dataset;
    }

    private DatasetIndex<D> getDatasetIndex() {
        return this.datasetIndex;
    }

    
    
}
