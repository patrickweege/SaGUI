package com.pw.ord.dao;

import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.spreadsheet.ListEntry;
import com.google.gdata.data.spreadsheet.ListFeed;
import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.google.gdata.data.spreadsheet.WorksheetEntry;
import com.google.gdata.data.spreadsheet.WorksheetFeed;

public abstract class AbstractSpreadsheetDataDao<T> implements IOrcamentoDomesticoDao<T> {

    private SpreadsheetConnection connection;
    private String tableName;
    private WorksheetEntry worksheet;

    public AbstractSpreadsheetDataDao(SpreadsheetConnection connection, String tableName) {
        this.connection = connection;
        this.tableName = tableName;
        this.worksheet = getWorksheet();
    }

    @Override
    public T getByID(Long id) {
        T data = null;
        try {
            ListEntry row = getRow(id);
            if (row != null) {
                data = toBean(row);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    @Override
    public T getNew() {
        return null;
    }

    @Override
    public T save(T toSave) {
        ListEntry row;
        try {
            row = getRow(toSave);
            if (row != null) {
                populate(toSave, row);
                row.update();
            } else {
                row = new ListEntry();
                URL listFeedUrl = worksheet.getListFeedUrl();
                SpreadsheetService service = connection.getSpreadsheetService();
                this.populate(toSave, row);
                row = service.insert(listFeedUrl, row);
            }
            row = getRow(toSave);
            return toBean(row);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<T> getAll() {
        List<T> all = new ArrayList<T>();
        try {
            String queryParams = "id>0";
            queryParams = URLEncoder.encode(queryParams, "UTF-8");

            StringBuilder strURL = new StringBuilder();
            strURL.append(worksheet.getListFeedUrl().toString());
            strURL.append("?sq=").append(queryParams);
            strURL.append("&orderby=realizado");

            URL url = new URI(strURL.toString()).toURL();
            ListFeed feed = connection.getSpreadsheetService().getFeed(url, ListFeed.class);
            List<ListEntry> rows = feed.getEntries();
            if (!rows.isEmpty()) {
                for (ListEntry row : rows) {
                    T aBean = toBean(row);
                    all.add(aBean);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return all;
    }

    @Override
    public void delete(Long id) {
        try {
            ListEntry row = getRow(id);
            if (row != null) {
                row.delete();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected abstract T toBean(ListEntry entry);

    protected abstract ListEntry toListEntry(T bean);

    protected abstract void populate(T source, ListEntry target);

    protected abstract void populate(ListEntry source, T target);

    protected abstract ListEntry getRow(T bean);

    protected ListEntry getRow(Long id) {
        ListEntry data = null;
        try {
            String queryParams = String.format("id=%1$s", id);
            queryParams = URLEncoder.encode(queryParams, "UTF-8");

            StringBuilder strURL = new StringBuilder();
            strURL.append(worksheet.getListFeedUrl().toString());
            strURL.append("?sq=").append(queryParams);
            strURL.append("&orderby=realizado");

            URL url = new URI(strURL.toString()).toURL();
            ListFeed feed = connection.getSpreadsheetService().getFeed(url, ListFeed.class);
            List<ListEntry> rows = feed.getEntries();
            if (!rows.isEmpty()) {
                data = rows.get(0);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    private WorksheetEntry getWorksheet() {
        try {
            SpreadsheetEntry spreadsheet = connection.getSpreadsheet();
            WorksheetFeed worksheetFeed = connection.getSpreadsheetService().getFeed(spreadsheet.getWorksheetFeedUrl(), WorksheetFeed.class);
            List<WorksheetEntry> worksheets = worksheetFeed.getEntries();
            for (WorksheetEntry worksheet : worksheets) {
                if (StringUtils.equals(tableName, worksheet.getTitle().getPlainText())) {
                    return worksheet;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
