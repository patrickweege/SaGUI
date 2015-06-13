package com.pw.ord.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.RowProcessor;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.pw.ord.et.OrcamentoData;

public class OrcamentoDataDao implements IOrcamentoDomesticoDao<OrcamentoData> {

    private final Map<String, String> columnsToBean;
    private final RowProcessor rowProcessor;

    public OrcamentoDataDao() {
        columnsToBean = new HashMap<String, String>();
        columnsToBean.put("ID", "id");
        columnsToBean.put("USERID", "usedid");
        columnsToBean.put("REALIZADO", "dataRealizado");
        columnsToBean.put("PREVISTO", "dataPrevisto");
        columnsToBean.put("CONTA_CREDITO", "contaCredito");
        columnsToBean.put("CONTA_DEBITO", "contaDebito");
        columnsToBean.put("CENTRO_CUSTO", "centroCusto");
        columnsToBean.put("VALOR", "valor");
        columnsToBean.put("OBSERVACAO", "observacao");
        this.rowProcessor = new BasicRowProcessor(new BeanProcessor(columnsToBean));
    }

    private Connection getConnection() {
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            Connection c = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/orcamentoDB", "SA", "");
            return c;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public OrcamentoData getByID(Long id) {
        Connection connection = getConnection();
        try {
            return getByID(id, connection);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            DbUtils.closeQuietly(connection);
        }
    }

    public OrcamentoData getNew() {
        OrcamentoData newEmp = new OrcamentoData();
        newEmp.setId(getNextID());
        return newEmp;

    }
    
    @Override
    public OrcamentoData save(OrcamentoData entity) {
        if (entity.getId() <= 0) {
            entity.setId(getNextID());
        }
        Connection connection = getConnection();
        try {
            this.save(entity, connection);
            return this.getByID(entity.getId(), connection);
        } finally {
            DbUtils.closeQuietly(connection);
        }
    }

    public List<OrcamentoData> getAll() {
        Connection connection = getConnection();
        try {
            ResultSetHandler<List<OrcamentoData>> h = new BeanListHandler<OrcamentoData>(OrcamentoData.class, new BasicRowProcessor(new BeanProcessor(this.columnsToBean)));
            QueryRunner run = new QueryRunner();
            List<OrcamentoData> all = run.query(connection, "SELECT * FROM TB_ORCAMENTO_DATA", h);
            return all;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            DbUtils.closeQuietly(connection);
        }
    }

    private void save(OrcamentoData entity, Connection connection) {
        try {
            OrcamentoData found = getByID(entity.getId(), connection);
            if (found != null) {
                update(entity, connection);
            } else {
                insert(entity, connection);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Long id) {
        Connection connection = getConnection();
        try {
            this.delete(id, connection);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            DbUtils.closeQuietly(connection);
        }
    }

    private Long getNextID() {
        Connection connection = getConnection();
        try {
            ResultSetHandler<Long> h = new ScalarHandler<Long>("MAX_ID");
            QueryRunner run = new QueryRunner();
            Long found = run.query(connection, "SELECT MAX(ID) AS MAX_ID FROM TB_ORCAMENTO_DATA", h);
            found = found == null ? 0 : found;
            return found + 1;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            DbUtils.closeQuietly(connection);
        }
    }

    private void insert(OrcamentoData et, Connection connection) {
        try {

            SQLInsertBuilder sql = new SQLInsertBuilder("TB_ORCAMENTO_DATA");
            sql.appendColumn("ID", et.getId());
            sql.appendColumn("USERID", et.getUserId());
            sql.appendColumn("REALIZADO", et.getDataRealizado());
            sql.appendColumn("PREVISTO", et.getDataPrevisto());
            sql.appendColumn("CONTA_CREDITO", et.getContaCredito());
            sql.appendColumn("CONTA_DEBITO", et.getContaDebito());
            sql.appendColumn("CENTRO_CUSTO", et.getCentroCusto());
            sql.appendColumn("VALOR", et.getValor());
            sql.appendColumn("OBSERVACAO", et.getObservacao());
            QueryRunner run = new QueryRunner();
            run.update(connection, sql.getSQL(), sql.getParams());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void update(OrcamentoData et, Connection connection) {
        try {
            QueryRunner run = new QueryRunner();

            SQLUpdateBuilder sql = new SQLUpdateBuilder("TB_ORCAMENTO_DATA");
            sql.update("USERID", et.getUserId());
            sql.update("REALIZADO", et.getDataRealizado());
            sql.update("PREVISTO", et.getDataPrevisto());
            sql.update("CONTA_CREDITO", et.getContaCredito());
            sql.update("CONTA_DEBITO", et.getContaDebito());
            sql.update("CENTRO_CUSTO", et.getCentroCusto());
            sql.update("VALOR", et.getValor());
            sql.update("OBSERVACAO", et.getObservacao());
            sql.where("ID", et.getId());
            run.update(connection, sql.getSQL(), sql.getParams());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void delete(Long id, Connection connection) {
        try {
            QueryRunner run = new QueryRunner();
            run.update(connection, "DELETE FROM TB_ORCAMENTO_DATA WHERE ID = ?", id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private OrcamentoData getByID(Long id, Connection connection) {
        try {
            ResultSetHandler<OrcamentoData> h = new BeanHandler<OrcamentoData>(OrcamentoData.class, this.rowProcessor);

            QueryRunner run = new QueryRunner();
            OrcamentoData found = run.query(connection, "SELECT * FROM TB_ORCAMENTO_DATA WHERE ID = ?", h, id);

            return found;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
