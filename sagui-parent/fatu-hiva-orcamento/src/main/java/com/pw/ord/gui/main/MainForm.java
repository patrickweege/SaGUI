package com.pw.ord.gui.main;

import java.awt.Insets;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.apache.commons.lang.StringUtils;

import com.fatuhiva.model.action.IFatuActionEvent;
import com.fatuhiva.model.action.IFatuActionListener;
import com.fatuhiva.model.button.FatuButton;
import com.fatuhiva.model.container.form.FatuForm;
import com.fatuhiva.model.container.panel.FatuPanel;
import com.fatuhiva.model.container.tabpanel.FatuTabPanel;
import com.fatuhiva.model.editable.editbox.FatuTextBox;
import com.fatuhiva.model.feature.FatuLabelPosition;
import com.fatuhiva.model.feature.FatuSize;
import com.fatuhiva.model.layout.border.FatuBorderLayout;
import com.fatuhiva.model.layout.border.FatuBorderLayoutRule;
import com.fatuhiva.model.layout.box.FatuHBoxLayout;
import com.fatuhiva.model.layout.box.FatuHBoxLayoutRule;
import com.fatuhiva.model.layout.box.FatuVBoxLayout;
import com.fatuhiva.model.layout.box.FatuVBoxLayoutRule;
import com.fatuhiva.model.layout.fit.FatuFitLayout;
import com.pw.ord.dao.SpreadsheetConnection;
import com.pw.ord.gui.contas.ManOrcamentoDataPanel;
import com.tuamotu.commons.i18n.I18n;

public class MainForm extends FatuForm<FatuBorderLayout> {

    private final FatuTabPanel<FatuPanel<?>> tabPanel;
    private final FatuPanel<FatuVBoxLayout> pnLogin;
    private final FatuPanel<FatuFitLayout> outraTab;

    private final ManOrcamentoDataPanel manContasPanel;
    private FatuTextBox tBoxPlanilha;
    private FatuButton btnGenerateURL;
    private FatuTextBox tBoxAuthURL;
    private FatuTextBox tBoxAuthToken;
    private FatuButton btnAutenticar;
    private FatuButton btnDesconectar;
    private SpreadsheetConnection connection;

    public MainForm() {
        super(new FatuBorderLayout());
        this.pnLogin = createLoginPanel();

        // Tab Contas
        this.manContasPanel = new ManOrcamentoDataPanel();
        this.manContasPanel.setSize(new FatuSize(300, 300));
        this.manContasPanel.setTitle("Orçamento");

        //Tab Outros..... 
        this.outraTab = new FatuPanel<FatuFitLayout>(FatuFitLayout.FIT_LAYOUT);
        this.outraTab.setName("outraTab");
        this.outraTab.setTitle("Outra Tab");

        this.tabPanel = new FatuTabPanel<FatuPanel<?>>();
        this.tabPanel.setPadding(new Insets(10, 10, 10, 10));
        this.tabPanel.addChild(manContasPanel);
        this.tabPanel.addChild(outraTab);

        super.addChild(pnLogin, FatuBorderLayoutRule.NORTH);
        super.addChild(tabPanel, FatuBorderLayoutRule.CENTER);
    }

    private FatuPanel<FatuVBoxLayout> createLoginPanel() {
        this.tBoxPlanilha = new FatuTextBox();
        this.tBoxPlanilha.setLabel(new I18n("tBoxPlanilha").setDefault("Planilha do Google"), FatuLabelPosition.LEFT);
        this.tBoxPlanilha.setSize(new FatuSize(400, FatuSize.NOT_ESPECIFIED));
        this.tBoxPlanilha.setLabelWidth(150);

        this.btnGenerateURL = new FatuButton();
        this.btnGenerateURL.setLabel(new I18n("tBoxAuthURL").setDefault("Obter URL..."));
        this.btnGenerateURL.setEnabled(false);
        this.btnGenerateURL.setMargins(new Insets(1, 5, 1, 1));

        this.tBoxAuthURL = new FatuTextBox();
        this.tBoxAuthURL.setLabel(new I18n("tBoxAuthURL").setDefault("URL Para Autenticação"), FatuLabelPosition.LEFT);
        this.tBoxAuthURL.setSize(new FatuSize(800, FatuSize.NOT_ESPECIFIED));
        this.tBoxAuthURL.setEnabled(false);
        this.tBoxAuthURL.setLabelWidth(150);
        
        this.tBoxAuthToken = new FatuTextBox();
        this.tBoxAuthToken.setLabel(new I18n("tBoxAuthToken").setDefault("Token"), FatuLabelPosition.LEFT);
        this.tBoxAuthToken.setSize(new FatuSize(800, FatuSize.NOT_ESPECIFIED));
        this.tBoxAuthToken.setEnabled(false);
        this.tBoxAuthToken.setLabelWidth(150);

        this.btnAutenticar = new FatuButton();
        this.btnAutenticar.setLabel(new I18n("btnAutenticar").setDefault("Conectar..."));
        this.btnAutenticar.setEnabled(false);

        this.btnDesconectar = new FatuButton();
        this.btnDesconectar.setLabel(new I18n("btnDesconectar").setDefault("Desconectar..."));
        this.btnDesconectar.setEnabled(false);
        this.btnDesconectar.setMargins(new Insets(1, 5, 1, 1));

        FatuPanel<FatuVBoxLayout> pnLogin = new FatuPanel<FatuVBoxLayout>(FatuVBoxLayout.VBOX_DEFAULT);
        pnLogin.setTitle("Login...");
        pnLogin.setPadding(new Insets(10, 10, 10, 10));

        FatuPanel<FatuHBoxLayout> pn = new FatuPanel<FatuHBoxLayout>(FatuHBoxLayout.HBOX_DEFAULT); 
        pn.addChild(tBoxPlanilha, FatuHBoxLayoutRule.HBOX_LAYOUT_RULE);
        pn.addChild(btnGenerateURL, FatuHBoxLayoutRule.HBOX_LAYOUT_RULE);
        pnLogin.addChild(pn, FatuVBoxLayoutRule.VBOX_LAYOUT_RULE);
        
        pn = new FatuPanel<FatuHBoxLayout>(FatuHBoxLayout.HBOX_DEFAULT);
        pn.addChild(tBoxAuthURL, FatuHBoxLayoutRule.HBOX_LAYOUT_RULE);
        pnLogin.addChild(pn, FatuVBoxLayoutRule.VBOX_LAYOUT_RULE);

        pn = new FatuPanel<FatuHBoxLayout>(FatuHBoxLayout.HBOX_DEFAULT);
        pn.addChild(tBoxAuthToken, FatuHBoxLayoutRule.HBOX_LAYOUT_RULE);
        pnLogin.addChild(pn, FatuVBoxLayoutRule.VBOX_LAYOUT_RULE);

        pn = new FatuPanel<FatuHBoxLayout>(FatuHBoxLayout.HBOX_DEFAULT);
        pn.addChild(btnAutenticar, FatuHBoxLayoutRule.HBOX_LAYOUT_RULE);
        pn.addChild(btnDesconectar, FatuHBoxLayoutRule.HBOX_LAYOUT_RULE);
        pnLogin.addChild(pn, FatuVBoxLayoutRule.VBOX_LAYOUT_RULE);

        IFatuActionListener btnListener = new IFatuActionListener() {

            @Override
            public void actionPerformed(IFatuActionEvent evt) {
                if (evt.getSource() == btnGenerateURL) {
                    MainForm.this.doGenerateURL();
                } else if (evt.getSource() == btnAutenticar) {
                    MainForm.this.doAutenticar();
                } else if (evt.getSource() == btnDesconectar) {
                    MainForm.this.doDeconectar();
                }
            }
        };
        btnGenerateURL.addActionListener(btnListener);
        btnAutenticar.addActionListener(btnListener);
        btnDesconectar.addActionListener(btnListener);

        PropertyChangeListener changeListener = new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getSource() == tBoxPlanilha) {
                    MainForm.this.tBoxPlanilhaChanged();
                } else if (evt.getSource() == tBoxAuthURL) {
                    MainForm.this.tBoxAuthURLChanged();
                } else if (evt.getSource() == tBoxAuthToken) {
                    MainForm.this.tBoxAuthTokenChanged();
                }
            }
        };

        tBoxPlanilha.addPropertyChangeListener(changeListener);
        tBoxAuthURL.addPropertyChangeListener(changeListener);
        tBoxAuthToken.addPropertyChangeListener(changeListener);

        return pnLogin;

    }

    private void tBoxPlanilhaChanged() {
        String value = tBoxPlanilha.getValue();

        boolean canGenerate = !StringUtils.isBlank(value);
        canGenerate = canGenerate && tBoxPlanilha.isEnabled();

        this.btnGenerateURL.setEnabled(canGenerate);

    }

    private void tBoxAuthURLChanged() {
        String value = tBoxAuthURL.getValue();
        this.tBoxAuthToken.setEnabled(!StringUtils.isBlank(value));
    }

    private void tBoxAuthTokenChanged() {
        String value = tBoxAuthToken.getValue();
        this.btnAutenticar.setEnabled(!StringUtils.isBlank(value));
    }

    private void doGenerateURL() {
        String value = tBoxPlanilha.getValue();
        SpreadsheetConnection newConn = new SpreadsheetConnection(value);
        this.setConnection(newConn);
        String url = this.connection.getAutorizationURL();
        this.tBoxAuthURL.setValue(url);
    }

    private void doAutenticar() {
        this.manContasPanel.setConnection(null);
        String value = tBoxAuthToken.getValue();
        this.connection.autenticate(value);
        if (this.connection.isAutenticated()) {
            this.btnGenerateURL.setEnabled(false);
            this.btnAutenticar.setEnabled(false);
            this.tBoxPlanilha.setEnabled(false);
            this.btnDesconectar.setEnabled(true);
        }
        this.setConnection(this.connection);
    }

    private void doDeconectar() {
        this.setConnection(null);
        this.btnAutenticar.setEnabled(false);
        this.btnDesconectar.setEnabled(false);
        this.btnGenerateURL.setEnabled(false);
        this.tBoxPlanilha.setEnabled(true);

        this.tBoxPlanilha.setValue(StringUtils.EMPTY);
        this.tBoxAuthURL.setValue(StringUtils.EMPTY);
        this.tBoxAuthToken.setValue(StringUtils.EMPTY);
    }

    private void setConnection(SpreadsheetConnection connection) {
        this.connection = connection;
        this.manContasPanel.setConnection(this.connection);
    }

}
