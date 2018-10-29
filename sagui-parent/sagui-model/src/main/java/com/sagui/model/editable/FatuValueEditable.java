package com.sagui.model.editable;

import java.util.Comparator;
import java.util.Observable;
import java.util.Observer;

import org.apache.commons.collections.ComparatorUtils;
import org.apache.commons.collections.comparators.ComparableComparator;

import com.sagui.dataset.commons.i18n.I18n;
import com.sagui.model.datasource.FatuAbstractDataSource;

@SuppressWarnings("unchecked")
public abstract class FatuValueEditable<V> extends FatuEditable {

    private V value;
    private FatuAbstractDataSource<V> dataSource;

    public FatuValueEditable(FatuAbstractDataSource<V> dataSource) {
        this.dataSource = dataSource;
        if (this.dataSource != null) {
            this.dataSource.addObserver(new DatasourceObserver(this));
        }
    }

    public V getValue() {
        return value;
    }

    public void setValue(V newValue) {
    	this.setErrorMsg(null);
        this.value = newValue;
        try {
            if (dataSource != null) {
                dataSource.setValue(value);
                this.value = dataSource.getValue();
            }
        } catch (Exception e) {
            this.setErrorMsg(new I18n("error").setDefault(e.getMessage()));
        }    	
    	
    	if (this.getErrorMsg() != null) {
    		this.setErrorMsg(null);
    	}
    }

    private void internalSetValue(V valueToSet) {
        this.value = valueToSet;
    }
    
    private class DatasourceObserver implements Observer {

        private final FatuValueEditable<V> editable;
        private final Comparator<V> valueComparator = ComparatorUtils.nullHighComparator(ComparableComparator.getInstance());

        private DatasourceObserver(FatuValueEditable<V> editable) {
            this.editable = editable;
        }

        @Override
        public void update(Observable o, Object arg) {
            if (this.editable.dataSource != null && o == this.editable.dataSource) {
                V dsValue = this.editable.dataSource.getValue();
                V thisValue = this.editable.value;
                int result = this.valueComparator.compare(dsValue, thisValue);
                if (result != 0) {
                    this.editable.internalSetValue(dsValue);
                }
                this.editable.setEnabled(this.editable.dataSource.isEditable());
            }
        }

    }

}
