package com.fatuhiva.ext.common.render;

import com.fatuhiva.ext.common.render.util.RenderWriter;

public interface IRender<T> {
	
	public boolean render(T component, RenderWriter out) throws RenderException;
	public void update(T component, RenderWriter out) throws RenderException;
	
}
