package com.fatuhiva.model.datamodel;


public interface IFatuColumnModel {
	
	public int getColumnCount();

	public String getColumnName(int columnIndex);
	
	public String getColumnLabel(int columnIndex);
	
	public String getColumnHint(int columnIndex);
	
	public IFatuCellRender getCellRenderer(int columnIndex);

	public void setCellRenderer(int columnIndex, IFatuCellRender cellRender);
	
	public boolean isVisible(int columIndex);
	
	public void setVisible(boolean visible, int columIndex);

}
