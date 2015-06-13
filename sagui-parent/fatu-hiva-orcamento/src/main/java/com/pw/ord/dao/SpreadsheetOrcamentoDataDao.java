package com.pw.ord.dao;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;

import com.google.gdata.data.spreadsheet.ListEntry;
import com.pw.ord.et.OrcamentoData;

public class SpreadsheetOrcamentoDataDao extends AbstractSpreadsheetDataDao<OrcamentoData> implements IOrcamentoDomesticoDao<OrcamentoData> {

    private final SimpleDateFormat DATE_FORMAT;
    private final DecimalFormat NUMBER_FORMAT;

    public SpreadsheetOrcamentoDataDao(SpreadsheetConnection connection) {
        super(connection, "TB_ORCAMENTO_DATA");
        this.DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('.');
        symbols.setDecimalSeparator(',');
        this.NUMBER_FORMAT = new DecimalFormat("#0.00", symbols);
        this.NUMBER_FORMAT.setParseIntegerOnly(false);
        this.NUMBER_FORMAT.setParseBigDecimal(true);
    }

    @Override
    protected OrcamentoData toBean(ListEntry entry) {
        OrcamentoData bean = new OrcamentoData();
        populate(entry, bean);
        return bean;
    }

    @Override
    protected ListEntry toListEntry(OrcamentoData bean) {
        ListEntry row = new ListEntry();
        populate(bean, row);
        return row;
    }

    protected void populate(ListEntry source, OrcamentoData target) {
        try {
            String asStr;

            asStr = source.getCustomElements().getValue("id");
            target.setId(Long.valueOf(asStr));

            asStr = source.getCustomElements().getValue("userid");
            target.setUserId(asStr);

            asStr = source.getCustomElements().getValue("realizado");
            target.setDataRealizado(DATE_FORMAT.parse(asStr));

            asStr = source.getCustomElements().getValue("previsto");
            target.setDataPrevisto(DATE_FORMAT.parse(asStr));

            asStr = source.getCustomElements().getValue("contacredito");
            target.setContaCredito(asStr);

            asStr = source.getCustomElements().getValue("contadebito");
            target.setContaDebito(asStr);

            asStr = source.getCustomElements().getValue("centrocusto");
            target.setCentroCusto(asStr);

            asStr = source.getCustomElements().getValue("valor");
            BigDecimal valor = (BigDecimal) NUMBER_FORMAT.parse(asStr);
            target.setValor(valor);

            asStr = source.getCustomElements().getValue("observacao");
            target.setObservacao(asStr);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected void populate(OrcamentoData source, ListEntry target) {
        target.getCustomElements().setValueLocal("id", String.valueOf(source.getId()));
        target.getCustomElements().setValueLocal("userid", source.getUserId());
        target.getCustomElements().setValueLocal("realizado", DATE_FORMAT.format(source.getDataRealizado()));
        target.getCustomElements().setValueLocal("previsto", DATE_FORMAT.format(source.getDataPrevisto()));
        target.getCustomElements().setValueLocal("contacredito", source.getContaCredito());
        target.getCustomElements().setValueLocal("contadebito", source.getContaDebito());
        target.getCustomElements().setValueLocal("centrocusto", source.getCentroCusto());
        target.getCustomElements().setValueLocal("valor", NUMBER_FORMAT.format(source.getValor()));
        target.getCustomElements().setValueLocal("observacao", source.getObservacao());
    }

    @Override
    protected ListEntry getRow(OrcamentoData bean) {
        return super.getRow(bean.getId());
    }

}
