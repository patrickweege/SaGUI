package com.fatuhiva.ext.render.generic;

import com.fatuhiva.ext.common.render.RenderException;
import com.fatuhiva.ext.common.render.generic.FatuGenericPropertyRender;
import com.fatuhiva.ext.common.render.util.RenderWriter;
import com.fatuhiva.model.editable.FatuEditable;

public class JextErrorMessageRender<T extends FatuEditable> extends FatuGenericPropertyRender<T> {

	public JextErrorMessageRender() {
		super("errorMsg", "activeError","setActiveError", String.class);
		super.setRenderIffNull(false);
	}
	
	@Override
	public void update(T component, RenderWriter out) throws RenderException {
        super.update(component, out);
        try {
            if(component.getErrorMsg() != null) {
                out.append("if(cmp != null) cmp.markInvalid(cmp.getActiveErrors())").ln();
            }
        } catch (Exception e) {
            throw new RenderException(e);
        }
	}

}
