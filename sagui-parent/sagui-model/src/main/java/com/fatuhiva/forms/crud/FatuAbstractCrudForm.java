package com.fatuhiva.forms.crud;

import java.beans.PropertyChangeListener;

import com.fatuhiva.forms.FatuActionEventHandler;
import com.fatuhiva.forms.FatuReflectActionListener;
import com.fatuhiva.forms.FatuReflectPropertyChangedListener;
import com.fatuhiva.model.FatuComponent;
import com.fatuhiva.model.action.IFatuActionEvent;
import com.fatuhiva.model.action.IFatuActionListener;
import com.fatuhiva.model.button.FatuButton;
import com.fatuhiva.model.container.form.FatuForm;
import com.fatuhiva.model.container.panel.FatuPanel;
import com.fatuhiva.model.container.toolbar.FatuToolbar;
import com.fatuhiva.model.editable.editbox.FatuTextBox;
import com.fatuhiva.model.layout.auto.FatuAutoLayout;
import com.fatuhiva.model.layout.auto.FatuAutoLayoutRule;
import com.fatuhiva.model.layout.border.FatuBorderLayout;
import com.fatuhiva.model.layout.border.FatuBorderLayoutRule;
import com.sagui.dataset.commons.dataset.IBookmark;
import com.sagui.dataset.commons.i18n.I18n;

public abstract class FatuAbstractCrudForm<M> extends FatuForm<FatuBorderLayout> {

    private final I18n btnNew_I18N;
    private final I18n btnSearch_I18N;
    private final I18n btnSave_I18N;
    private final I18n btnDelete_I18N;

    private final IFatuActionListener defaultActionListener;
    private final PropertyChangeListener defaultPropertyChangeListener;

    private final FatuToolbar<FatuComponent> toolbar;
    private final FatuPanel<FatuAutoLayout> toolbarPanel;
    private final FatuPanel<FatuAutoLayout> contentPanel;

    private final FatuTextBox tBoxSearchValue;

    private final FatuButton btnNew;
    private final FatuButton btnSave;
    private final FatuButton btnDelete;
    private final FatuButton btnSearch;

    private IBookmark<M> current = null;

    public FatuAbstractCrudForm() {
        super(new FatuBorderLayout());

        this.btnNew_I18N = new I18n("btnNew_I18N");
        this.btnNew_I18N.setDefault("Novo");

        this.btnSearch_I18N = new I18n("btnSearch_I18N");
        this.btnSearch_I18N.setDefault("Buscar");

        this.btnSave_I18N = new I18n("btnSave_I18N");
        this.btnSave_I18N.setDefault("Salvar");

        this.btnDelete_I18N = new I18n("btnDelete_I18N");
        this.btnDelete_I18N.setDefault("Excluir");

        this.defaultActionListener = new FatuReflectActionListener(this);
        this.defaultPropertyChangeListener = new FatuReflectPropertyChangedListener(this);

        this.setTitle(this.getClass().getName());

        this.toolbarPanel = new FatuPanel<FatuAutoLayout>(FatuAutoLayout.AUTO_LAYOUT);
        super.addChild(toolbarPanel, FatuBorderLayoutRule.NORTH);

        this.toolbar = new FatuToolbar<FatuComponent>();
        this.toolbarPanel.addChild(toolbar, FatuAutoLayoutRule.AUTO_LAYOUT_RULE);

        this.contentPanel = new FatuPanel<FatuAutoLayout>(FatuAutoLayout.AUTO_LAYOUT);
        this.contentPanel.setTitle("Fields Area....");
        super.addChild(contentPanel, FatuBorderLayoutRule.CENTER);

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
    }

    public FatuPanel<FatuAutoLayout> getContentPanel() {
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
        if (getCurrent() != null) {
            tBoxSearchValue.setValue(null);
        }
    }

    @FatuActionEventHandler(components = "btnDelete")
    public final void btnDeleteOnClick(IFatuActionEvent evt) {
        doDelete();
    }

    @FatuActionEventHandler(components = "btnCreate")
    public final void btnCreateOnClick(IFatuActionEvent evt) {
        doCreate();
    }

    protected abstract void doCreate();

    protected abstract void doSave();

    protected abstract boolean isCanSave();

    protected abstract void doSearch(String value);

    protected abstract void doDelete();

    public IBookmark<M> getCurrent() {
        return current;
    }

    protected void setCurrent(IBookmark<M> current) {
        this.current = current;
        this.refreshToolbar();
    }

    private void refreshToolbar() {
        this.btnNew.setEnabled(true);
        this.btnSave.setEnabled(this.current != null && isCanSave());
        this.btnDelete.setEnabled(this.current != null);
    }

}
