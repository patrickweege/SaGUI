package com.sagui.ext.render.listener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import com.pw.common.JextContext;
import com.sagui.ext.common.render.ChangesManager;
import com.sagui.model.FatuComponent;
import com.sagui.model.container.page.FatuPage;

public class WebRenderPropertyChangedListener implements PropertyChangeListener {

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ChangesManager pChanges = JextContext.getValue(ChangesManager.CHANGED_PROPERTIES_KEY);
        FatuPage<?> currentPage = JextContext.getValue("CURRENT_PAGE");
        if (pChanges != null && currentPage != null) {
            FatuComponent source = (FatuComponent) evt.getSource();
            pChanges.addPropertyChanged(source.getId(), evt.getPropertyName(), evt.getOldValue(), evt.getNewValue());
        }
    }

}
