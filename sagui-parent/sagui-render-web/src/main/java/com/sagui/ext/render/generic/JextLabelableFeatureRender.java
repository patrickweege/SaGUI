package com.sagui.ext.render.generic;

import com.sagui.ext.common.render.generic.FatuCompositeRender;
import com.sagui.ext.common.render.generic.FatuGenericPropertyRender;
import com.sagui.model.FatuComponent;
import com.sagui.model.feature.FatuLabelPosition;
import com.sagui.model.feature.IFatuLabelableFeature;

@SuppressWarnings("unchecked")
public class JextLabelableFeatureRender<T extends FatuComponent> extends FatuCompositeRender<T> {

    public JextLabelableFeatureRender() {
        addRender(new FatuHivaLabelRender());
        //addRender(new FatuHivaLabelableLabelStyleRender());
        addRender(new FatuHivaLabelWidthRender());
        addRender(new FatuHivaLabelPositionPropertyRender());
    }

    public class FatuHivaLabelRender extends FatuGenericPropertyRender<T> {

        public FatuHivaLabelRender() {
            super("label", "fieldLabel", null, String.class);
        }

    }

    public class FatuHivaLabelWidthRender extends FatuGenericPropertyRender<T> {

        public FatuHivaLabelWidthRender() {
            super("labelWidth", "labelWidth", null, Integer.class);
        }
    }

    public class FatuHivaLabelPositionPropertyRender extends FatuGenericPropertyRender<T> {

        public FatuHivaLabelPositionPropertyRender() {
            super("labelPosition", "labelAlign", null, String.class);
            this.setRenderIffNull(false);
        }

        protected Object getPropertyValue(T component, String prop) {
            FatuLabelPosition pos = (FatuLabelPosition) super.getPropertyValue(component, prop);
            switch (pos) {
                case TOP:
                    return "top";
                case LEFT:
                    return "left";
                case RIGHT:
                    return "right";
                default:
                    return null;
            }
        };

    }

    private class FatuHivaLabelableLabelStyleRender extends FatuGenericPropertyRender<T> {

        public FatuHivaLabelableLabelStyleRender() {
            super("dummy", "labelStyle", null, String.class);
            this.setRenderIffNull(false);
        }

        protected Object getPropertyValue(T component, String prop) {
            if (component instanceof IFatuLabelableFeature) {
                IFatuLabelableFeature cmp = (IFatuLabelableFeature) component;
                if (cmp.getLabelWidth() == null || cmp.getLabelWidth() == 0) {
                    return "white-space: nowrap;";
                }
            }
            return null;
        }
    }

}
