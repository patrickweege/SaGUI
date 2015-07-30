package com.sagui.model.datamodel;

import java.awt.Color;

import com.sagui.model.FatuComponent;


public interface IFatuCellRender {
    
    public Color getColor(FatuComponent component, int row, int col, Object value);
    
    public Color getbackgroundColor(FatuComponent component, int row, int col, Object value);
    
}
