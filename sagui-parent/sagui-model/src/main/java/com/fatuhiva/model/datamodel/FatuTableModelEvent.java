package com.fatuhiva.model.datamodel;



public class FatuTableModelEvent {

    public enum FatuTableModelEventType {
        INSERT, UPDATE, REMOVE, DATA_RESET,
    }
    
    private final FatuTableModelEventType type;
    private final Object source;
    private final int column;
    private final int firstRow;
    private final int lastRow;
    private final Object data;

    public FatuTableModelEvent(Object source, int column, int firstRow, int lastRow, Object data, FatuTableModelEventType type) {
        this.source = source;
        this.column = column;
        this.firstRow = firstRow;
        this.lastRow = lastRow;
        this.data = data;
        this.type = type;
    }
    
    
    public int getFirstRow() {
        return firstRow;
    }
    
    
    public int getLastRow() {
        return lastRow;
    }
    
    public int getColumn() {
        return column;
    }
    
    public FatuTableModelEventType getType() {
        return type;
    }
    
    public Object getSource() {
        return source;
    }
    
    @SuppressWarnings("unchecked")
    public <V> V getData() {
        return (V) data;
    }
    
}
