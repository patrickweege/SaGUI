package com.pw.ord.et;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.LongConverter;
import org.apache.commons.lang.StringEscapeUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

public class OrcamentoData {

    private Long id;
    private String userId;
    private Date dataRealizado;
    private Date dataPrevisto;
    private String contaCredito;
    private String contaDebito;
    private String centroCusto;
    private BigDecimal valor;
    private String observacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getDataRealizado() {
        return dataRealizado;
    }

    public void setDataRealizado(Date dataRealizado) {
        this.dataRealizado = dataRealizado;
    }

    public Date getDataPrevisto() {
        return dataPrevisto;
    }

    public void setDataPrevisto(Date dataPrevisto) {
        this.dataPrevisto = dataPrevisto;
    }

    public String getContaCredito() {
        return contaCredito;
    }

    public void setContaCredito(String contaCredito) {
        this.contaCredito = contaCredito;
    }

    public String getContaDebito() {
        return contaDebito;
    }

    public void setContaDebito(String contaDebito) {
        this.contaDebito = contaDebito;
    }

    public String getCentroCusto() {
        return centroCusto;
    }

    public void setCentroCusto(String centroCusto) {
        this.centroCusto = centroCusto;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
    
    
    public static void main(String[] args) throws JSONException {
        

        System.out.println(StringEscapeUtils.escapeJavaScript("teste 'yuppi"));
        System.out.println(StringEscapeUtils.escapeJavaScript("Need tips? Visit W3Schools!"));
        

    }
    
}
