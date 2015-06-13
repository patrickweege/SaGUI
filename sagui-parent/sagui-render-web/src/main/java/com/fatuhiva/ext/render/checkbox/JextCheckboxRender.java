package com.fatuhiva.ext.render.checkbox;

import com.fatuhiva.ext.common.render.IComponentRender;
import com.fatuhiva.ext.common.render.RenderException;
import com.fatuhiva.ext.common.render.generic.FatuAbstractRender;
import com.fatuhiva.ext.common.render.generic.FatuGenericPropertyRender;
import com.fatuhiva.ext.common.render.request.FatuhivaActionRequest;
import com.fatuhiva.ext.common.render.request.FatuhivaRequest;
import com.fatuhiva.ext.common.render.request.FatuhivaRequestRender;
import com.fatuhiva.ext.common.render.util.RenderWriter;
import com.fatuhiva.ext.render.generic.JextListenersRender;
import com.fatuhiva.model.container.page.FatuPage;
import com.fatuhiva.model.editable.list.checkbox.FatuCheckbox;
import com.fatuhiva.model.util.FatuUtil;

public class JextCheckboxRender extends FatuAbstractRender<FatuCheckbox> {

    public JextCheckboxRender() {
        super("Ext.form.field.Checkbox");
        super.composite.addRender(new FatuGenericPropertyRender<FatuCheckbox>("label", "boxLabel", null, String.class));
        super.composite.addRender(new FatuGenericPropertyRender<FatuCheckbox>("value", "checked", "setValue", Boolean.class));
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
                FatuPage<?> page = FatuUtil.getPage(component);
                out.append("change : function(field, newValue, oldValue, opts) {").ln();

                FatuhivaRequest request = new FatuhivaActionRequest(page.getId(), component.getId(), "change");
                request.addParamAsScript("NEW_VALUE", "field.getValue()");
                FatuhivaRequestRender render = new FatuhivaRequestRender();
                render.render(request, out);

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
