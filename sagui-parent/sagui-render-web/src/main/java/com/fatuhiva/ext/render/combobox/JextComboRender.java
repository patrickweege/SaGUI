package com.fatuhiva.ext.render.combobox;

import java.util.Collection;

import com.fatuhiva.ext.common.render.IComponentRender;
import com.fatuhiva.ext.common.render.RenderException;
import com.fatuhiva.ext.common.render.generic.FatuAbstractRender;
import com.fatuhiva.ext.common.render.util.RenderWriter;
import com.fatuhiva.ext.render.generic.JextErrorMessageRender;
import com.fatuhiva.ext.render.generic.JextLabelableFeatureRender;
import com.fatuhiva.ext.render.generic.JextListenersRender;
import com.fatuhiva.model.list.IFatuListModel;
import com.fatuhiva.model.list.combo.editable.FatuComboBox;
import com.sagui.dataset.commons.dataset.IBookmark;

@SuppressWarnings("rawtypes")
public class JextComboRender extends FatuAbstractRender<FatuComboBox> {

    public JextComboRender() {
        super("Ext.form.ComboBox");
        super.composite.addRender(new JextLabelableFeatureRender<FatuComboBox>());
        super.composite.addRender(new JextErrorMessageRender<FatuComboBox>());
        super.composite.addRender(new JextComboSelectionRender());
        super.composite.addRender(new JextComboItemsRender());

        JextListenersRender<FatuComboBox> listenerRender = new JextListenersRender<FatuComboBox>();
        listenerRender.addRender(new JextComboBoxOnSelectListener());

        super.composite.addRender(listenerRender);

    }

    @Override
    public boolean render(FatuComboBox component, RenderWriter out) throws RenderException {
        return super.render(component, out);
    }

    @SuppressWarnings({ "unchecked" })
    public class JextComboSelectionRender implements IComponentRender<FatuComboBox> {

        @Override
        public boolean render(FatuComboBox component, RenderWriter out) throws RenderException {
            try {
                Collection<IBookmark> selection = component.getSelection();
                if (selection.size() == 1) {
                    IBookmark next = selection.iterator().next();
                    IFatuListModel model = component.getListModel();
                    int index = model.getRowIndex(next);
                    String ID = model.getKey(index);
                    out.tab().writeConfigAsString("value", ID);
                    return true;
                }
            } catch (Exception e) {
                throw new RenderException(e);
            }
            return false;
        }

        @Override
        public void update(FatuComboBox component, RenderWriter out) throws RenderException {
            // TODO Auto-generated method stub
        }

    }

}
