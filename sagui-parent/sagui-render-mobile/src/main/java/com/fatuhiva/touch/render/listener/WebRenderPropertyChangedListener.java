package com.fatuhiva.touch.render.listener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import com.fatuhiva.ext.common.render.ChangesManager;
import com.fatuhiva.model.FatuComponent;
import com.fatuhiva.model.container.page.FatuPage;
import com.pw.common.JextContext;

public class WebRenderPropertyChangedListener implements PropertyChangeListener {

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ChangesManager pChanges = JextContext.getValue(ChangesManager.CHANGED_PROPERTIES_KEY);
        FatuPage currentPage = JextContext.getValue("CURRENT_PAGE");
        if (pChanges != null && currentPage != null) {
            FatuComponent source = (FatuComponent) evt.getSource();
            pChanges.addPropertyChanged(source.getId(), evt.getPropertyName(), evt.getOldValue(), evt.getNewValue());
        }
    }

}
