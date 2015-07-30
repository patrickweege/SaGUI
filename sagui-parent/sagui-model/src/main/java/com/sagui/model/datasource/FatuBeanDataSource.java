package com.sagui.model.datasource;

import com.sagui.dataset.commons.field.IField;

@SuppressWarnings("unchecked")
public class FatuBeanDataSource<BEAN, V> extends FatuAbstractDataSource<V> {

    private final IField<BEAN> field;
    private BEAN bean;

    public FatuBeanDataSource(IField<BEAN> field, BEAN bean) {
        this.bean = bean;
        this.field = field;
    }

    public void setBean(BEAN bean) {
        this.bean = bean;
        this.setChanged();
        this.notifyObservers();
    }

    @Override
    public void setValue(V value) {
        this.field.setValue(bean, value);
        this.setChanged();
        this.notifyObservers();
    }

    @Override
    public V getValue() {
        V value = (V) (bean == null ? null : this.field.getValue(bean));
        return value;
    }
    
    @Override
    public boolean isEditable() {
        return super.isEditable() && bean != null;
    }
    
}
