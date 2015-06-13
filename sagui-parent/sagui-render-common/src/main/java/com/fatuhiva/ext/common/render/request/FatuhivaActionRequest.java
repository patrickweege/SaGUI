package com.fatuhiva.ext.common.render.request;



public class FatuhivaActionRequest extends FatuhivaRequest {
    
    public static final String PAGE_PARAM = "PAGE_ID";
    public static final String COMPONENT_PARAM = "COMPONENT_ID";
    public static final String EVENT_PARAM = "EVENT";
    
    public FatuhivaActionRequest(String pageID, String componentID, String event) {
        super.addParamAsString(PAGE_PARAM, pageID);
        super.addParamAsString(COMPONENT_PARAM,componentID);
        super.addParamAsString(EVENT_PARAM,event);
    }

}
