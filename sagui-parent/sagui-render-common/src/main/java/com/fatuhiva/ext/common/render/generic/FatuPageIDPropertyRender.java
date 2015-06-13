package com.fatuhiva.ext.common.render.generic;

import java.io.IOException;

import com.fatuhiva.ext.common.render.IComponentRender;
import com.fatuhiva.ext.common.render.RenderException;
import com.fatuhiva.ext.common.render.util.RenderWriter;
import com.fatuhiva.model.FatuComponent;
import com.fatuhiva.model.container.page.FatuPage;
import com.fatuhiva.model.util.FatuUtil;

public class FatuPageIDPropertyRender<T extends FatuComponent> implements IComponentRender<T> {

	@Override
	public boolean render(T component, RenderWriter out) throws RenderException {
		try {
            FatuPage<?> page = FatuUtil.getPage(component);
			out.tab().writeConfigAsString("pageID", page.getId()).ln();
		} catch (IOException e) {
			throw new RenderException(e);
		}
		return true;
	}

	@Override
	public void update(T component, RenderWriter out) throws RenderException {
		
	}
}
