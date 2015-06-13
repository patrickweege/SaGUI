package com.fatuhiva.touch.render.combobox;

import com.fatuhiva.ext.common.render.IComponentRender;
import com.fatuhiva.ext.common.render.RenderException;
import com.fatuhiva.ext.common.render.util.RenderWriter;
import com.fatuhiva.model.FatuElement;
import com.fatuhiva.model.list.combo.editable.FatuComboBox;

@SuppressWarnings("rawtypes")
public class JextComboItemsRender implements IComponentRender<FatuComboBox<? extends FatuElement>> {

    private static final String LABEL_FIELD = "label";
    private static final String VALUE_FIELD = "id";

    @Override
    public boolean render(FatuComboBox component, RenderWriter out) throws RenderException {
        try {
            return true;
        } catch (Exception e) {
            throw new RenderException(e);
        }
    }

    @Override
    public void update(FatuComboBox component, RenderWriter out) throws RenderException {
        try {
        } catch (Exception e) {
            throw new RenderException(e);
        }
    }

}
