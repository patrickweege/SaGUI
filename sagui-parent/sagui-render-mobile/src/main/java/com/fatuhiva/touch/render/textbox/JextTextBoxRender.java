package com.fatuhiva.touch.render.textbox;

import com.fatuhiva.ext.common.render.IComponentRender;
import com.fatuhiva.ext.common.render.RenderException;
import com.fatuhiva.ext.common.render.util.RenderWriter;
import com.fatuhiva.model.container.page.FatuPage;
import com.fatuhiva.model.editable.editbox.FatuTextBox;
import com.fatuhiva.model.util.FatuUtil;
import com.fatuhiva.touch.render.generic.JextAbstractRender;
import com.fatuhiva.touch.render.generic.JextEnabledFeatureRender;
import com.fatuhiva.touch.render.generic.JextErrorMessageRender;
import com.fatuhiva.touch.render.generic.JextLabelableFeatureRender;
import com.fatuhiva.touch.render.generic.JextListenersRender;
import com.fatuhiva.touch.render.generic.JextVisibleFeatureRender;
import com.fatuhiva.touch.render.generic.MarginFeatureRender;

public class JextTextBoxRender extends  JextAbstractRender<FatuTextBox> {

    public JextTextBoxRender() {
        super("Ext.form.TextField");
        super.composite.addRender(new JextTextBoxValueRender());
        super.composite.addRender(new JextLabelableFeatureRender<FatuTextBox>());
        super.composite.addRender(new JextErrorMessageRender<FatuTextBox>());
        super.composite.addRender(new JextEnabledFeatureRender<FatuTextBox>());
        super.composite.addRender(new JextVisibleFeatureRender<FatuTextBox>());
        super.composite.addRender(new MarginFeatureRender<FatuTextBox>());
        
        JextListenersRender<FatuTextBox> listenerRender = new JextListenersRender<FatuTextBox>();
        listenerRender.addRender(new OnBlurListenerRender());
        
        super.composite.addRender(listenerRender);
    }

    
    
    private class OnBlurListenerRender implements IComponentRender<FatuTextBox> {

        @Override
        public boolean render(FatuTextBox component, RenderWriter out) throws RenderException {
            try {
                FatuPage<?> page = FatuUtil.getPage(component);
                out.tab().append("blur : function(field, opts) {").ln();
                out.tab().append("   if(field.isDirty()) {").ln();
                out.tab().append("      Ext.Ajax.request({").ln();
                out.tab().append("         url: '../action',").ln();
                out.tab().format("         params: { PAGE_ID: '%1s', COMPONENT_ID: '%2s', NEW_VALUE : field.getValue()},", page.getId(), component.getId()).ln();
                out.tab().append("         success: function(response, opts) {").ln();
                out.tab().append("            eval(response.responseText);").ln();
                out.tab().append("         }").ln();
                out.tab().append("      });// Ajax Request").ln();
                out.tab().append("   }// Is Dirty").ln();
                out.tab().append("}// blur listener").ln();
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
