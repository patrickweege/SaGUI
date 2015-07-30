package com.sagui.ext.render.textbox;

import com.sagui.ext.common.render.IComponentRender;
import com.sagui.ext.common.render.RenderException;
import com.sagui.ext.common.render.generic.FatuAbstractRender;
import com.sagui.ext.common.render.util.RenderWriter;
import com.sagui.ext.render.generic.FatuMarginFeatureRender;
import com.sagui.ext.render.generic.JextEnabledFeatureRender;
import com.sagui.ext.render.generic.JextErrorMessageRender;
import com.sagui.ext.render.generic.JextLabelableFeatureRender;
import com.sagui.ext.render.generic.JextListenersRender;
import com.sagui.ext.render.generic.JextSizeFeatureRender;
import com.sagui.ext.render.generic.JextVisibleFeatureRender;
import com.sagui.model.container.page.FatuPage;
import com.sagui.model.editable.editbox.FatuTextBox;
import com.sagui.model.util.FatuUtil;

public class JextTextBoxRender extends FatuAbstractRender<FatuTextBox> {

    public JextTextBoxRender() {
        super("Ext.form.TextField");
        super.composite.addRender(new JextTextBoxValueRender());
        super.composite.addRender(new JextLabelableFeatureRender<FatuTextBox>());
        super.composite.addRender(new JextErrorMessageRender<FatuTextBox>());
        super.composite.addRender(new JextEnabledFeatureRender<FatuTextBox>());
        super.composite.addRender(new JextVisibleFeatureRender<FatuTextBox>());
        super.composite.addRender(new FatuMarginFeatureRender<FatuTextBox>());
        super.composite.addRender(new JextSizeFeatureRender<FatuTextBox>());
        
        JextListenersRender<FatuTextBox> listenerRender = new JextListenersRender<FatuTextBox>();
        listenerRender.addRender(new OnBlurListenerRender());

        super.composite.addRender(listenerRender);
    }

    private class OnBlurListenerRender implements IComponentRender<FatuTextBox> {

        @Override
        public boolean render(FatuTextBox component, RenderWriter out) throws RenderException {
            try {
                FatuPage<?> page = FatuUtil.getPage(component);
                out.format("blur : new FatuhivaTextBoxOnBlurListener('%1s','%2s').execute", page.getId(), component.getId()).ln();
            } catch (Exception e) {
                throw new RenderException(e);
            }
            return true;

        }

        @Override
        public void update(FatuTextBox component, RenderWriter out) throws RenderException {

        }

    };

}
