package com.sagui.ext.render.combobox;

import com.sagui.ext.common.render.IComponentRender;
import com.sagui.ext.common.render.RenderException;
import com.sagui.ext.common.render.util.RenderWriter;
import com.sagui.model.container.page.FatuPage;
import com.sagui.model.list.combo.editable.FatuComboBox;
import com.sagui.model.util.FatuUtil;

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
