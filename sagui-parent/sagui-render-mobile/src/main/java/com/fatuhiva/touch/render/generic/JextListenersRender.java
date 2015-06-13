package com.fatuhiva.touch.render.generic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fatuhiva.ext.common.render.IComponentRender;
import com.fatuhiva.ext.common.render.RenderException;
import com.fatuhiva.ext.common.render.util.RenderWriter;
import com.fatuhiva.model.FatuComponent;

public class JextListenersRender<T extends FatuComponent> implements IComponentRender<T> {

	private List<IComponentRender<T>> listeners;
	

	public JextListenersRender() {
		this.listeners = new ArrayList<IComponentRender<T>>();
	}
	
	public void addRender(IComponentRender<T> render) {
		listeners.add(render); 
	}
	
	@Override
	public boolean render(T component, RenderWriter out) throws RenderException {
        try {
        	out.tab().append("listeners : {").ln();
        	out.ident();
        	for (Iterator<IComponentRender<T>> iterator = listeners.iterator(); iterator.hasNext();) {
				IComponentRender<T> listener = iterator.next();
				boolean rendered = listener.render(component, out);
				if(rendered) out.pushComma();
			}
        	out.udent();
        	out.popComma();
        	out.tab().append("}").ln();
        } catch (Exception e) {
            throw new RenderException(e);
        }
        return true;
	}

	@Override
	public void update(T component, RenderWriter out) throws RenderException {
		// TODO Auto-generated method stub
	}
	
}
