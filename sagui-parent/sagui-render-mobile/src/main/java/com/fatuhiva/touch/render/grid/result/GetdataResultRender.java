package com.fatuhiva.touch.render.grid.result;

import java.util.ArrayList;
import java.util.Collection;

import org.json.JSONObject;
import org.slf4j.Logger;

import com.fatuhiva.ext.common.render.IRender;
import com.fatuhiva.ext.common.render.RenderException;
import com.fatuhiva.ext.common.render.util.RenderWriter;
import com.fatuhiva.touch.controller.grid.GetdataResult;
import com.tuamotu.commons.log.FatuLoggerFactory;

public class GetdataResultRender implements IRender<GetdataResult> {
    private static final Logger log = FatuLoggerFactory.create();

    @Override
    public boolean render(GetdataResult component, RenderWriter out) throws RenderException {
        try {
            if (log.isTraceEnabled()) log.debug("Render GridGetdataActionResult - {}", component.toString());
            JSONObject json = new JSONObject();
            json.put("totalCount", component.getTotalCount());
            Collection<JSONObject> items = new ArrayList<JSONObject>();
            for (int row = 0; row < component.getPageCount(); row++) {
                JSONObject item = new JSONObject();
                for (int col = 0; col < component.getColumnCount(); col++) {
                    item.put(component.getColumnName(col), component.getData(row, col));
                }
                items.add(item);
            }
            json.put("items", items);
            out.append(json.toString());
            return true;
        } catch (Exception e) {
            throw new RenderException(e);
        }
    }

    @Override
    public void update(GetdataResult component, RenderWriter out) throws RenderException {
        // TODO Auto-generated method stub
    }

}
