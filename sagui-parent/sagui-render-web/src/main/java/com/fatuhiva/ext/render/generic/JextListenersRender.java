package com.fatuhiva.ext.render.generic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fatuhiva.ext.common.render.IRender;
import com.fatuhiva.ext.common.render.RenderException;
import com.fatuhiva.ext.common.render.util.RenderWriter;

public class JextListenersRender<T> implements IRender<T> {

	private List<IRender<T>> listeners;
	

	public JextListenersRender() {
		this.listeners = new ArrayList<IRender<T>>();
	}
	
	public void addRender(IRender<T> render) {
		listeners.add(render); 
	}
	
	@Override
	public boolean render(T component, RenderWriter out) throws RenderException {
	    boolean wasAnyRendered = false;
        try {
        	out.tab().append("listeners : {").ln();
        	out.ident();
        	for (Iterator<IRender<T>> iterator = listeners.iterator(); iterator.hasNext();) {
        	    IRender<T> listener = iterator.next();
				boolean rendered = listener.render(component, out);
				if(rendered) out.pushComma();
				wasAnyRendered = wasAnyRendered || rendered;
			}
        	out.udent();
        	if(wasAnyRendered) out.popComma();
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
