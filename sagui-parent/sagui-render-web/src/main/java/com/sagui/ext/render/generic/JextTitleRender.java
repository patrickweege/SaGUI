package com.sagui.ext.render.generic;

import java.io.IOException;

import com.sagui.ext.common.render.IComponentRender;
import com.sagui.ext.common.render.RenderException;
import com.sagui.ext.common.render.util.RenderWriter;
import com.sagui.model.container.form.FatuForm;

@SuppressWarnings("rawtypes")
public class JextTitleRender implements IComponentRender<FatuForm> {

	@Override
	public boolean render(FatuForm component, RenderWriter out) throws RenderException {
		try {
			out.tab().writeConfigAsString("title", component.getTitle()).ln();
		} catch (IOException e) {
			throw new RenderException(e);
		}
		return true;
	}

	@Override
	public void update(FatuForm component, RenderWriter out)
			throws RenderException {

	}
}
