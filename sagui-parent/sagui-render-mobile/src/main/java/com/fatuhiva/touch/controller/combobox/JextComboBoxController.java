package com.fatuhiva.touch.controller.combobox;

import org.apache.commons.lang.StringUtils;

import com.fatuhiva.model.FatuElement;
import com.fatuhiva.model.list.IFatuListModel;
import com.fatuhiva.model.list.combo.editable.FatuComboBox;
import com.fatuhiva.model.selection.IFatuSelectionModel;
import com.fatuhiva.touch.controller.IActionResult;
import com.fatuhiva.touch.controller.IJextAction;
import com.fatuhiva.touch.controller.IJextController;
import com.tuamotu.commons.i18n.CurrentLanguageResolver;
import com.tuamotu.commons.i18n.I18n;

@SuppressWarnings("rawtypes")
public class JextComboBoxController implements IJextController<FatuComboBox> {

    @Override
    @SuppressWarnings({ "unchecked" })
    public IActionResult execute(IJextAction<FatuComboBox> action) {
        if (action.getTarget() instanceof FatuComboBox) {
            String newValue = action.getParameter("NEW_VALUE");
            FatuComboBox target = action.getTarget();
            if (target != null) {
                IFatuListModel<FatuElement> model = target.getListModel();
                String[] selectedIds = newValue.split(";");
                IFatuSelectionModel selectionModel = target.getSelectionModel();
                selectionModel.clearSelection();
                for (String selecteID : selectedIds) {
                    for (int row = 0; row < model.getRowCount(); row++) {
                        String key = model.getKey(row);
                        if (StringUtils.equals(key, selecteID)) {
                            target.getSelectionModel().select(key);
                            break;
                        }
                    }
                    if (selectionModel.getSelectedCount() != selectedIds.length) {
                        I18n errorMsg = new I18n(target.getId());
                        errorMsg.setTranslation(CurrentLanguageResolver.getInstance().getCurrentLocale(), "Pelo menos um item selecionado não foi encontrado");
                        target.setErrorMsg(errorMsg);
                    } else {
                        target.setErrorMsg(null);
                    }
                }
                return null;
            }
        }
        throw new IllegalStateException("Cannot execute Action");

    }

}
