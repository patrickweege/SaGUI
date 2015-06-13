package com.fatuhiva.touch.render.checkbox;

import com.fatuhiva.ext.common.render.IComponentRender;
import com.fatuhiva.ext.common.render.RenderException;
import com.fatuhiva.ext.common.render.util.RenderWriter;
import com.fatuhiva.model.container.page.FatuPage;
import com.fatuhiva.model.editable.list.checkbox.FatuCheckbox;
import com.fatuhiva.model.util.FatuUtil;
import com.fatuhiva.touch.render.generic.JextAbstractRender;
import com.fatuhiva.touch.render.generic.JextGenericPropertyRender;
import com.fatuhiva.touch.render.generic.JextListenersRender;

public class JextCheckboxRender extends JextAbstractRender<FatuCheckbox> {

	public JextCheckboxRender() {
		super("Ext.form.field.Checkbox");
		super.composite.addRender(new JextGenericPropertyRender<FatuCheckbox>("label","boxLabel",null,String.class));
		super.composite.addRender(new JextGenericPropertyRender<FatuCheckbox>("value","checked","setValue",Boolean.class));
		//super.composite.addRender(new JextErrorMessageRender<JextCheckboxGroup>());
		//super.composite.addRender(new JextComboSelectionRender());
		//super.composite.addRender(new JextCheckboxItemsRender());
		
		JextListenersRender<FatuCheckbox> listenerRender = new JextListenersRender<FatuCheckbox>();
        listenerRender.addRender(new JextCheckboxOnChangeListenerRender());
        
        super.composite.addRender(listenerRender);

	}

	@Override
	public boolean render(FatuCheckbox component, RenderWriter out) throws RenderException {
		return super.render(component, out);
	}
	
	
    private class JextCheckboxOnChangeListenerRender implements IComponentRender<FatuCheckbox> {

        @Override
        public boolean render(FatuCheckbox component, RenderWriter out) throws RenderException {
            try {
                FatuPage page = FatuUtil.getPage(component);
                out.tab(0).append("change : function(field, newValue, oldValue, opts) {").ln();
                //out.tab(1).append("   if(field.isDirty()) {").ln();
                out.tab(2).append("      Ext.Ajax.request({").ln();
                out.tab(3).append("         url: '../action',").ln();
                out.tab(3).format("         params: { PAGE_ID: '%1s', COMPONENT_ID: '%2s', NEW_VALUE : field.getValue()},", page.getId(), component.getId()).ln();
                out.tab(3).append("         success: function(response, opts) {").ln();
                out.tab(4).append("            eval(response.responseText);").ln();
                out.tab(3).append("         }").ln();
                out.tab(2).append("      });// Ajax Request").ln();
                //out.tab(1).append("   }// Is Dirty").ln();
                out.tab(0).append("}// change listener").ln();
            } catch (Exception e) {
                throw new RenderException(e);
            }
            return true;

        }

		@Override
		public void update(FatuCheckbox component, RenderWriter out) throws RenderException {
			
		}

    };
	
	

}
