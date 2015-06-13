package com.fatuhiva.touch.render.combobox;

import java.util.Collection;

import com.fatuhiva.ext.common.render.IComponentRender;
import com.fatuhiva.ext.common.render.RenderException;
import com.fatuhiva.ext.common.render.util.RenderWriter;
import com.fatuhiva.model.FatuElement;
import com.fatuhiva.model.list.combo.editable.FatuComboBox;
import com.fatuhiva.touch.render.generic.JextAbstractRender;
import com.fatuhiva.touch.render.generic.JextErrorMessageRender;
import com.fatuhiva.touch.render.generic.JextLabelableFeatureRender;
import com.fatuhiva.touch.render.generic.JextListenersRender;

@SuppressWarnings("rawtypes")
public class JextComboRender extends JextAbstractRender<FatuComboBox<? extends FatuElement>> {

    public JextComboRender() {
        super("Ext.form.ComboBox");
        super.composite.addRender(new JextLabelableFeatureRender<FatuComboBox<?>>());
        super.composite.addRender(new JextErrorMessageRender<FatuComboBox<?>>());
        super.composite.addRender(new JextComboSelectionRender());
        super.composite.addRender(new JextComboItemsRender());

        JextListenersRender<FatuComboBox<? extends FatuElement>> listenerRender = new JextListenersRender<FatuComboBox<? extends FatuElement>>();
        listenerRender.addRender(new JextComboBoxOnSelectListener());

        super.composite.addRender(listenerRender);

    }

    @Override
    public boolean render(FatuComboBox<? extends FatuElement> component, RenderWriter out) throws RenderException {
        return super.render(component, out);
    }

    @SuppressWarnings({ "unchecked" })
    public class JextComboSelectionRender implements IComponentRender<FatuComboBox<? extends FatuElement>> {

        @Override
        public boolean render(FatuComboBox component, RenderWriter out) throws RenderException {
            try {
                Collection<? extends FatuElement> selection = component.getSelection();
                if (selection.size() == 1) {
                    FatuElement next = selection.iterator().next();
                    out.tab().writeConfigAsString("value", next.getId());
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
