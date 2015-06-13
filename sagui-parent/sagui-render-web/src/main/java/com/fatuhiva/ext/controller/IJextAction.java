package com.fatuhiva.ext.controller;

import org.apache.commons.collections.Transformer;

import com.fatuhiva.model.FatuComponent;
import com.fatuhiva.model.container.page.FatuPage;


public interface IJextAction<T extends FatuComponent> {

	public FatuPage<?> getPage();
	
	public T getTarget();
	
	public <V> V getParameter(String name);
	
	/**
	 * Get the parameter transform the parameter value with the transformer
	 * @param name
	 * @param transformer
	 * @return Transformed parameter value
	 */
	public <V> V getParameter(String name, Transformer transformer);

    public String getEvent();
	
}
