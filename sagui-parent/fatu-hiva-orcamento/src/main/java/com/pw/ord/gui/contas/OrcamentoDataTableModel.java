package com.pw.ord.gui.contas;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.ComparatorUtils;

import com.fatuhiva.model.datamodel.FatuAbstractDatasetTableModel;
import com.pw.ord.dao.IOrcamentoDomesticoDao;
import com.pw.ord.dao.SpreadsheetConnection;
import com.pw.ord.dao.SpreadsheetOrcamentoDataDao;
import com.pw.ord.et.OrcamentoData;
import com.tuamotu.commons.dataset.Dataset;
import com.tuamotu.commons.dataset.DatasetIndex;
import com.tuamotu.commons.dataset.IDataset;
import com.tuamotu.commons.dataset.IDatasetField;
import com.tuamotu.commons.field.BeanFieldHelper;
import com.tuamotu.commons.field.FormaterField;
import com.tuamotu.commons.field.IField;

public class OrcamentoDataTableModel extends FatuAbstractDatasetTableModel<OrcamentoData> {

    private static final Format DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    private final Dataset<OrcamentoData> dataset;
    private final DatasetIndex<OrcamentoData> index;

    public final IDatasetField<OrcamentoData> ID_FIELD;
    public final IDatasetField<OrcamentoData> REALIZADO_FIELD;
    public final IDatasetField<OrcamentoData> PREVISTO_FIELD;
    public final IDatasetField<OrcamentoData> CONTA_CREDITO_FIELD;
    public final IDatasetField<OrcamentoData> CONTA_DEBITO_FIELD;
    public final IDatasetField<OrcamentoData> CENTRO_CUSTO_FIELD;
    public final IDatasetField<OrcamentoData> VALOR_FIELD;
    public final IDatasetField<OrcamentoData> OBS_FIELD;

    private IOrcamentoDomesticoDao<OrcamentoData> dao;

    public OrcamentoDataTableModel() {

        this.dataset = new Dataset<OrcamentoData>();
        this.ID_FIELD = this.dataset.addField(BeanFieldHelper.getField("id", OrcamentoData.class));
        this.addField(ID_FIELD);

        IField<OrcamentoData> aField = BeanFieldHelper.getField("dataRealizado", OrcamentoData.class);
        aField = new FormaterField<OrcamentoData>(aField, DATE_FORMAT);
        this.REALIZADO_FIELD = this.dataset.addField(aField);
        this.addField(REALIZADO_FIELD);

        aField = BeanFieldHelper.getField("dataPrevisto", OrcamentoData.class);
        aField = new FormaterField<OrcamentoData>(aField, DATE_FORMAT);
        this.PREVISTO_FIELD = this.dataset.addField(aField);
        this.addField(PREVISTO_FIELD);

        this.CONTA_CREDITO_FIELD = this.dataset.addField(BeanFieldHelper.getField("contaCredito", OrcamentoData.class));
        this.addField(CONTA_CREDITO_FIELD);

        this.CONTA_DEBITO_FIELD = this.dataset.addField(BeanFieldHelper.getField("contaDebito", OrcamentoData.class));
        this.addField(CONTA_DEBITO_FIELD);

        this.CENTRO_CUSTO_FIELD = this.dataset.addField(BeanFieldHelper.getField("centroCusto", OrcamentoData.class));
        this.addField(CENTRO_CUSTO_FIELD);

        this.VALOR_FIELD = this.dataset.addField(BeanFieldHelper.getField("valor", OrcamentoData.class));
        this.addField(VALOR_FIELD);

        this.OBS_FIELD = this.dataset.addField(BeanFieldHelper.getField("observacao", OrcamentoData.class));
        this.addField(OBS_FIELD);

        @SuppressWarnings({ "unchecked", "rawtypes" })
        Comparator<OrcamentoData> dateAndIDComparator = new Comparator<OrcamentoData>() {

            private Comparator<Comparable> natural = ComparatorUtils.naturalComparator();
            private Comparator<Comparable> c = ComparatorUtils.nullHighComparator(natural);

            @Override
            public int compare(OrcamentoData one, OrcamentoData other) {
                int result = 0;
                if (result == 0) {
                    Date oneValue = one.getDataRealizado();
                    Date otherValue = other.getDataRealizado();
                    result = c.compare(oneValue, otherValue);
                }
                if (result == 0) {
                    Long oneID = one.getId();
                    Long otherID = other.getId();
                    result = c.compare(oneID, otherID);
                }
                return result;
            }
        };

        this.index = this.dataset.addIndex(dateAndIDComparator);

    }

    public void setConnection(SpreadsheetConnection connection) {
        List<OrcamentoData> all = Collections.emptyList();
        if (connection != null && connection.isAutenticated()) {
            this.dao = new SpreadsheetOrcamentoDataDao(connection);
            all = this.dao.getAll();
            super.setData(all, false);
        } else {
            this.dao = null;
            super.setData(all, false);
        }
    }

    @Override
    protected IDataset<OrcamentoData> getDataset() {
        return dataset;
    }

    @Override
    protected DatasetIndex<OrcamentoData> getDatasetIndex() {
        return index;
    }

}
