package com.fatuhiva.touch.render.grid;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fatuhiva.ext.common.render.IComponentRender;
import com.fatuhiva.ext.common.render.RenderException;
import com.fatuhiva.ext.common.render.generic.FatuUnmodifiablePropertyRender;
import com.fatuhiva.ext.common.render.util.RenderWriter;
import com.fatuhiva.model.FatuComponent;
import com.fatuhiva.model.datamodel.IFatuColumnModel;
import com.fatuhiva.model.feature.IFatuColumnModelFeature;
import com.fatuhiva.model.grid.FatuGrid;
import com.fatuhiva.touch.render.generic.JextAbstractRender;
import com.fatuhiva.touch.render.generic.JextSizeFeatureRender;
import com.fatuhiva.touch.render.grid.store.JextStorePropertyRender;


public class JextGridRender extends JextAbstractRender<FatuGrid> {

    public JextGridRender() {
        super("Ext.grid.GridPanel");
        try {
            super.composite.addRender(new JextStorePropertyRender("store"));
            super.composite.addRender(new JextSizeFeatureRender<FatuGrid>());
            super.composite.addRender(new JextSupportColumnModelRender<FatuGrid>());

            JSONObject jsonObj = new JSONObject().put("variableRowHeight", false);
            super.composite.addRender(new FatuUnmodifiablePropertyRender<FatuGrid>("verticalScroller", jsonObj));
            
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean render(FatuGrid component, RenderWriter out) throws RenderException {
        return super.render(component, out);

    }

    public static class JextSupportColumnModelRender<T extends FatuComponent> implements IComponentRender<T> {

        @Override
        public boolean render(T component, RenderWriter out) throws RenderException {
            try {
                IFatuColumnModelFeature cmp = (IFatuColumnModelFeature) component;
                IFatuColumnModel model = cmp.getColumnModel();
                JSONArray columns = new JSONArray();
                JSONObject column = null;
                for (int i = 0; i < model.getColumnCount(); i++) {
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
