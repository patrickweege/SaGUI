package com.sagui.ext.render.grid.result;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;

import org.json.JSONObject;
import org.slf4j.Logger;

import com.sagui.dataset.commons.log.FatuLoggerFactory;
import com.sagui.ext.common.render.IRender;
import com.sagui.ext.common.render.RenderException;
import com.sagui.ext.common.render.util.ColorHexConverter;
import com.sagui.ext.common.render.util.RenderWriter;
import com.sagui.ext.controller.grid.GridGetdataActionResult;

public class GetdataResultRender implements IRender<GridGetdataActionResult> {

    private static final Logger log = FatuLoggerFactory.create();
    
    private static final ColorHexConverter COLOR_CONVERTER = ColorHexConverter.getInstance();

    @Override
    public boolean render(GridGetdataActionResult component, RenderWriter out) throws RenderException {
        try {
            JSONObject json = new JSONObject();
            json.put("totalCount", component.getTotalCount());
            Collection<JSONObject> items = new ArrayList<JSONObject>();
            for (int row = 0; row < component.getPageCount(); row++) {
                JSONObject item = new JSONObject();
                JSONObject bgColors = new JSONObject();
                JSONObject colors = new JSONObject();
                JSONObject rowMetadata = new JSONObject();
                for (int col = 0; col < component.getColumnCount(); col++) {
                    if (!component.isVisible(col)) continue;
                    String colName = component.getColumnName(col);
                    item.put(colName, component.getData(row, col));
                    Color bgColor = component.getBackgroundColor(row, col);
                    String hex = null;
                    if (bgColor != null) {
                        hex = (String) COLOR_CONVERTER.convert(String.class, bgColor);
                    }
                    bgColors.put(colName, hex);

                    Color color = component.getColor(row, col);
                    hex = null;
                    if (color != null) {
                        hex = (String) COLOR_CONVERTER.convert(String.class, color);
                    }
                    colors.put(colName, hex);
                }
                rowMetadata.put("__fatuhiva_isSelected", component.isSelected(row));
                rowMetadata.put("__fatuhiva_rowUUID", component.getRowUUID(row));
                rowMetadata.put("__fatuhiva_colors", colors);
                rowMetadata.put("__fatuhiva_bgcolors", bgColors);
                item.put("__fatuhiva_rowMetadata", rowMetadata);

                /*
                 * Template of one Record / Row
                 * {
                 *      col-1-name: "Column 1 Value",
                 *      col-2-name: "Column 2 Value",
                 *      __fatuhiva_rowMetadata : {
                 *          __fatuhiva_isSelected : true or false,
                 *          __fatuhiva_rowUUID : "row-uuid",
                 *          __fatuhiva_bgcolors : {
                 *              colName1 : '#gdgdg'
                 *              colName2 : '#gdgdg'
                 *          },
                 *          __fatuhiva_colors : {
                 *              colName1 : '#gdgdg'
                 *              colName2 : '#gdgdg'
                 *          }
                 *      }
                 * }
                 */

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
    public void update(GridGetdataActionResult component, RenderWriter out) throws RenderException {
        // TODO Auto-generated method stub
    }
}
