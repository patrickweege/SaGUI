package com.fatuhiva.ext.render.combobox;

import com.fatuhiva.ext.common.render.IComponentRender;
import com.fatuhiva.ext.common.render.RenderException;
import com.fatuhiva.ext.common.render.util.RenderWriter;
import com.fatuhiva.model.container.page.FatuPage;
import com.fatuhiva.model.list.combo.editable.FatuComboBox;
import com.fatuhiva.model.util.FatuUtil;

@SuppressWarnings("rawtypes") 
public class JextComboBoxOnSelectListener implements IComponentRender<FatuComboBox> {

    @Override
    public boolean render(FatuComboBox component, RenderWriter out) throws RenderException {
        try {
            FatuPage page = FatuUtil.getPage(component);
            out.format("'select' : new FatuhivaComboBoxSelectionListener('%1s','%2s').execute", page.getId(), component.getId()).ln();
        } catch (Exception e) {
            throw new RenderException(e);
        }
        return true;
    }

    @Override
    public void update(FatuComboBox component, RenderWriter out) throws RenderException {
    }
}
