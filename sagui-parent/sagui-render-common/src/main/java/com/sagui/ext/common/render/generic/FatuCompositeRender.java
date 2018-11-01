package com.sagui.ext.common.render.generic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sagui.ext.common.render.IRender;
import com.sagui.ext.common.render.RenderException;
import com.sagui.ext.common.render.util.RenderWriter;

public class FatuCompositeRender<T> implements IRender<T> {

    private final List<IRender<T>> renders;

    @SafeVarargs
    public FatuCompositeRender(IRender<T>... renders) {
        this.renders = new ArrayList<IRender<T>>();
        this.renders.addAll(Arrays.asList(renders));
    }

    public void addRender(IRender<T> renders) {
        this.renders.add(renders);
    }

    @Override
    public boolean render(T component, RenderWriter out) throws RenderException {
    	boolean wasAnyRendered = false; 
        for (IRender<T> render : renders) {
            boolean rendered = render.render(component, out);
            if (rendered) {
            	wasAnyRendered = wasAnyRendered || rendered;
                out.pushComma();
            }
        }
        out.popComma();
        return true;
    }

    @Override
    public void update(T component, RenderWriter out) throws RenderException {
        for (IRender<T> render : renders) {
            render.update(component, out);
        }
    }

    public final int size() {
        return renders.size();
    }

}
