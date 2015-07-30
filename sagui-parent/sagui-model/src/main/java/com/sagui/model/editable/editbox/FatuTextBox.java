package com.sagui.model.editable.editbox;

import com.sagui.model.datasource.FatuAbstractDataSource;
import com.sagui.model.editable.FatuValueEditable;

public class FatuTextBox extends FatuValueEditable<String>  {

    public FatuTextBox() {
        this(null);
    }
    
    public FatuTextBox(FatuAbstractDataSource<String> dataSource) {
        super(dataSource);
    }
    
}
