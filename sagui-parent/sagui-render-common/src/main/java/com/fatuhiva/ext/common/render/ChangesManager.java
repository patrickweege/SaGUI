package com.fatuhiva.ext.common.render;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.Predicate;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

public class ChangesManager {

    public static String CHILD_REMOVED_CHANGE = "ELEMENT_REMOVED";
    public static String CHILD_ADDED_CHANGE = "ELEMENT_ADDED";

    public static final String CHANGED_PROPERTIES_KEY = ChangesManager.class.getName();

    private final Map<String, ComponentChange> changes;

    public ChangesManager() {
        this.changes = new LinkedHashMap<String, ComponentChange>();
    }

    public <PVALUE> void addPropertyChanged(String componentID, String property, PVALUE oldValue, PVALUE newValue) {
        ComponentChange componentChange = changes.get(componentID);
        if (componentChange == null) {
            componentChange = new ComponentChange(componentID);
            changes.put(componentID, componentChange);
        }
        componentChange.addChange(property, oldValue, newValue);
    }

    public ComponentChange getChanges(String componentID) {
        return this.changes.get(componentID);
    }

    public Collection<ComponentChange> getChanges() {
        return new ArrayList<ComponentChange>(this.changes.values());
    }

    public boolean hasChanges(String componentID, String property) {
        ComponentChange componentChange = changes.get(componentID);
        return componentChange == null ? false : (componentChange.getPropertyChanges(property) != null);
    }

    public void removeChanges(String componentID) {
        this.changes.remove(componentID);
    }

    public Collection<String> getChangedComponents() {
        Set<String> changedComponents = new HashSet<String>();
        for (String id : changes.keySet()) {
            changedComponents.add(id);
        }
        return changedComponents;
    }

    @SuppressWarnings({ "rawtypes", "unused" })
    private class FindPropertyChangesPredicate implements Predicate {

        private String componentID;

        private FindPropertyChangesPredicate(String componentID) {
            this.componentID = componentID;
        }

        @Override
        public boolean evaluate(Object obj) {
            boolean matches = true;
            PropertyChange pChanges = (PropertyChange) obj;
            if (!StringUtils.isEmpty(componentID)) matches = matches && StringUtils.equals(pChanges.getComponentID(), componentID);
            //if(!StringUtils.isEmpty(propertyName)) matches = matches && StringUtils.equals(pChanges.getProperty(), propertyName);
            return matches;
        }

    }

    @SuppressWarnings("rawtypes")
    public static class ComponentChange {

        private String componentID;
        private Map<String, PropertyChange<?>> propertyChanges;

        public ComponentChange(String componentID) {
            this.componentID = componentID;
            this.propertyChanges = new HashMap<String, ChangesManager.PropertyChange<?>>();
        }

        @SuppressWarnings("unchecked")
        public <PVALUE> void addChange(String property, PVALUE oldValue, PVALUE newValue) {
            PropertyChange<PVALUE> propertyChange = (PropertyChange<PVALUE>) propertyChanges.get(property);
            if (propertyChange == null) {
                propertyChange = new PropertyChange<PVALUE>(this.componentID, property, oldValue, newValue);
                propertyChanges.put(property, propertyChange);
            } else {
                propertyChange.newValue = newValue;
            }
        }

        public void removeChange(String property) {
            this.propertyChanges.remove(property);
        }

        public String getComponentID() {
            return componentID;
        }

        public PropertyChange getPropertyChanges(String property) {
            return propertyChanges.get(property);
        }
    }

    public static class PropertyChange<PVALUE> {

        private String componentID;
        private String property;
        private PVALUE oldValue;
        private PVALUE newValue;

        public PropertyChange(String componentID, String property, PVALUE oldValue, PVALUE newValue) {
            super();
            this.componentID = componentID;
            this.property = property;
            this.oldValue = oldValue;
            this.newValue = newValue;
        }

        public String getComponentID() {
            return componentID;
        }

        public String getProperty() {
            return property;
        }

        public PVALUE getNewValue() {
            return newValue;
        }

        public PVALUE getOldValue() {
            return oldValue;
        }

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this);
        }
    }

}
