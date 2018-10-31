package com.sagui.model.container.menu;

import java.util.Arrays;
import java.util.List;

import com.sagui.model.FatuContainer;

public class FatuMenu<T extends FatuMenuItem> extends FatuContainer<T> {
	
	public FatuMenu(List<T> menuItems) {
		this.internalAddItems(menuItems);
	}
	
	@SafeVarargs
	public FatuMenu(T ...menuItems) {
		this.internalAddItems(Arrays.asList(menuItems));
	}
	
	
	private void internalAddItems(List<T> menuItems) {
		for (T mItem : menuItems) {
			this.addChild(mItem);
		}
	}
	
}
