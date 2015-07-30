package com.sagui.ext.common.render;

import com.sagui.ext.common.render.util.RenderWriter;

public interface IRender<T> {
	
	public boolean render(T component, RenderWriter out) throws RenderException;
	public void update(T component, RenderWriter out) throws RenderException;
	
}
