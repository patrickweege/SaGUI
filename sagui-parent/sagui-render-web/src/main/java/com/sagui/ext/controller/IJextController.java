package com.sagui.ext.controller;

import com.sagui.model.FatuComponent;


public interface IJextController<T extends FatuComponent> {
	
	public IActionResult execute(IJextAction<T> jextAction);
	
}
