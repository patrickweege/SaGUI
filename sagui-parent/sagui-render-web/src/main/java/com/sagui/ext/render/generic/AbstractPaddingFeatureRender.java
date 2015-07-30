package com.sagui.ext.render.generic;

import java.awt.Insets;
import java.io.IOException;
import java.text.MessageFormat;

import com.sagui.ext.common.render.IRender;
import com.sagui.ext.common.render.RenderException;
import com.sagui.ext.common.render.util.RenderWriter;
import com.sagui.model.feature.IFatuPaddingFeature;

public abstract class AbstractPaddingFeatureRender<T extends IFatuPaddingFeature> implements IRender<T> {

    private final String extPaddingProperty;

    public AbstractPaddingFeatureRender(String extPaddingProperty) {
        this.extPaddingProperty = extPaddingProperty;
    }
    
    @Override
    public boolean render(T component, RenderWriter out) throws RenderException {
        boolean rendered = false;
        if (component instanceof IFatuPaddingFeature) {
            try {
                Insets padding = component.getPadding();
                if(padding != null) {
                    String fmt = MessageFormat.format("{0} : ''{1} {2} {3} {4}''", extPaddingProperty, padding.top, padding.right, padding.bottom, padding.left);
                    out.appendLn(fmt);
                    rendered = true;
                }
            } catch (IOException e) {
                throw new RenderException(e);
            }
        }
        return rendered;
    }

    @Override
    public void update(T component, RenderWriter out) throws RenderException {
        // Do nothind
    }
}
