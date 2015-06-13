package com.fatuhiva.ext.controller.grid;

import com.fatuhiva.ext.controller.IJextAction;
import com.fatuhiva.model.grid.FatuGrid;

public class JextGridAction {

	public enum GridEvent {
		GETDATA
	}
	
	private GridEvent evt; 
	
	public JextGridAction(IJextAction<FatuGrid> webAction) {
		evt = GridEvent.valueOf((String)webAction.getParameter("EVENT"));
	}
	
	public GridEvent getEvent() {
		return evt;
	}
	

}
