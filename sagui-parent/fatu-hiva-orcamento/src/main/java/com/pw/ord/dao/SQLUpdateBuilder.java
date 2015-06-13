package com.pw.ord.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SQLUpdateBuilder {

    private final List<String> updateColumns;
    private final List<String> whereColumns;
    private final List<Object> updateParams;
    private final List<Object> whereParams;
    private final String table;

    public SQLUpdateBuilder(String tableName) {
        this.updateColumns = new ArrayList<String>();
        this.updateParams = new ArrayList<Object>();
        this.whereColumns = new ArrayList<String>();
        this.whereParams = new ArrayList<Object>();
        this.table = tableName;
    }

    public SQLUpdateBuilder update(String column, Object value) {
        this.updateColumns.add(column);
        this.updateParams.add(value);
        return this;
    }

    public SQLUpdateBuilder where(String column, Object value) {
        this.whereColumns.add(column);
        this.whereParams.add(value);
        return this;
    }

    public String getSQL() {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE ").append(table).append(" SET ");
        for (Iterator<String> iterator = updateColumns.iterator(); iterator.hasNext();) {
            String column = (String) iterator.next();
            sql.append(column).append(" = ?");
            if (iterator.hasNext()) {
                sql.append(", ");
            }
        }

        StringBuilder where = new StringBuilder();
        where.append(" where ");
        for (Iterator<String> iterator = whereColumns.iterator(); iterator.hasNext();) {
            String column = (String) iterator.next();
            where.append(column).append(" = ?");
            if (iterator.hasNext()) {
                where.append(" and ");
            }
        }

        return sql.append(where).toString();
    }

    public Object[] getParams() {
        updateParams.toArray();
        List<Object> params = new ArrayList<Object>(updateParams);
        params.addAll(whereParams);
        return params.toArray();
    }

}
