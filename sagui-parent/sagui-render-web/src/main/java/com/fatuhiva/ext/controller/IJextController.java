package com.fatuhiva.ext.controller;

import com.fatuhiva.model.FatuComponent;


public interface IJextController<T extends FatuComponent> {
	
	public IActionResult execute(IJextAction<T> jextAction);
	
}
