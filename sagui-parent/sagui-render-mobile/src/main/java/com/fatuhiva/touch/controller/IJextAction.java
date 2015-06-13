package com.fatuhiva.touch.controller;

import com.fatuhiva.model.FatuComponent;
import com.fatuhiva.model.container.page.FatuPage;


public interface IJextAction<T extends FatuComponent> {

	public FatuPage getPage();
	
	public T getTarget();
	
	public <V> V getParameter(String parameter);
	
    public String getEvent();
	
}
