package com.sagui.ext.render.grid.store;

import com.pw.common.JextContext;
import com.sagui.ext.common.render.ChangesManager;
import com.sagui.ext.common.render.IRender;
import com.sagui.ext.common.render.RenderException;
import com.sagui.ext.common.render.ChangesManager.ComponentChange;
import com.sagui.ext.common.render.generic.FatuExtCreationMode;
import com.sagui.ext.common.render.generic.FatuObjectPropertyRender;
import com.sagui.ext.common.render.generic.FatuUnmodifiablePropertyRender;
import com.sagui.ext.common.render.util.RenderWriter;
import com.sagui.ext.render.generic.JextListenersRender;
import com.sagui.model.FatuComponent;
import com.sagui.model.container.page.FatuPage;
import com.sagui.model.feature.IFatuSelectionFeature;
import com.sagui.model.util.FatuUtil;

@SuppressWarnings("rawtypes")
public class FatuExtStorePropertyRender<T extends FatuComponent> extends FatuObjectPropertyRender<T> {

    @SuppressWarnings("unchecked")
    public FatuExtStorePropertyRender() {
        //super("store", "Ext.data.Store", ExtCreationMode.EXT_CREATE);
        super("store", "Fatuhiva.data.Store", FatuExtCreationMode.EXT_CREATE);

        super.composite.addRender(new FatuUnmodifiablePropertyRender<T>("buffered", Boolean.TRUE));
        //super.composite.addRender(new JextUnmodifiablePropertyRender<T>("leadingBufferZone", 80));
        super.composite.addRender(new FatuUnmodifiablePropertyRender<T>("pageSize", 33));
        super.composite.addRender(new FatuUnmodifiablePropertyRender<T>("autoLoad", Boolean.TRUE));
        
        super.composite.addRender(new FatuExtStoreIDRender<T>());
        super.composite.addRender(new FatuExtStoreModelNameRender<T>());
        super.composite.addRender(new FatuExtModelConfigRender());
        
        super.composite.addRender(new FatuExtAjaxProxyRender());
        
        JextListenersRender<T> lRender = new JextListenersRender<T>();
        lRender.addRender(new OnLoadStoreListenerRender());
        
        super.composite.addRender(lRender); 

    }

    @Override
    public void update(T component, RenderWriter out) throws RenderException {
        try {
            ChangesManager changeMgr = JextContext.getValue(ChangesManager.CHANGED_PROPERTIES_KEY);
            ComponentChange cmpChanges = changeMgr.getChanges(component.getId());
            if (cmpChanges != null && cmpChanges.getPropertyChanges("rows") != null) {
                out.format("if(cmp != null) cmp.getStore().fatuReload();").ln();
            }
            
            if (cmpChanges != null && cmpChanges.getPropertyChanges("selectedRows") != null) {
                out.format("if(cmp != null) cmp.getStore().fatuReload();").ln();
            }

        } catch (Exception e) {
            throw new RenderException(e);
        }
    }

    
    private class OnLoadStoreListenerRender implements IRender<T> {

        @Override
        public boolean render(T component, RenderWriter out) throws RenderException {
            boolean redered = false;
            try {
                if(component instanceof IFatuSelectionFeature) {
                    FatuPage page = FatuUtil.getPage(component);
                    out.format("load : new FatuhivaGridStoreOnLoadListener('%1$s','%2$s').execute",page.getId(),component.getId()).ln();
                    redered = true;
                }
            } catch (Exception e) {
                throw new RenderException(e);
            }
            return redered;
        }

        @Override
        public void update(T component, RenderWriter out) throws RenderException {

        }
    };
}
