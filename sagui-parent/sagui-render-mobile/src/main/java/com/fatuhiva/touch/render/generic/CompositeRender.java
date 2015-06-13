package com.fatuhiva.touch.render.generic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fatuhiva.ext.common.render.IComponentRender;
import com.fatuhiva.ext.common.render.RenderException;
import com.fatuhiva.ext.common.render.util.RenderWriter;
import com.fatuhiva.model.FatuComponent;

public class CompositeRender<T extends FatuComponent> implements IComponentRender<T> {

	private final List<IComponentRender<T>> renders;
	
	public CompositeRender(IComponentRender<T>... renders) {
		this.renders = new ArrayList<IComponentRender<T>>();
		this.renders.addAll(Arrays.asList(renders));
	}
	
	public void addRender(IComponentRender<T> renders) {
		this.renders.add(renders);
	}
	
	@Override
	public boolean render(T component, RenderWriter out) throws RenderException {
		for (IComponentRender<T> render : renders) {
			boolean rendered = render.render(component, out);
			if(rendered) {
				out.pushComma();
			}
		}
		out.popComma();
		return true;
	}

	@Override
	public void update(T component, RenderWriter out) throws RenderException {
		for (IComponentRender<T> render : renders) {
			render.update(component, out);
		}
	}
}
