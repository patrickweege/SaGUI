package com.fatuhiva.touch.render.generic;

import java.io.IOException;

import com.fatuhiva.ext.common.render.IComponentRender;
import com.fatuhiva.ext.common.render.RenderException;
import com.fatuhiva.ext.common.render.util.RenderWriter;
import com.fatuhiva.model.container.form.FatuForm;

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
