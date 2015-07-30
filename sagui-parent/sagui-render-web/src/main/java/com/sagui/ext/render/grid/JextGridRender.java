package com.sagui.ext.render.grid;

import org.json.JSONArray;
import org.json.JSONObject;

import com.pw.common.JextContext;
import com.sagui.ext.common.render.ChangesManager;
import com.sagui.ext.common.render.IRender;
import com.sagui.ext.common.render.RenderException;
import com.sagui.ext.common.render.ChangesManager.ComponentChange;
import com.sagui.ext.common.render.generic.FatuAbstractRender;
import com.sagui.ext.common.render.generic.FatuPageIDPropertyRender;
import com.sagui.ext.common.render.util.RenderWriter;
import com.sagui.ext.render.generic.JextSizeFeatureRender;
import com.sagui.ext.render.grid.store.FatuExtModelConfigRender;
import com.sagui.model.datamodel.IFatuColumnModel;
import com.sagui.model.feature.IFatuColumnModelFeature;
import com.sagui.model.grid.FatuGrid;

public class JextGridRender extends FatuAbstractRender<FatuGrid> {

    public JextGridRender() {
        super("Fatuhiva.grid.Panel");
        try {
            super.composite.addRender(new FatuPageIDPropertyRender<FatuGrid>());
            super.composite.addRender(new JextSizeFeatureRender<FatuGrid>());
            super.composite.addRender(new FatuExtColumnModelFeatureRender<FatuGrid>());
            super.composite.addRender(new FatuExtModelConfigRender<FatuGrid>());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean render(FatuGrid component, RenderWriter out) throws RenderException {
        return super.render(component, out);

    }

    @Override
    public void update(FatuGrid component, RenderWriter out) throws RenderException {
        super.update(component, out);
        try {
            ChangesManager changeMgr = JextContext.getValue(ChangesManager.CHANGED_PROPERTIES_KEY);
            ComponentChange cmpChanges = changeMgr.getChanges(component.getId());
            if (cmpChanges != null && cmpChanges.getPropertyChanges("rows") != null) {
                out.format("if(cmp != null) cmp.getStore().reload();").ln();
            }

            if (cmpChanges != null && cmpChanges.getPropertyChanges("selectedRows") != null) {
                out.format("if(cmp != null) cmp.getStore().reload();").ln();
            }

        } catch (Exception e) {
            throw new RenderException(e);
        }
    }

    public static class FatuExtColumnModelFeatureRender<T extends IFatuColumnModelFeature> implements IRender<T> {

        @Override
        public boolean render(T component, RenderWriter out) throws RenderException {
            try {
                IFatuColumnModelFeature cmp = (IFatuColumnModelFeature) component;
                IFatuColumnModel model = cmp.getColumnModel();
                JSONArray columns = new JSONArray();
                JSONObject column = null;
                for (int i = 0; i < model.getColumnCount(); i++) {
                    if(!model.isVisible(i)) continue;
                    column = new JSONObject();
                    column.put("dataIndex", model.getColumnName(i));
                    column.put("text", model.getColumnLabel(i));
                    columns.put(column);
                }
                column.put("flex", 1);
                out.tab().writeConfig("columns", columns.toString()).ln();
                return true;
            } catch (Exception e) {
                throw new RenderException(e);
            }
        }

        @Override
        public void update(T component, RenderWriter out) throws RenderException {

        }

    }

}
