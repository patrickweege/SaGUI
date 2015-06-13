package com.fatuhiva.ext.controller.combobox;

import java.util.Collection;

import org.apache.commons.lang.StringUtils;

import com.fatuhiva.ext.controller.ActionParam;
import com.fatuhiva.ext.controller.IActionResult;
import com.fatuhiva.ext.controller.IJextAction;
import com.fatuhiva.ext.controller.IJextController;
import com.fatuhiva.model.list.IFatuListModel;
import com.fatuhiva.model.list.combo.editable.FatuComboBox;
import com.fatuhiva.model.selection.IFatuSelectionModel;
import com.pw.common.transformer.ToCollectionTransformer;
import com.tuamotu.commons.dataset.IBookmark;
import com.tuamotu.commons.i18n.CurrentLanguageResolver;
import com.tuamotu.commons.i18n.I18n;

@SuppressWarnings("rawtypes")
public class JextComboBoxController implements IJextController<FatuComboBox> {

    private ToCollectionTransformer<String> TO_COLLECTION_TRANSFORMER;

    public JextComboBoxController() {
        this.TO_COLLECTION_TRANSFORMER = new ToCollectionTransformer<String>();
    }

    @Override
    public IActionResult execute(IJextAction<FatuComboBox> action) {
        if (action.getTarget() instanceof FatuComboBox) {
            Collection<String> newSelection = action.getParameter(ActionParam.NEW_VALUE.getKey(), TO_COLLECTION_TRANSFORMER);
            FatuComboBox<?> target = action.getTarget();
            if (target != null) {
                IFatuListModel<?> dataModel = target.getListModel();
                IFatuSelectionModel selectionModel = target.getSelectionModel();
                selectionModel.clearSelection();
                for (String selectedID : newSelection) {
                    for (int row = 0; row < dataModel.getRowCount(); row++) {
                        String key = dataModel.getKey(row);
                        if (StringUtils.equals(key, selectedID)) {
                            IBookmark<?> toSelect = dataModel.getBookmark(row);
                            selectionModel.select(toSelect);
                            break;
                        }
                    }
                    if (selectionModel.getSelectedCount() != newSelection.size()) {
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
