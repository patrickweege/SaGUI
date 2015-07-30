package com.sagui.ext.controller;

import org.apache.commons.collections.Transformer;

import com.sagui.model.FatuComponent;
import com.sagui.model.container.page.FatuPage;


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
