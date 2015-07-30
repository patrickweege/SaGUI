package com.sagui.ext.render.grid.store;

import org.json.JSONObject;

import com.sagui.ext.common.render.RenderException;
import com.sagui.ext.common.render.generic.FatuExtCreationMode;
import com.sagui.ext.common.render.generic.FatuObjectPropertyRender;
import com.sagui.ext.common.render.generic.FatuUnmodifiablePropertyRender;
import com.sagui.ext.common.render.generic.VatuValueRender;
import com.sagui.ext.common.render.util.RenderWriter;
import com.sagui.model.FatuComponent;
import com.sagui.model.container.page.FatuPage;
import com.sagui.model.util.FatuUtil;

public class FatuExtAjaxProxyRender<T extends FatuComponent> extends FatuObjectPropertyRender<T> {

    public FatuExtAjaxProxyRender() {
        super("proxy", "Ext.data.proxy.Ajax", FatuExtCreationMode.NEW);
        super.composite.addRender(new FatuUnmodifiablePropertyRender<T>("type", "'ajax'"));
        super.composite.addRender(new FatuUnmodifiablePropertyRender<T>("url", "'../action'"));
        super.composite.addRender(new InternalExtraParamRender());
        super.composite.addRender(new FatuUnmodifiablePropertyRender<T>("reader", "{type: 'json', root : 'items', totalProperty : 'totalCount'}"));
    }

    @Override
    public void update(T component, RenderWriter out) throws RenderException {
        // NOTHING to do here at time
    }

    private class InternalExtraParamRender extends VatuValueRender<T> {

        public InternalExtraParamRender() {
            super("extraParams");
        }

        @Override
        protected Object getValueToRender(T component) {
            try {
                FatuPage<?> page = FatuUtil.getPage(component);

                JSONObject extraParams = new JSONObject();
                extraParams.put("PAGE_ID", page.getId());
                extraParams.put("COMPONENT_ID", component.getId());
                extraParams.put("EVENT", "getData");

                return extraParams.toString();

            } catch (Exception e) {
                throw new RenderException(e);
            }

        }

    }
}
