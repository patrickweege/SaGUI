package com.fatuhiva.model.list;

import com.fatuhiva.model.datamodel.IFatuTableModel;


public interface IFatuListModel<T> extends IFatuTableModel<T>  {
    
    public int getKeyColumn();
    public int getTextColumn();
    
    public String getKey(int row);
    public String getLabel(int row);
    
}
