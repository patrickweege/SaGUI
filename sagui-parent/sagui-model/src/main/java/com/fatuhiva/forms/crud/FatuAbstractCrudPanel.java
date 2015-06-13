package com.fatuhiva.forms.crud;

import java.beans.PropertyChangeListener;
import java.util.Collection;

import com.fatuhiva.forms.FatuActionEventHandler;
import com.fatuhiva.forms.FatuReflectActionListener;
import com.fatuhiva.forms.FatuReflectPropertyChangedListener;
import com.fatuhiva.model.FatuComponent;
import com.fatuhiva.model.action.IFatuActionEvent;
import com.fatuhiva.model.action.IFatuActionListener;
import com.fatuhiva.model.button.FatuButton;
import com.fatuhiva.model.container.panel.FatuPanel;
import com.fatuhiva.model.container.toolbar.FatuToolbar;
import com.fatuhiva.model.datamodel.IFatuTableModel;
import com.fatuhiva.model.editable.editbox.FatuTextBox;
import com.fatuhiva.model.feature.FatuSize;
import com.fatuhiva.model.grid.FatuGrid;
import com.fatuhiva.model.layout.border.FatuBorderLayout;
import com.fatuhiva.model.layout.border.FatuBorderLayoutRule;
import com.fatuhiva.model.layout.box.FatuVBoxLayout;
import com.fatuhiva.model.layout.fit.FatuFitLayout;
import com.fatuhiva.model.layout.fit.FatuFitLayoutRule;
import com.fatuhiva.model.selection.IFatuSelectionListener;
import com.fatuhiva.model.selection.IFatuSelectionModel;
import com.tuamotu.commons.dataset.IBookmark;
import com.tuamotu.commons.i18n.I18n;

public abstract class FatuAbstractCrudPanel<M> extends FatuPanel<FatuBorderLayout> {

    private final I18n btnNew_I18N;
    private final I18n btnSearch_I18N;
    private final I18n btnSave_I18N;
    private final I18n btnDelete_I18N;

    private final IFatuActionListener defaultActionListener;
    private final PropertyChangeListener defaultPropertyChangeListener;

    private FatuToolbar<FatuComponent> toolbar;
    private FatuPanel<FatuVBoxLayout> contentPanel;
    private FatuGrid grid;

    private FatuTextBox tBoxSearchValue;

    private FatuButton btnNew;
    private FatuButton btnSave;
    private FatuButton btnDelete;
    private FatuButton btnSearch;

    private IFatuTableModel<M> tableModel;
    private IFatuSelectionModel<IBookmark<M>> selectionModel;
    
    public FatuAbstractCrudPanel() {
        super(new FatuBorderLayout());
        this.setTitle(this.getClass().getName());
        
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
    }
    
    protected void initGui() {
        // The Grid
        this.tableModel = this.getTableModel();
        this.selectionModel = this.getSelectionModel();

        this.grid = new FatuGrid(tableModel, selectionModel);
        FatuPanel<FatuFitLayout> gridContainer = new FatuPanel<FatuFitLayout>(FatuFitLayout.FIT_LAYOUT);
        gridContainer.setSize(new FatuSize(100, 200));
        gridContainer.addChild(this.grid, FatuFitLayoutRule.FIT_LAYOUT_RULE);

        this.addChild(gridContainer, FatuBorderLayoutRule.NORTH);

        // The Toolbar Buttons
        this.toolbar = new FatuToolbar<FatuComponent>();
        this.setToolbar(this.toolbar);

        this.btnNew = new FatuButton();
        this.btnNew.addActionListener(getDefaultActionListener());
        this.btnNew.setName("btnCreate");
        this.btnNew.setLabel(btnNew_I18N);
        this.btnNew.setEnabled(true);
        this.getToolbar().addChild(btnNew);

        this.btnSave = new FatuButton();
        this.btnSave.addActionListener(getDefaultActionListener());
        this.btnSave.setName("btnSave");
        this.btnSave.setLabel(btnSave_I18N);
        this.btnSave.setEnabled(false);
        this.getToolbar().addChild(btnSave);

        this.btnDelete = new FatuButton();
        this.btnDelete.addActionListener(getDefaultActionListener());
        this.btnDelete.setName("btnDelete");
        this.btnDelete.setLabel(btnDelete_I18N);
        this.btnDelete.setEnabled(false);
        this.getToolbar().addChild(btnDelete);

        this.tBoxSearchValue = new FatuTextBox();
        this.tBoxSearchValue.setName("tBoxSearchValue");
        tBoxSearchValue.addPropertyChangeListener(defaultPropertyChangeListener);
        this.getToolbar().addChild(tBoxSearchValue);

        this.btnSearch = new FatuButton();
        this.btnSearch.addActionListener(getDefaultActionListener());
        this.btnSearch.setName("btnSearch");
        this.btnSearch.setLabel(btnSearch_I18N);
        this.btnSearch.setEnabled(true);
        this.getToolbar().addChild(btnSearch);

        // Content Panel
        this.contentPanel = new FatuPanel<FatuVBoxLayout>(FatuVBoxLayout.VBOX_DEFAULT);
        //this.contentPanel.setTitle("Fields Area....");
        this.addChild(contentPanel, FatuBorderLayoutRule.CENTER);
        
        
        IFatuSelectionListener<IBookmark<M>> l = new IFatuSelectionListener<IBookmark<M>>() {

            @Override
            public void selectionChanged(Collection<IBookmark<M>> newSelection, Collection<IBookmark<M>> oldSelection) {
                FatuAbstractCrudPanel.this.refreshToolbar();
            }
        };
        this.selectionModel.addSelectionListener(l);
    }

    public final FatuPanel<FatuVBoxLayout> getContentPanel() {
        return contentPanel;
    }

    public FatuToolbar<FatuComponent> getToolbar() {
        return toolbar;
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

    protected abstract void doCreate();

    protected abstract void doSave();

    protected abstract boolean isCanSave();

    protected abstract void doSearch(String value);

    protected abstract void doDelete();

    protected abstract IFatuTableModel<M> getTableModel();
    
    protected abstract IFatuSelectionModel<IBookmark<M>> getSelectionModel();
    
    protected void refreshToolbar() {
        this.btnNew.setEnabled(true);
        this.btnSave.setEnabled(isCanSave());
        this.btnDelete.setEnabled(this.getSelectionModel().getSelectedCount() != 0);
    }
    
    
    
    

}
