package com.fatuhiva.forms.tabular;

import java.beans.PropertyChangeListener;
import java.util.Collection;

import org.slf4j.Logger;

import com.fatuhiva.forms.FatuActionEventHandler;
import com.fatuhiva.forms.FatuReflectActionListener;
import com.fatuhiva.forms.FatuReflectPropertyChangedListener;
import com.fatuhiva.model.FatuComponent;
import com.fatuhiva.model.action.IFatuActionEvent;
import com.fatuhiva.model.action.IFatuActionListener;
import com.fatuhiva.model.button.FatuButton;
import com.fatuhiva.model.container.panel.FatuPanel;
import com.fatuhiva.model.container.toolbar.FatuToolbar;
import com.fatuhiva.model.datamodel.FatuTableModelEvent;
import com.fatuhiva.model.datamodel.IFatuTableModel;
import com.fatuhiva.model.datamodel.IFatuTableModelListener;
import com.fatuhiva.model.datasource.FatuAbstractDataSource;
import com.fatuhiva.model.datasource.FatuBookmarkModelDataSource;
import com.fatuhiva.model.editable.editbox.FatuTextBox;
import com.fatuhiva.model.label.FatuLabel;
import com.fatuhiva.model.layout.fit.FatuFitLayout;
import com.fatuhiva.model.layout.fit.FatuFitLayoutRule;
import com.fatuhiva.model.layout.table.FatuTableLayout;
import com.fatuhiva.model.layout.table.FatuTableLayoutRule;
import com.fatuhiva.model.selection.IFatuSelectionListener;
import com.fatuhiva.model.selection.IFatuSelectionModel;
import com.tuamotu.commons.dataset.IBookmark;
import com.tuamotu.commons.field.IField;
import com.tuamotu.commons.i18n.I18n;
import com.tuamotu.commons.log.FatuLoggerFactory;

@SuppressWarnings({ "rawtypes", "unchecked" })
public abstract class FatuAbstractTabularPanel<M> extends FatuPanel {

    private static final Logger log = FatuLoggerFactory.create();

    private final I18n btnNew_I18N;
    private final I18n btnSearch_I18N;
    private final I18n btnSave_I18N;
    private final I18n btnDelete_I18N;

    private final InternalTableModelListener dataModelListener;
    private final IFatuActionListener defaultActionListener;
    private final PropertyChangeListener defaultPropertyChangeListener;

    private final FatuToolbar<FatuComponent> toolbar;
    private final FatuPanel contentPanel;

    private final FatuTextBox tBoxSearchValue;

    private final FatuButton btnNew;
    private final FatuButton btnSave;
    private final FatuButton btnDelete;
    private final FatuButton btnSearch;

    private IFatuTableModel<M> tableModel;
    private IFatuSelectionModel<IBookmark<M>> selectionModel;

    private FatuPanel tablePanel;

    public FatuAbstractTabularPanel() {
        super(FatuFitLayout.FIT_LAYOUT);

        this.setTitle(this.getClass().getName());

        this.dataModelListener = new InternalTableModelListener();
        this.defaultActionListener = new FatuReflectActionListener(this);
        this.defaultPropertyChangeListener = new FatuReflectPropertyChangedListener(this);

        this.btnNew_I18N = new I18n("btnNew_I18N");
        this.btnNew_I18N.setDefault("Novo");

        this.btnSave_I18N = new I18n("btnSave_I18N");
        this.btnSave_I18N.setDefault("Salvar");

        this.btnDelete_I18N = new I18n("btnDelete_I18N");
        this.btnDelete_I18N.setDefault("Excluir");

        this.btnSearch_I18N = new I18n("btnSearch_I18N");
        this.btnSearch_I18N.setDefault("Buscar");

        this.toolbar = new FatuToolbar<FatuComponent>();

        this.btnNew = new FatuButton();
        this.btnNew.addActionListener(getDefaultActionListener());
        this.btnNew.setName("btnCreate");
        this.btnNew.setLabel(btnNew_I18N);
        this.btnNew.setEnabled(true);
        this.toolbar.addChild(btnNew);

        this.btnSave = new FatuButton();
        this.btnSave.addActionListener(getDefaultActionListener());
        this.btnSave.setName("btnSave");
        this.btnSave.setLabel(btnSave_I18N);
        this.btnSave.setEnabled(false);
        this.toolbar.addChild(btnSave);

        this.btnDelete = new FatuButton();
        this.btnDelete.addActionListener(getDefaultActionListener());
        this.btnDelete.setName("btnDelete");
        this.btnDelete.setLabel(btnDelete_I18N);
        this.btnDelete.setEnabled(false);
        this.toolbar.addChild(btnDelete);

        // The Toolbar Buttons
        this.tBoxSearchValue = new FatuTextBox();
        this.tBoxSearchValue.setName("tBoxSearchValue");
        tBoxSearchValue.addPropertyChangeListener(defaultPropertyChangeListener);
        this.toolbar.addChild(tBoxSearchValue);

        this.btnSearch = new FatuButton();
        this.btnSearch.addActionListener(getDefaultActionListener());
        this.btnSearch.setName("btnSearch");
        this.btnSearch.setLabel(btnSearch_I18N);
        this.btnSearch.setEnabled(true);
        this.toolbar.addChild(btnSearch);

        // Content Panel
        this.contentPanel = new FatuPanel(FatuFitLayout.FIT_LAYOUT);
        this.addChild(contentPanel, FatuFitLayoutRule.FIT_LAYOUT_RULE);
    }

    public FatuPanel getContentPanel() {
        return contentPanel;
    }

    public IFatuActionListener getDefaultActionListener() {
        return defaultActionListener;
    }

    public PropertyChangeListener getDefaultPropertyChangeListener() {
        return defaultPropertyChangeListener;
    }

    @FatuActionEventHandler(components = "btnSave")
    public final void btnSaveOnClick(IFatuActionEvent evt) {
        doSave();
    }

    @FatuActionEventHandler(components = "btnSearch")
    public final void btnSearchOnClick(IFatuActionEvent evt) {
        doSearch(tBoxSearchValue.getValue());
    }

    @FatuActionEventHandler(components = "btnDelete")
    public final void btnDeleteOnClick(IFatuActionEvent evt) {
        doDelete();
    }

    @FatuActionEventHandler(components = "btnCreate")
    public final void btnCreateOnClick(IFatuActionEvent evt) {
        doCreate();
        refreshToolbar();
    }

    protected void initGui() {
        // The Grid
        this.tableModel = this.getTableModel();
        this.selectionModel = this.getSelectionModel();

        this.setToolbar(this.toolbar);

        // Content Panel
        this.tablePanel = new FatuPanel(new FatuTableLayout(this.tableModel.getColumnCount()));
        //this.contentPanel.setTitle("Fields Area....");
        this.addChild(contentPanel, FatuFitLayoutRule.FIT_LAYOUT_RULE);

        IFatuSelectionListener<IBookmark<M>> l = new IFatuSelectionListener<IBookmark<M>>() {

            @Override
            public void selectionChanged(Collection<IBookmark<M>> newSelection, Collection<IBookmark<M>> oldSelection) {
                FatuAbstractTabularPanel.this.refreshToolbar();
            }
        };
        this.selectionModel.addSelectionListener(l);

        this.createTable();
    }

    protected final void createTable() {
        if (this.tableModel != null) {
            tableModel.removeTableModelListener(dataModelListener);
        }
        if (this.tablePanel != null) {
            this.contentPanel.removeChild(tablePanel);
        }

        this.tableModel.addTableModelListener(dataModelListener);
        if (this.tableModel == null) {
            this.tablePanel = new FatuPanel(new FatuTableLayout(1));
            FatuLabel lblDataModelNull = new FatuLabel();
            lblDataModelNull.setLabel(new I18n("lblDataModelNull").setDefault("Datamodel ist null"));
            this.tablePanel.addChild(lblDataModelNull, FatuTableLayoutRule.TABLE_RULE);
        } else {
            this.tablePanel = new FatuPanel(new FatuTableLayout(this.tableModel.getColumnCount()));

            for (int i = 0; i < this.tableModel.getColumnCount(); i++) {
                FatuLabel columnHeader = null;
                if (columnHeader == null) {
                    FatuLabel lbl = new FatuLabel();
                    lbl.setLabel(new I18n(lbl.getId()).setDefault(tableModel.getColumn(i).getName()));
                    columnHeader = lbl;
                }
                tablePanel.addChild(columnHeader, FatuTableLayoutRule.TABLE_RULE);
            }
            for (int row = 0; row < tableModel.getRowCount(); row++) {
                createRow(row);
            }

        }
        this.contentPanel.addChild(tablePanel, FatuFitLayoutRule.FIT_LAYOUT_RULE);
    }

    protected abstract void doCreate();

    protected abstract void doSave();

    protected abstract boolean isCanSave();

    protected abstract void doSearch(String value);

    protected abstract void doDelete();

    protected abstract IFatuTableModel<M> getTableModel();

    protected abstract IFatuSelectionModel<IBookmark<M>> getSelectionModel();

    protected abstract <V> FatuComponent getCellComponent(int row, int col, FatuAbstractDataSource<V> datasource);

    protected void refreshToolbar() {
        this.btnNew.setEnabled(true);
        this.btnSave.setEnabled(isCanSave());
        this.btnDelete.setEnabled(this.getSelectionModel().getSelectedCount() != 0);
    }

    /*
     * Remove Editors from the specified row
     */
    private void removeRow(int row) {
        for (int col = 0; col < tableModel.getColumnCount(); col++) {
            int toDeleteIndex = getEditorIndex(row, 0);
            FatuComponent toRemove = tablePanel.getChidAt(toDeleteIndex);
            tablePanel.removeChild(toRemove);
        }
    }

    private void createRow(int row) {
        IBookmark<M> rowBookmark = tableModel.getBookmark(row);
        for (int col = 0; col < tableModel.getColumnCount(); col++) {
            IField<M> theField = tableModel.getColumn(col);
            FatuBookmarkModelDataSource dataSource = new FatuBookmarkModelDataSource(tableModel, rowBookmark, theField);
            FatuComponent cmp = getCellComponent(row, col, dataSource);
            int insertPos = getEditorIndex(row, col);
            tablePanel.insertChild(insertPos, cmp, FatuTableLayoutRule.TABLE_RULE);
        }
    }

    private void dataDeleted(FatuTableModelEvent evt) {
        int firstRow = evt.getFirstRow();
        int lastRow = evt.getLastRow();
        for (int i = firstRow; i <= lastRow; i++) {
            this.removeRow(i);
        }
    }

    private void dataInserted(FatuTableModelEvent evt) {
        int firstRow = evt.getFirstRow();
        int lastRow = evt.getLastRow();
        for (int row = firstRow; row <= lastRow; row++) {
            this.createRow(row);
        }
    }

    private void dataReseted(FatuTableModelEvent evt) {
        this.createTable();
    }

    private int getEditorIndex(int row, int col) {
        return col + (row * tableModel.getColumnCount()) + tableModel.getColumnCount();
    }

    private class InternalTableModelListener implements IFatuTableModelListener {

        @Override
        public void tableChanged(FatuTableModelEvent evt) {
            if (log.isTraceEnabled()) log.trace(evt.getType().toString());
            
            if (evt.getType() == FatuTableModelEvent.FatuTableModelEventType.REMOVE) {
                FatuAbstractTabularPanel.this.dataDeleted(evt);
            } else if (evt.getType() == FatuTableModelEvent.FatuTableModelEventType.INSERT) {
                FatuAbstractTabularPanel.this.dataInserted(evt);
            } else if (evt.getType() == FatuTableModelEvent.FatuTableModelEventType.UPDATE) {
                //
            } else if (evt.getType() == FatuTableModelEvent.FatuTableModelEventType.DATA_RESET) {
                FatuAbstractTabularPanel.this.dataReseted(evt);
            }
        }

    }

}
