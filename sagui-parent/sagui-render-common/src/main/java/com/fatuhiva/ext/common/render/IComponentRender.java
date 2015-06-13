package com.fatuhiva.ext.common.render;

import com.fatuhiva.ext.common.render.util.RenderWriter;
import com.fatuhiva.model.FatuComponent;

public interface IComponentRender<T extends FatuComponent> extends IRender<T> {
	
	public boolean render(T component, RenderWriter out) throws RenderException;
	public void update(T component, RenderWriter out) throws RenderException;
	
}
