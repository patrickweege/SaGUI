package com.sagui.forms.tabular;

import org.slf4j.Logger;

import com.sagui.dataset.commons.dataset.IBookmark;
import com.sagui.dataset.commons.field.IField;
import com.sagui.dataset.commons.i18n.I18n;
import com.sagui.dataset.commons.log.FatuLoggerFactory;
import com.sagui.forms.FatuActionEventHandler;
import com.sagui.forms.FatuReflectActionListener;
import com.sagui.model.FatuComponent;
import com.sagui.model.action.IFatuActionEvent;
import com.sagui.model.action.IFatuActionListener;
import com.sagui.model.button.FatuButton;
import com.sagui.model.container.form.FatuForm;
import com.sagui.model.container.panel.FatuPanel;
import com.sagui.model.container.toolbar.FatuToolbar;
import com.sagui.model.datamodel.FatuAbstractTableModel;
import com.sagui.model.datamodel.FatuTableModelEvent;
import com.sagui.model.datamodel.IFatuTableModel;
import com.sagui.model.datamodel.IFatuTableModelListener;
import com.sagui.model.datasource.FatuAbstractDataSource;
import com.sagui.model.datasource.FatuBookmarkModelDataSource;
import com.sagui.model.label.FatuLabel;
import com.sagui.model.layout.auto.FatuAutoLayout;
import com.sagui.model.layout.auto.FatuAutoLayoutRule;
import com.sagui.model.layout.border.FatuBorderLayout;
import com.sagui.model.layout.border.FatuBorderLayoutRule;
import com.sagui.model.layout.fit.FatuFitLayout;
import com.sagui.model.layout.fit.FatuFitLayoutRule;
import com.sagui.model.layout.table.FatuTableLayout;
import com.sagui.model.layout.table.FatuTableLayoutRule;

@SuppressWarnings({ "rawtypes", "unchecked" })
public abstract class FatuAbstractTabularForm extends FatuForm {

    private Logger log = FatuLoggerFactory.create();

    private static final I18n btnSave_I18N;
    private static final I18n btnNew_I18N;
    private static final I18n btnDelete_I18N;
    static {
        btnSave_I18N = new I18n("btnSave_I18N");
        btnSave_I18N.setDefault("Salvar");

        btnNew_I18N = new I18n("btnNew_I18N");
        btnNew_I18N.setDefault("Novo");

        btnDelete_I18N = new I18n("btnDelete_I18N");
        btnDelete_I18N.setDefault("Excluir");
    }

    private final IFatuActionListener defaultActionListener;
    private final InternalListeModelListener dataModelListener;

    private final FatuToolbar<FatuComponent> toolbar;
    private final FatuPanel toolbarPanel;
    private final FatuPanel contentPanel;

    private final FatuButton btnNew;
    private final FatuButton btnSave;
    private final FatuButton btnDelete;

    private IFatuTableModel<?> dataModel;
    private FatuPanel tablePanel;

    public <T> FatuAbstractTabularForm() {
        super(new FatuBorderLayout());

        this.dataModelListener = new InternalListeModelListener();
        this.defaultActionListener = new FatuReflectActionListener(this);

        this.setTitle(this.getClass().getName());

        this.toolbarPanel = new FatuPanel(FatuAutoLayout.AUTO_LAYOUT);
        super.addChild(toolbarPanel, FatuBorderLayoutRule.NORTH);

        this.toolbar = new FatuToolbar<FatuComponent>();
        this.toolbarPanel.addChild(toolbar, FatuAutoLayoutRule.AUTO_LAYOUT_RULE);

        this.contentPanel = new FatuPanel(FatuFitLayout.FIT_LAYOUT);
        this.contentPanel.setTitle("Fields Area....");
        super.addChild(contentPanel, FatuBorderLayoutRule.CENTER);

        this.btnNew = new FatuButton();
        this.btnNew.addActionListener(defaultActionListener);
        this.btnNew.setName("btnNew");
        this.btnNew.setLabel(btnNew_I18N);
        this.btnNew.setEnabled(true);
        this.toolbar.addChild(btnNew);

        this.btnSave = new FatuButton();
        this.btnSave.addActionListener(defaultActionListener);
        this.btnSave.setName("btnSave");
        this.btnSave.setLabel(btnSave_I18N);
        this.btnSave.setEnabled(true);
        this.toolbar.addChild(btnSave);

        this.btnDelete = new FatuButton();
        this.btnDelete.addActionListener(defaultActionListener);
        this.btnDelete.setName("btnDelete");
        this.btnDelete.setLabel(btnDelete_I18N);
        this.btnDelete.setEnabled(true);
        this.toolbar.addChild(btnDelete);

    }

    public FatuPanel getTablePanel() {
        return tablePanel;
    }

    @FatuActionEventHandler(components = "btnNew")
    public abstract void btnNewOnClick(IFatuActionEvent evt);

    @FatuActionEventHandler(components = "btnSave")
    public abstract void btnSaveOnClick(IFatuActionEvent evt);

    @FatuActionEventHandler(components = "btnDelete")
    public abstract void btnDeleteOnClick(IFatuActionEvent evt);

    protected abstract <V> FatuComponent getCellComponent(int row, int col, FatuAbstractDataSource<V> datasource);

    protected abstract <T> FatuAbstractTableModel<T> getDataModel();

    protected abstract FatuComponent getColumnHeader(int i);

    protected final void createUI() {
        if (this.dataModel != null) {
            dataModel.removeTableModelListener(dataModelListener);
        }
        if (this.tablePanel != null) {
            this.contentPanel.removeChild(tablePanel);
        }

        this.dataModel = getDataModel();
        this.dataModel.addTableModelListener(dataModelListener);
        if (this.dataModel == null) {
            this.tablePanel = new FatuPanel(new FatuTableLayout(1));
            FatuLabel lblDataModelNull = new FatuLabel();
            lblDataModelNull.setLabel(new I18n("lblDataModelNull").setDefault("Datamodel ist null"));
            this.tablePanel.addChild(lblDataModelNull, FatuTableLayoutRule.TABLE_RULE);
        } else {
            this.tablePanel = new FatuPanel(new FatuTableLayout(this.dataModel.getColumnCount()));

            for (int i = 0; i < this.dataModel.getColumnCount(); i++) {
                FatuComponent columnHeader = getColumnHeader(i);
                if (columnHeader == null) {
                    FatuLabel lbl = new FatuLabel();
                    lbl.setLabel(new I18n(lbl.getId()).setDefault(dataModel.getColumn(i).getName()));
                    columnHeader = lbl;
                }
                tablePanel.addChild(columnHeader, FatuTableLayoutRule.TABLE_RULE);
            }
            for (int row = 0; row < dataModel.getRowCount(); row++) {
                createRow(row);
            }

        }
        this.contentPanel.addChild(tablePanel, FatuFitLayoutRule.FIT_LAYOUT_RULE);
    }

    /*
     * Remove Editors from the specified row
     */
    private void removeRow(int row) {
        for (int col = 0; col < dataModel.getColumnCount(); col++) {
            int toDeleteIndex = getEditorIndex(row, 0);
            FatuComponent toRemove = tablePanel.getChidAt(toDeleteIndex);
            tablePanel.removeChild(toRemove);
        }
    }

    private void createRow(int row) {
        IBookmark<?> rowBookmark = dataModel.getBookmark(row);
        for (int col = 0; col < dataModel.getColumnCount(); col++) {
            IField<?> theColumn = dataModel.getColumn(col);
            FatuBookmarkModelDataSource dataSource = new FatuBookmarkModelDataSource(dataModel, rowBookmark, theColumn);
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

    private int getEditorIndex(int row, int col) {
        return col + (row * dataModel.getColumnCount()) + dataModel.getColumnCount();
    }

    private class InternalListeModelListener implements IFatuTableModelListener {

        @Override
        public void tableChanged(FatuTableModelEvent evt) {
            if (log.isTraceEnabled()) log.trace(evt.getType().toString());
            if (evt.getType() == FatuTableModelEvent.FatuTableModelEventType.REMOVE) {
                FatuAbstractTabularForm.this.dataDeleted(evt);
            } else if (evt.getType() == FatuTableModelEvent.FatuTableModelEventType.INSERT) {
                FatuAbstractTabularForm.this.dataInserted(evt);
            } else if (evt.getType() == FatuTableModelEvent.FatuTableModelEventType.UPDATE) {
                //
            }
        }

    }
}
