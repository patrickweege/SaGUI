package com.fatuhiva.ext.common.render.generic;

import com.fatuhiva.ext.common.render.IComponentRender;
import com.fatuhiva.ext.common.render.util.RenderWriter;
import com.fatuhiva.model.FatuComponent;

public class FatuExtFixedValuePropertyRender<T extends FatuComponent> implements IComponentRender<T> {

    private String extProp;
    private Object value;

    public FatuExtFixedValuePropertyRender(String extProp, Object value) {
        this.extProp = extProp;
        this.value = value;
    }

    @Override
    public boolean render(T component, RenderWriter w) {
        boolean rendered = false;
        try {
            if (value instanceof Integer) {
                w.format("%1$s : %2$d", extProp, value).ln();
                rendered = true;
            }
            if (value instanceof String) {
                w.format("%1$s : '%2$s'", extProp, value).ln();
                rendered = true;
            }
            if (value instanceof Boolean) {
                w.format("%1$s : %2$s", extProp, value == Boolean.TRUE ? "true" : "false").ln();
                rendered = true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return rendered;
    }

    @Override
    public void update(T component, RenderWriter out) {
    }

}
