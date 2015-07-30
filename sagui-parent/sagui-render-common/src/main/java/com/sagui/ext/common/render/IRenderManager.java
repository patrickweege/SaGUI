package com.sagui.ext.common.render;

public interface IRenderManager {

    public static final String RENDER_MANAGER_KEY = "IRenderManager";
    
    public <T>  IRender<T> getRender(Class<T> renderFor);
    
    public <T>  IRender<T> getRender(T renderFor);

    
}
