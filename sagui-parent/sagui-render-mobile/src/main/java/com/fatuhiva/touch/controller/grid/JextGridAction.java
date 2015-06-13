package com.fatuhiva.touch.controller.grid;

import com.fatuhiva.model.grid.FatuGrid;
import com.fatuhiva.touch.controller.IJextAction;

public class JextGridAction {

	public enum GridEvent {
		GETDATA
	}
	
	private IJextAction<FatuGrid> webAction;
	private GridEvent evt; 
	
	public JextGridAction(IJextAction<FatuGrid> webAction) {
		this.webAction = webAction;
		evt = GridEvent.valueOf((String)webAction.getParameter("EVENT"));
	}
	
	public GridEvent getEvent() {
		return evt;
	}
	

}
