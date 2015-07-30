package com.sagui.ext.common.render.generic;

import java.io.IOException;

import com.sagui.ext.common.render.IComponentRender;
import com.sagui.ext.common.render.RenderException;
import com.sagui.ext.common.render.util.RenderWriter;
import com.sagui.model.FatuComponent;

@SuppressWarnings("unchecked")
public class FatuAbstractRender<T extends FatuComponent> implements IComponentRender<T> {

	protected final FatuCompositeRender<T> composite;
	private final String extType;

    public FatuAbstractRender(String extType) {
        this.composite = new FatuCompositeRender<T>();
		this.composite.addRender(new FatuIDPropertyRender<T>());
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
