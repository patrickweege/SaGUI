package com.sagui.ext.render.layout;

import java.io.IOException;

import com.sagui.ext.common.render.IComponentRender;
import com.sagui.ext.common.render.RenderException;
import com.sagui.ext.common.render.util.RenderWriter;
import com.sagui.ext.render.generic.PaddingFeatureRender;
import com.sagui.model.FatuComponent;
import com.sagui.model.feature.IFatuLayoutFeature;
import com.sagui.model.feature.IFatuPaddingFeature;
import com.sagui.model.layout.IFatuLayoutManager;
import com.sagui.model.layout.box.FatuHBoxLayout;
import com.sagui.model.layout.box.FatuVBoxLayout;

public class JextBoxLayoutPropertyRender<T extends FatuComponent & IFatuLayoutFeature<?>> implements IComponentRender<T> {

    private final PaddingFeatureRender<IFatuPaddingFeature> PADDING_RENDER;
    
    public JextBoxLayoutPropertyRender() {
        this.PADDING_RENDER = new PaddingFeatureRender<IFatuPaddingFeature>();
    }
    
    @Override
    public boolean render(T component, RenderWriter out) throws RenderException {
        boolean rendered = false;
        IFatuLayoutManager<?> layout = component.getLayout();
        if (layout != null && layout instanceof FatuHBoxLayout) {
            return render(component, out, (FatuHBoxLayout) layout);
        } else if (layout != null && layout instanceof FatuVBoxLayout) {
            return render(component, out, (FatuVBoxLayout) layout);
        }
        return rendered;
    }

    @Override
    public void update(T component, RenderWriter out) throws RenderException {
    }

    private boolean render(T component, RenderWriter out, FatuHBoxLayout hBox) {
        try {
            out.tab().append("layout : {").ln();
            out.ident();
            out.tab().writeConfigAsString("type", "hbox").ln().pushComma();

            switch (hBox.getAlign()) {
                case CENTER:
                    out.tab().writeConfigAsString("align", "middle").ln().pushComma();
                    break;
                case START:
                    out.tab().writeConfigAsString("align", "begin").ln().pushComma();
                    break;
                case END:
                    out.tab().writeConfigAsString("align", "end").ln().pushComma();
                    break;
                case STRETCH:
                    out.tab().writeConfigAsString("align", "stretch").ln().pushComma();
                    break;
                case STRETCH_TO_LARGEST:
                    out.tab().writeConfigAsString("align", "stretchmax").ln().pushComma();
                    break;
                default:
                    throw new IllegalStateException("Render canot handle the value of Align property: " + hBox.getAlign());
            }

            switch (hBox.getPosition()) {
                case START:
                    out.tab().writeConfigAsString("pack", "start").ln().pushComma();
                    break;
                case CENTER:
                    out.tab().writeConfigAsString("pack", "center").ln().pushComma();
                    break;
                case END:
                    out.tab().writeConfigAsString("pack", "end").ln().pushComma();
                    break;
                default:
                    throw new IllegalStateException("Render canot handle the value of Position property: " + hBox.getPosition());
            }

            if(hBox.getPadding() != null) {
                PADDING_RENDER.render(hBox, out);
            }
            
            out.udent();
            out.tab().append("}").ln();
            return true;
        } catch (IOException e) {
            throw new RenderException(e);
        }
    }

    private boolean render(T component, RenderWriter out, FatuVBoxLayout vBox) {
        try {
            out.tab().append("layout : {").ln();
            out.ident();
            out.tab().writeConfigAsString("type", "vbox").ln().pushComma();

            switch (vBox.getAlign()) {
                case CENTER:
                    out.tab().writeConfigAsString("align", "middle").ln().pushComma();
                    break;
                case START:
                    out.tab().writeConfigAsString("align", "begin").ln().pushComma();
                    break;
                case END:
                    out.tab().writeConfigAsString("align", "end").ln().pushComma();
                    break;
                case STRETCH:
                    out.tab().writeConfigAsString("align", "stretch").ln().pushComma();
                    break;
                case STRETCH_TO_LARGEST:
                    out.tab().writeConfigAsString("align", "stretchmax").ln().pushComma();
                    break;
                default:
                    throw new IllegalStateException("Render canot handle the value of Align property: " + vBox.getAlign());
            }

            switch (vBox.getPosition()) {
                case START:
                    out.tab().writeConfigAsString("pack", "start").ln().pushComma();
                    break;
                case CENTER:
                    out.tab().writeConfigAsString("pack", "center").ln().pushComma();
                    break;
                case END:
                    out.tab().writeConfigAsString("pack", "end").ln().pushComma();
                    break;
                default:
                    throw new IllegalStateException("Render canot handle the value of Position property: " + vBox.getPosition());
            }
            
            if(vBox.getPadding() != null) {
                PADDING_RENDER.render(vBox, out);
            }

            out.udent();
            out.tab().append("}").ln();
            return true;
        } catch (IOException e) {
            throw new RenderException(e);
        }
    }

}
