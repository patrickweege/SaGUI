package com.fatuhiva.touch.render.grid.store;

import org.json.JSONArray;

import com.fatuhiva.ext.common.render.ChangesManager;
import com.fatuhiva.ext.common.render.ChangesManager.ComponentChange;
import com.fatuhiva.ext.common.render.IComponentRender;
import com.fatuhiva.ext.common.render.RenderException;
import com.fatuhiva.ext.common.render.util.RenderWriter;
import com.fatuhiva.model.container.page.FatuPage;
import com.fatuhiva.model.datamodel.IFatuTableModel;
import com.fatuhiva.model.grid.FatuGrid;
import com.fatuhiva.model.util.FatuUtil;
import com.pw.common.JextContext;

public class JextStorePropertyRender implements IComponentRender<FatuGrid> {

    private final String storeProperty;

    public JextStorePropertyRender(String storeProperty) {
        this.storeProperty = storeProperty;
    }

    @Override
    public boolean render(FatuGrid component, RenderWriter out) throws RenderException {
        try {
            out.tab().writeConfig(storeProperty, " Ext.create('Ext.data.Store', {").ln();
            out.ident();
            out.writeConfigAsString("storeId", component + "_store").commaLn();
            out.writeConfig("buffered", true).commaLn();
            out.writeConfig("leadingBufferZone", 80).commaLn();
            out.writeConfig("pageSize", 33).commaLn();
            out.writeConfig("autoLoad", true).commaLn();

            renderFields(component, out).commaLn();
            renderProxy(component, out).pushComma();

            out.udent();
            out.popComma();
            out.append("})").ln();
        } catch (Exception e) {
            throw new RenderException(e);
        }
        return true;

    }

    @Override
    public void update(FatuGrid component, RenderWriter out) throws RenderException {
        try {
            ChangesManager changeMgr = JextContext.getValue(ChangesManager.CHANGED_PROPERTIES_KEY);
            ComponentChange cmpChanges = changeMgr.getChanges(component.getId());
            if (cmpChanges != null && cmpChanges.getPropertyChanges("rows") != null) {
                out.format("if(cmp != null) cmp.getStore().load();").ln();
                //out.format("if(cmp != null) cmp.getView().refresh();").ln();
            }
        } catch (Exception e) {
            throw new RenderException(e);
        }
    }

    private RenderWriter renderProxy(FatuGrid component, RenderWriter out) throws RenderException {
        try {
            FatuPage<?> page = FatuUtil.getPage(component);
            out.writeConfig("proxy", "new Ext.data.proxy.Ajax({").ln();
            out.ident();
            out.writeConfigAsString("type", "ajax").commaLn();
            out.writeConfigAsString("url", "../action").commaLn();
            out.format("extraParams : { PAGE_ID: '%1s', COMPONENT_ID: '%2s', EVENT : 'getData'}", page.getId(), component.getId()).commaLn();

            renderReader(component, out);

            out.udent();

            out.append("})").ln();
        } catch (Exception e) {
            throw new RenderException(e);
        }
        return out;
    }

    private RenderWriter renderReader(FatuGrid component, RenderWriter out) throws RenderException {
        try {
            out.tab().writeConfig("reader", "{").ln();
            out.ident();
            out.writeConfigAsString("root", "items").commaLn();
            out.writeConfigAsString("totalProperty", "totalCount").commaLn();
            out.udent().popComma();
            out.append("}").ln();
        } catch (Exception e) {
            throw new RenderException(e);
        }
        return out;
    }

    private RenderWriter renderFields(FatuGrid component, RenderWriter out) throws RenderException {
        try {
            IFatuTableModel<?> tableModel = component.getTableModel();
            JSONArray fields = new JSONArray();
            for (int col = 0; col <  tableModel.getColumnCount(); col++) {
                fields.put(tableModel.getColumnName(col));
            }
            out.writeConfig("fields", fields);
        } catch (Exception e) {
            throw new RenderException(e);
        }
        return out;
    }
}
