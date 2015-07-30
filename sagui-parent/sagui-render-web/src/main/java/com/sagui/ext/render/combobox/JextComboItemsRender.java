package com.sagui.ext.render.combobox;

import com.pw.common.JextContext;
import com.sagui.ext.common.render.ChangesManager;
import com.sagui.ext.common.render.IComponentRender;
import com.sagui.ext.common.render.RenderException;
import com.sagui.ext.common.render.ChangesManager.ComponentChange;
import com.sagui.ext.common.render.util.RenderWriter;
import com.sagui.model.list.IFatuListModel;
import com.sagui.model.list.combo.editable.FatuComboBox;

@SuppressWarnings("rawtypes")
public class JextComboItemsRender implements IComponentRender<FatuComboBox> {

    private static final String LABEL_FIELD = "label";
    private static final String VALUE_FIELD = "id";

    @Override
    public boolean render(FatuComboBox component, RenderWriter out) throws RenderException {
        try {
            IFatuListModel listModel = component.getListModel();
            if (listModel.getRowCount() > 0) {
                out.tab().writeConfigAsString("valueField", "id").ln().pushComma();
                out.tab().writeConfigAsString("displayField", "label").ln().pushComma();
                out.tab().append("store :  Ext.create('Ext.data.Store', {").ln();
                out.ident();
                out.tab().append("fields : ['id','label'],").ln();
                out.tab().append("data : [").ln();
                out.ident();
                for (int row = 0; row < listModel.getRowCount(); row++) {
                    out.tab().append("{");
                    out.writeConfigAsString("'id'", listModel.getKey(row)).pushComma();
                    out.writeConfigAsString("'label'", listModel.getLabel(row));
                    out.append("}").ln();
                    if (row < listModel.getRowCount() - 1) {
                        out.pushComma();
                    }
                }
                out.udent();
                out.tab().append("]").ln();
                out.udent();
                out.tab().append("})").ln();
            }
            return true;
        } catch (Exception e) {
            throw new RenderException(e);
        }
    }

    @Override
    public void update(FatuComboBox component, RenderWriter out) throws RenderException {
        try {
            ChangesManager changeMgr = JextContext.getValue(ChangesManager.CHANGED_PROPERTIES_KEY);
            ComponentChange cmpChanges = changeMgr.getChanges(component.getId());
            if (cmpChanges != null && cmpChanges.getPropertyChanges("items") != null) {
                out.append("if(cmp != null) cmp.getStore().removeAll();").ln();
                IFatuListModel listModel = component.getListModel();
                for (int row = 0; row < listModel.getRowCount(); row++) {
                    String id = listModel.getKey(row);
                    String label = listModel.getLabel(row);
                    out.format("if(cmp != null) cmp.getStore().add({%1$s: '%2$s', %3$s: '%4$s'});", VALUE_FIELD, id, LABEL_FIELD, label).ln();
                }
            }

        } catch (Exception e) {
            throw new RenderException(e);
        }
    }

}
