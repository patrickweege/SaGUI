package com.pw.ord.gui.contas;

import java.awt.Insets;
import java.util.Calendar;

import com.fatuhiva.model.action.IFatuActionEvent;
import com.fatuhiva.model.action.IFatuActionListener;
import com.fatuhiva.model.button.FatuButton;
import com.fatuhiva.model.container.field.FatuFieldSet;
import com.fatuhiva.model.container.panel.FatuPanel;
import com.fatuhiva.model.datamodel.IFatuColumnModel;
import com.fatuhiva.model.datasource.FatuSelectedDataModelDataSource;
import com.fatuhiva.model.editable.editbox.FatuTextBox;
import com.fatuhiva.model.feature.FatuLabelPosition;
import com.fatuhiva.model.feature.FatuSize;
import com.fatuhiva.model.grid.FatuGrid;
import com.fatuhiva.model.layout.box.FatuBoxLayoutAlign;
import com.fatuhiva.model.layout.box.FatuBoxPosition;
import com.fatuhiva.model.layout.box.FatuHBoxLayout;
import com.fatuhiva.model.layout.box.FatuHBoxLayoutRule;
import com.fatuhiva.model.layout.box.FatuVBoxLayout;
import com.fatuhiva.model.layout.box.FatuVBoxLayoutRule;
import com.fatuhiva.model.layout.fit.FatuFitLayout;
import com.fatuhiva.model.layout.fit.FatuFitLayoutRule;
import com.fatuhiva.model.selection.FatuSimpleBookmarkSelectionModel;
import com.pw.ord.dao.SpreadsheetConnection;
import com.pw.ord.et.OrcamentoData;
import com.tuamotu.commons.dataset.IBookmark;
import com.tuamotu.commons.i18n.I18n;

public class ManOrcamentoDataPanel extends FatuPanel<FatuVBoxLayout> {

    private final FatuFieldSet<FatuHBoxLayout> pnManData;
    private final FatuTextBox tBoxDataRealizado;
    private final FatuTextBox tBoxDataPrevisto;
    private final FatuTextBox tBoxContaCredito;
    private final FatuTextBox tBoxContaDebito;
    private final FatuTextBox tBoxCentroCusto;
    private final FatuTextBox tBoxValor;
    private final FatuTextBox tBoxObservacao;
    private final FatuButton btnNovo;

    private final FatuSelectedDataModelDataSource<OrcamentoData, String> dsRealizado;
    private final FatuSelectedDataModelDataSource<OrcamentoData, String> dsPrevisto;
    private final FatuSelectedDataModelDataSource<OrcamentoData, String> dsContaCredito;
    private final FatuSelectedDataModelDataSource<OrcamentoData, String> dsContaDebito;
    private final FatuSelectedDataModelDataSource<OrcamentoData, String> dsCentroCusto;
    private final FatuSelectedDataModelDataSource<OrcamentoData, String> dsValor;
    private final FatuSelectedDataModelDataSource<OrcamentoData, String> dsObservacao;

    private final FatuFieldSet<FatuHBoxLayout> pnFilter;
    private final FatuTextBox tBoxFilterData;
    private final FatuTextBox tBoxFilterConta;
    private final FatuTextBox tBoxFilterCC;

    private final FatuPanel<FatuFitLayout> pnTable;
    private final FatuGrid dataGrid;

    private final OrcamentoDataTableModel tableModel;
    private final FatuSimpleBookmarkSelectionModel<OrcamentoData> selectionModel;

    public ManOrcamentoDataPanel() {
        super(new FatuVBoxLayout(FatuBoxLayoutAlign.STRETCH, FatuBoxPosition.START));

        this.tableModel = new OrcamentoDataTableModel();
        this.selectionModel = new FatuSimpleBookmarkSelectionModel<OrcamentoData>();

        // Campos para Entrada de Dados 
        this.dsRealizado = new FatuSelectedDataModelDataSource<OrcamentoData, String>(String.class, tableModel.REALIZADO_FIELD, tableModel, selectionModel);
        this.tBoxDataRealizado = new FatuTextBox(dsRealizado);
        this.tBoxDataRealizado.setLabel(new I18n("tBoxDataRealizado").setDefault("Realizado"), FatuLabelPosition.TOP);

        this.dsPrevisto = new FatuSelectedDataModelDataSource<OrcamentoData, String>(String.class, tableModel.PREVISTO_FIELD, tableModel, selectionModel);
        this.tBoxDataPrevisto = new FatuTextBox(dsPrevisto);
        this.tBoxDataPrevisto.setLabel(new I18n("tBoxDataPrevisto").setDefault("Previsto"), FatuLabelPosition.TOP);

        this.dsContaCredito = new FatuSelectedDataModelDataSource<OrcamentoData, String>(String.class, tableModel.CONTA_CREDITO_FIELD, tableModel, selectionModel);
        this.tBoxContaCredito = new FatuTextBox(dsContaCredito);
        this.tBoxContaCredito.setLabel(new I18n("tBoxContaCredito").setDefault("Conta Crédito"), FatuLabelPosition.TOP);

        this.dsContaDebito = new FatuSelectedDataModelDataSource<OrcamentoData, String>(String.class, tableModel.CONTA_DEBITO_FIELD, tableModel, selectionModel);
        this.tBoxContaDebito = new FatuTextBox(dsContaDebito);
        this.tBoxContaDebito.setLabel(new I18n("tBoxContaDebito").setDefault("Conta Débito"), FatuLabelPosition.TOP);

        this.dsCentroCusto = new FatuSelectedDataModelDataSource<OrcamentoData, String>(String.class, tableModel.CENTRO_CUSTO_FIELD, tableModel, selectionModel);
        this.tBoxCentroCusto = new FatuTextBox(dsCentroCusto);
        this.tBoxCentroCusto.setLabel(new I18n("tBoxCentroCusto").setDefault("Centro Custo"), FatuLabelPosition.TOP);

        this.dsValor = new FatuSelectedDataModelDataSource<OrcamentoData, String>(String.class, tableModel.VALOR_FIELD, tableModel, selectionModel);
        this.tBoxValor = new FatuTextBox(dsValor);
        this.tBoxValor.setLabel(new I18n("tBoxValor").setDefault("Valor"), FatuLabelPosition.TOP);

        this.dsObservacao = new FatuSelectedDataModelDataSource<OrcamentoData, String>(String.class, tableModel.OBS_FIELD, tableModel, selectionModel);
        this.tBoxObservacao = new FatuTextBox(dsObservacao);
        this.tBoxObservacao.setLabel(new I18n("tBoxObservacao").setDefault("Observação"), FatuLabelPosition.TOP);

        this.btnNovo = createBtnNovo();

        
        FatuHBoxLayout hBoxLayout = new FatuHBoxLayout(FatuBoxLayoutAlign.START, FatuBoxPosition.START);
        hBoxLayout.setPadding(new Insets(5, 5, 5, 5));
        this.pnManData =  new FatuFieldSet<FatuHBoxLayout>(hBoxLayout);
        this.pnManData.setSize(new FatuSize(200, FatuSize.NOT_ESPECIFIED));
        this.pnManData.setTitle("Entrada de Dados");
        this.pnManData.addChild(tBoxDataRealizado, FatuHBoxLayoutRule.HBOX_LAYOUT_RULE);
        this.pnManData.addChild(tBoxDataPrevisto, FatuHBoxLayoutRule.HBOX_LAYOUT_RULE);
        this.pnManData.addChild(tBoxContaDebito, FatuHBoxLayoutRule.HBOX_LAYOUT_RULE);
        this.pnManData.addChild(tBoxContaCredito, FatuHBoxLayoutRule.HBOX_LAYOUT_RULE);
        this.pnManData.addChild(tBoxCentroCusto, FatuHBoxLayoutRule.HBOX_LAYOUT_RULE);
        this.pnManData.addChild(tBoxValor, FatuHBoxLayoutRule.HBOX_LAYOUT_RULE);
        this.pnManData.addChild(tBoxObservacao, FatuHBoxLayoutRule.HBOX_LAYOUT_RULE);
        this.pnManData.addChild(btnNovo, FatuHBoxLayoutRule.HBOX_LAYOUT_RULE);
        this.addChild(pnManData, FatuVBoxLayoutRule.VBOX_LAYOUT_RULE);

        // Filtros para a Table
        this.tBoxFilterData = new FatuTextBox();
        this.tBoxFilterData.setLabel(new I18n("tBoxFilterData").setDefault("Data"), FatuLabelPosition.TOP);

        this.tBoxFilterConta = new FatuTextBox();
        this.tBoxFilterConta.setLabel(new I18n("tBoxFilterConta").setDefault("Conta"), FatuLabelPosition.TOP);

        this.tBoxFilterCC = new FatuTextBox();
        this.tBoxFilterCC.setLabel(new I18n("tBoxFilterCC").setDefault("Centro Custo"), FatuLabelPosition.TOP);

        
        hBoxLayout = new FatuHBoxLayout(FatuBoxLayoutAlign.START, FatuBoxPosition.START);
        hBoxLayout.setPadding(new Insets(5, 5, 5, 5));
        this.pnFilter = new FatuFieldSet<FatuHBoxLayout>(hBoxLayout);
        this.pnFilter.setSize(new FatuSize(200, FatuSize.NOT_ESPECIFIED));
        this.pnFilter.setTitle("Filtros");
        this.pnFilter.addChild(tBoxFilterData, FatuHBoxLayoutRule.HBOX_LAYOUT_RULE);
        this.pnFilter.addChild(tBoxFilterConta, FatuHBoxLayoutRule.HBOX_LAYOUT_RULE);
        this.pnFilter.addChild(tBoxFilterCC, FatuHBoxLayoutRule.HBOX_LAYOUT_RULE);
        this.addChild(pnFilter, FatuVBoxLayoutRule.VBOX_LAYOUT_RULE);


        this.dataGrid = new FatuGrid(tableModel, selectionModel);
        IFatuColumnModel cModel = this.dataGrid.getColumnModel();
        cModel.setVisible(false, tableModel.getColumnIndex(tableModel.ID_FIELD));

        this.pnTable = new FatuPanel<FatuFitLayout>(FatuFitLayout.FIT_LAYOUT);
        this.pnTable.setTitle("Dados");
        this.pnTable.addChild(dataGrid, FatuFitLayoutRule.FIT_LAYOUT_RULE);
        
        this.addChild(pnTable, FatuVBoxLayoutRule.VBOX_LAYOUT_RULE);

    }

    public void setConnection(SpreadsheetConnection conn) {
        this.selectionModel.clearSelection();
        this.tableModel.setConnection(conn);
    }
    
    private FatuButton createBtnNovo() {
        FatuButton btn = new FatuButton();
        btn.setEnabled(true);
        btn.setLabel(new I18n("btnNovo").setDefault("Novo..."));
        btn.addActionListener(new IntegernalActionListener());

        return btn;
    }

    private void doNovoRegistro() {
        OrcamentoData novo = new OrcamentoData();
        novo.setId(Calendar.getInstance().getTimeInMillis());
        //novo.setUserId(0);
        IBookmark<OrcamentoData> inserted = this.tableModel.insert(novo);
        this.selectionModel.clearSelection();
        this.selectionModel.select(inserted);
    }

    private class IntegernalActionListener implements IFatuActionListener {

        @Override
        public void actionPerformed(IFatuActionEvent evt) {
            if (evt.getSource() == ManOrcamentoDataPanel.this.btnNovo) {
                doNovoRegistro();
            }
        }
    }

}
