package com.sagui.ext.common.render.request;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class FatuhivaRequest {
    
    private final Map<String, Object> parameters;

    public FatuhivaRequest() {
        this.parameters = new HashMap<String, Object>();
    }

    public FatuhivaRequest addParam(String name, Object value) {
        this.parameters.put(name, value);
        return this;
    }
    
    public FatuhivaRequest addParamAsString(String name, Object value) {
        this.parameters.put(name, String.format("'%1s'", value));
        return this;
    }
    
    public FatuhivaRequest addParamAsScript(String name, String value) {
        this.parameters.put(name, value);
        return this;
    }
    
    public Map<String,Object> getParameters() {
        return Collections.unmodifiableMap(parameters);
    }
}
