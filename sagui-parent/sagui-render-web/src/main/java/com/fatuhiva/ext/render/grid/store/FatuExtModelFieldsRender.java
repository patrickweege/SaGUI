package com.fatuhiva.ext.render.grid.store;

import org.json.JSONArray;

import com.fatuhiva.ext.common.render.IRender;
import com.fatuhiva.ext.common.render.RenderException;
import com.fatuhiva.ext.common.render.util.RenderWriter;
import com.fatuhiva.model.datamodel.IFatuColumnModel;
import com.fatuhiva.model.feature.IFatuColumnModelFeature;
import com.fatuhiva.model.feature.IFatuTableModelFeature;

public class FatuExtModelFieldsRender<T extends IFatuTableModelFeature & IFatuColumnModelFeature> implements IRender<T> {

    @Override
    public boolean render(T component, RenderWriter out) throws RenderException {
        try {
            IFatuColumnModel colModel = component.getColumnModel();
            JSONArray fields = new JSONArray();
            for (int col = 0; col < colModel.getColumnCount(); col++) {
                //IField<T> field = tableModel.getColumn(col);
                //fields.put(field.getName());
                String colName = colModel.getColumnName(col);
                fields.put(colName);
            }
            fields.put("__fatuhiva_rowMetadata");
            out.writeConfig("fields", fields).ln();
            return true;
        } catch (Exception e) {
            throw new RenderException(e);
        }
    }

    @Override
    public void update(T component, RenderWriter out) throws RenderException {
        // TODO Auto-generated method stub
    }
}
