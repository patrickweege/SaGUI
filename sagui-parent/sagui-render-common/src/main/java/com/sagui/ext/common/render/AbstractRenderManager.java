package com.sagui.ext.common.render;

import java.util.HashMap;
import java.util.Map;


public class AbstractRenderManager implements IRenderManager {

    private Map<Class<?>,IRender<?>> renders;

    public AbstractRenderManager() {
        this.renders = new HashMap<Class<?>, IRender<?>>();
    }
    
    protected <T> void addRender(Class<T> clazz, IRender<T> render) {
        this.renders.put(clazz, render);
    }
    
    
    @SuppressWarnings("unchecked")
    public <T>  IRender<T> getRender(Class<T> renderFor) {
        return (IRender<T>)this.renders.get(renderFor);
    }
    

    @SuppressWarnings("unchecked")
    public <T>  IRender<T> getRender(T renderFor) {
        Class<T> clazz = (Class<T>) renderFor.getClass();
        while(true) {
            IRender<T> render = getRender(clazz);
            if(render != null) {
                return render;
            }
            clazz = (Class<T>) clazz.getSuperclass();
            if(clazz == null) return null;
        }
    }

}
