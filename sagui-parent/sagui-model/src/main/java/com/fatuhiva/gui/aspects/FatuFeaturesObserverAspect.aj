package com.fatuhiva.gui.aspects;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;

import com.fatuhiva.model.FatuComponent;
import com.fatuhiva.model.datamodel.FatuTableModelEvent;
import com.fatuhiva.model.datamodel.IFatuTableModelListener;
import com.fatuhiva.model.list.IFatuListModel;
import com.fatuhiva.model.list.combo.editable.FatuComboBox;
import com.fatuhiva.model.selection.IFatuSelectionListener;
import com.fatuhiva.model.selection.IFatuSelectionModel;
import com.tuamotu.commons.log.FatuLoggerFactory;
import com.tuamotu.commons.util.JextPropertyUtils;

public aspect FatuFeaturesObserverAspect {

    private static final Logger log = FatuLoggerFactory.create(FatuFeaturesObserverAspect.class);

    public pointcut setters(com.fatuhiva.model.FatuComponent component) : target(component) 
        && (execution(public void com.fatuhiva.model.container.toolbar.IFatuSupportToolbar.setToolbar(..))
        || execution(public void com.fatuhiva.model.feature.IFatuTitleFeature.setTitle(..))
        || execution(public void com.fatuhiva.model.feature.IFatuSizeFeature.setSize(..))
        || execution(public void com.fatuhiva.model.feature.IFatuSizeFeature.setMargins(..))
        || execution(public void com.fatuhiva.model.feature.IFatuSizeFeature.setEnabled(..))
        || execution(public void com.fatuhiva.model.feature.IFatuSizeFeature.setVisible(..))
        || execution(public void com.fatuhiva.model.feature.IFatuTitleFeature.setTitle(..))
        || execution(public void com.fatuhiva.model.feature.IFatuLabelableFeature.setLabelWidth(..))
        || execution(public void com.fatuhiva.model.feature.IFatuLabelableFeature.setLabel(..))
        || execution(public void com.fatuhiva.model.feature.IFatuEnabledFeature.setEnabled(..))        
        || execution(public void com.fatuhiva.model.feature.IFatuVisibleFeature.setVisible(..))
        || execution(public void com.fatuhiva.model.feature.IFatuMarginFeature.setMargins(..))        
        || execution(public void com.fatuhiva.model.editable.FatuValueEditable.setValue(..))
        || execution(public void com.fatuhiva.model.label.FatuLabel.setLabel(..))
        || execution(public void com.fatuhiva.model.label.FatuLabel.setHint(..))
        );

    public pointcut internalSetters(com.fatuhiva.model.FatuComponent component) : target(component) 
        && (execution(private void com.fatuhiva.model.editable.FatuValueEditable+.internalSetValue(..)));

    @SuppressWarnings("rawtypes")
    public pointcut addChild(com.fatuhiva.model.FatuContainer container) : target(container) 
        && (execution(protected void com.fatuhiva.model.FatuContainer+.addChild(com.fatuhiva.model.FatuComponent+)));

    @SuppressWarnings("rawtypes")
    public pointcut insertChild(com.fatuhiva.model.FatuContainer container) : target(container) 
        && (execution(protected void com.fatuhiva.model.FatuContainer+.insertChild(int, com.fatuhiva.model.FatuComponent+)));

    @SuppressWarnings("rawtypes")
    public pointcut removeChild(com.fatuhiva.model.FatuContainer container) : target(container) 
        && (execution(public void com.fatuhiva.model.FatuContainer+.removeChild(com.fatuhiva.model.FatuComponent+)));

    after() returning(com.fatuhiva.model.FatuComponent component) : call(com.fatuhiva.model.FatuComponent+.new(..)) {
        if (log.isTraceEnabled()) log.trace("new {}(...)", component.getClass());
        component.addPropertyChangeListener(new com.fatuhiva.gui.aspects.FatuPropertyChangeListener());
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    after(com.fatuhiva.model.list.combo.editable.FatuComboBox combo) returning: this(combo) && initialization(com.fatuhiva.model.list.editable.impl.FatuAbstractListComponent.new(IFatuListModel+, IFatuSelectionModel+)) {
        if (log.isTraceEnabled()) log.trace("new {}(...)", combo.getClass().getName());
        Object[] args = thisJoinPoint.getArgs();
        final FatuComboBox theCombo = (FatuComboBox) combo;
        IFatuListModel model = (IFatuListModel) args[0];

        IFatuTableModelListener l = new IFatuTableModelListener() {

            @Override
            public void tableChanged(FatuTableModelEvent evt) {
                theCombo.getPropertyChangeSupport().firePropertyChange("items", null, null);
            }
        };
        model.addTableModelListener(l);

        IFatuSelectionModel selectionModel = (IFatuSelectionModel) args[1];
        IFatuSelectionListener sListener = new IFatuSelectionListener() {

            @Override
            public void selectionChanged(Collection newItems, Collection oldItems) {
                theCombo.getPropertyChangeSupport().firePropertyChange("selection", oldItems, newItems);
            }

        };
        selectionModel.addSelectionListener(sListener);

    }

    void around(com.fatuhiva.model.FatuComponent component) : internalSetters(component) {
        String setter = thisJoinPoint.getSignature().getName();
        if (log.isTraceEnabled()) log.trace("{}.{}(...)", component.getClass().getName(), setter);
        try {
            System.out.print(setter);
            String propertyName = StringUtils.substringAfter(setter, "internal");
            System.out.print(" - " + propertyName);
            propertyName = JextPropertyUtils.getPropertyNameFromMethod(propertyName);
            System.out.println(" - " + propertyName);
            Object oldValue = PropertyUtils.getProperty(component, propertyName);
            proceed(component);
            Object newValue = PropertyUtils.getProperty(component, propertyName);
            component.getPropertyChangeSupport().firePropertyChange(propertyName, oldValue, newValue);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    void around(com.fatuhiva.model.FatuComponent component) : setters(component) {
        String setter = thisJoinPoint.getSignature().getName();
        if (log.isTraceEnabled()) log.trace("{}.{}(...)", component.getClass().getName(), setter);
        try {
            String propertyName = JextPropertyUtils.getPropertyNameFromMethod(setter);
            if (PropertyUtils.isReadable(component, propertyName)) {
                Object oldValue = PropertyUtils.getProperty(component, propertyName);
                proceed(component);
                Object newValue = PropertyUtils.getProperty(component, propertyName);
                component.getPropertyChangeSupport().firePropertyChange(propertyName, oldValue, newValue);
            } else {
                if (log.isTraceEnabled()) log.trace("No Getter for {}.{}(...). The Property will not be logger", component.getClass().getName(), propertyName);
                proceed(component);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    void around(com.fatuhiva.model.FatuContainer container) : addChild(container)  {
        if (log.isTraceEnabled()) log.trace("{}.addChild(...)", container.getClass().getName());
        try {
            List<FatuComponent> oldChildren = new ArrayList<FatuComponent>(container.getChildren());
            proceed(container);
            List<FatuComponent> newChildren = new ArrayList<FatuComponent>(container.getChildren());
            container.getPropertyChangeSupport().firePropertyChange("children", oldChildren, newChildren);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    void around(com.fatuhiva.model.FatuContainer container) : insertChild(container)  {
        if (log.isTraceEnabled()) log.trace("{}.insertChild(...)", container.getClass().getName());
        try {
            List<FatuComponent> oldChildren = new ArrayList<FatuComponent>(container.getChildren());
            proceed(container);
            List<FatuComponent> newChildren = new ArrayList<FatuComponent>(container.getChildren());
            container.getPropertyChangeSupport().firePropertyChange("children", oldChildren, newChildren);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    void around(com.fatuhiva.model.FatuContainer container) : removeChild(container) {
        if (log.isTraceEnabled()) log.trace("{}.removeChild(...)", container.getClass().getName());
        try {
            List<FatuComponent> oldChildren = new ArrayList<FatuComponent>(container.getChildren());
            proceed(container);
            List<FatuComponent> newChildren = new ArrayList<FatuComponent>(container.getChildren());
            container.getPropertyChangeSupport().firePropertyChange("children", oldChildren, newChildren);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
