package com.sagui.ext.common.render;

import com.sagui.ext.common.render.util.RenderWriter;
import com.sagui.model.FatuComponent;

public interface IComponentRender<T extends FatuComponent> extends IRender<T> {
	
	public boolean render(T component, RenderWriter out) throws RenderException;
	public void update(T component, RenderWriter out) throws RenderException;
	
}
