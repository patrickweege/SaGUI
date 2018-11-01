
package com.sagui.ext.common.render.generic;
import java.util.HashMap;
import java.util.Map;

import com.sagui.model.FatuComponent;

@SuppressWarnings("rawtypes")
public class FatuGenericMappedPropertyRender<T extends FatuComponent> extends FatuGenericPropertyRender<T> {

    private final Map<Object, Object> srcToTargetMap;

    public FatuGenericMappedPropertyRender(Class<T> componentClass, String sourceProp, String configName, String setterName, Class targetClass) {
    	super(componentClass, sourceProp, configName, setterName, targetClass);
        this.srcToTargetMap = new HashMap<>();
    }

    public void mapSrcToTargetValue(Object srcValue, Object targetValue) {
    	this.srcToTargetMap.put(srcValue, targetValue);
    }
    
    @Override
    protected Object getPropertyValue(T component, String prop) {
    	Object srcPropValue = super.getPropertyValue(component, prop);
    	return this.srcToTargetMap.get(srcPropValue);
    }
    
    
}
