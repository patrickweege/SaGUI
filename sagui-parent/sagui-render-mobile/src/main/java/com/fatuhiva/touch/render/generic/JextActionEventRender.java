package com.fatuhiva.touch.render.generic;

import com.fatuhiva.ext.common.render.IComponentRender;
import com.fatuhiva.ext.common.render.RenderException;
import com.fatuhiva.ext.common.render.util.RenderWriter;
import com.fatuhiva.model.FatuComponent;
import com.fatuhiva.model.button.FatuButton;
import com.fatuhiva.model.container.page.FatuPage;
import com.fatuhiva.model.util.FatuUtil;

public abstract class JextActionEventRender<T extends FatuComponent> implements IComponentRender<T> {

	private String extProp;

	public JextActionEventRender(String extProp) {
		this.extProp = extProp;
	}

	@Override
	public boolean render(T component, RenderWriter w) throws RenderException {
		try {
			FatuPage page = FatuUtil.getPage(component);
			FatuButton cmp = (FatuButton) component;
			if(cmp.getActionListeners().size() > 0) {
				w.format("%1s: function() {", extProp).ln();
				w.append("	Ext.Ajax.request({").ln();
				w.append("		url: '../action',").ln();
				w.format("		params: { PAGE_ID: '%1s', COMPONENT_ID: '%2s'},", page.getId(), component.getId()).ln();
				w.append("		success: function(response, opts) {").ln();
				w.append("			eval(response.responseText);").ln();
				w.append("		}").ln();
				w.append("	});").ln();
				w.append("}").ln();			
			} else {
				w.format("%1s: function() {}", extProp).ln();
			}
		} catch (Exception e) {
			throw new RenderException(e);
		}
		return true;
	}

	@Override
	public void update(T component, RenderWriter out) throws RenderException {
	}

}
