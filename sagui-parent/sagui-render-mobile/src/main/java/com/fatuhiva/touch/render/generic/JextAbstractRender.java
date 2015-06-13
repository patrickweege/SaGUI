package com.fatuhiva.touch.render.generic;

import java.io.IOException;

import com.fatuhiva.ext.common.render.IComponentRender;
import com.fatuhiva.ext.common.render.RenderException;
import com.fatuhiva.ext.common.render.util.RenderWriter;
import com.fatuhiva.model.FatuComponent;

@SuppressWarnings("unchecked")
public class JextAbstractRender<T extends FatuComponent> implements IComponentRender<T> {

	protected final CompositeRender<T> composite;
	private final String extType;

    public JextAbstractRender(String extType) {
		this.composite = new CompositeRender<T>(new JextIDRender<T>());
		this.extType = extType;
	}

	@Override
	public boolean render(T component, RenderWriter out) throws RenderException {
		try {
			out.format("new %1$s({",extType).ln();
			out.ident();
			composite.render(component, out);
			out.udent();
			out.append("})").ln();
		} catch (IOException e) {
			throw new RenderException(e);
		}
		return true;
	}

	@Override
	public void update(T component, RenderWriter out) throws RenderException {
		try {
			out.format("var cmp = Ext.getCmp('%1$s');", component.getId()).ln();
			composite.update(component, out);
		} catch (IOException e) {
			throw new RenderException(e);
		}
	}

}
