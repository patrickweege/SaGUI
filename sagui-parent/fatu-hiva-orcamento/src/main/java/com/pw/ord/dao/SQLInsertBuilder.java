package com.pw.ord.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SQLInsertBuilder {

    private final List<String> columns;
    private final List<Object> params;
    private final String table;

    public SQLInsertBuilder(String tableName) {
        this.columns = new ArrayList<String>();
        this.params = new ArrayList<Object>();
        this.table = tableName;
    }

    public SQLInsertBuilder appendColumn(String column, Object value) {
        this.columns.add(column);
        this.params.add(value);
        return this;
    }

    public String getSQL() {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO ").append(table).append(" (");

        StringBuilder values = new StringBuilder();
        values.append("values (");
        for (Iterator<String> iterator = columns.iterator(); iterator.hasNext();) {
            String column = (String) iterator.next();
            sql.append(column);
            values.append("?");
            if (iterator.hasNext()) {
                sql.append(", ");
                values.append(", ");
            } else {
                sql.append(") ");
                values.append(") ");
            }
        }
        return sql.append(values).toString();
    }

    public Object[] getParams() {
        return params.toArray();
    }

}
