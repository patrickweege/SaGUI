package com.fatuhiva.model.editable.editbox;

import com.fatuhiva.model.datasource.FatuAbstractDataSource;
import com.fatuhiva.model.editable.FatuValueEditable;

public class FatuTextBox extends FatuValueEditable<String>  {

    public FatuTextBox() {
        this(null);
    }
    
    public FatuTextBox(FatuAbstractDataSource<String> dataSource) {
        super(dataSource);
    }
    
}
