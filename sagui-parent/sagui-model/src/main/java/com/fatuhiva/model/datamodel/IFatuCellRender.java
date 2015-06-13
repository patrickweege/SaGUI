package com.fatuhiva.model.datamodel;

import java.awt.Color;

import com.fatuhiva.model.FatuComponent;


public interface IFatuCellRender {
    
    public Color getColor(FatuComponent component, int row, int col, Object value);
    
    public Color getbackgroundColor(FatuComponent component, int row, int col, Object value);
    
}
