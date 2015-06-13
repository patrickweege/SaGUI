package com.fatuhiva.ext.render.generic;

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
            FatuPage<?> page = FatuUtil.getPage(component);
            FatuButton cmp = (FatuButton) component;
            if (cmp.getActionListeners().size() > 0) {
                w.writeConfigFmt(extProp, "new FatuhivaButtonClickListener('%1s','%2s').execute", page.getId(), component.getId()).ln();
            } else {
                w.format("%1s: Ext.emptyFn", extProp).ln();
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
