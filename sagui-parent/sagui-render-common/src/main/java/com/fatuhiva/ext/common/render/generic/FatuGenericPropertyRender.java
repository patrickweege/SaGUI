package com.fatuhiva.ext.common.render.generic;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.ObjectUtils;

import com.fatuhiva.ext.common.render.IComponentRender;
import com.fatuhiva.ext.common.render.RenderException;
import com.fatuhiva.ext.common.render.util.RenderWriter;
import com.fatuhiva.model.FatuComponent;
import com.pw.common.transformer.ToJsTransformers;

@SuppressWarnings("rawtypes")
public class FatuGenericPropertyRender<T extends FatuComponent> implements IComponentRender<T> {

    private final String sourceProp;

    private final String configName;
    private final String setterName;
    private final Class targetClass;
    private Object defaultIfNull;
    private boolean renderIffNull;

    public FatuGenericPropertyRender(String sourceProp, String configName, String setterName, Class targetClass) {
        this.configName = configName;
        this.setterName = setterName;
        this.sourceProp = sourceProp;
        this.targetClass = targetClass;
    }

    public FatuGenericPropertyRender(Class<T> componentClass, String sourceProp, String configName, String setterName, Class targetClass) {
        this.configName = configName;
        this.setterName = setterName;
        this.sourceProp = sourceProp;
        this.targetClass = targetClass;
    }

    @Override
    public boolean render(T component, RenderWriter out) throws RenderException {
        try {
            Object propValue = getScriptValue(component, sourceProp);
            if (propValue == null) {
                return false;
            } else {
                out.tab().writeConfig(configName, propValue).ln();
            }
        } catch (Exception e) {
            throw new RenderException(e);
        }
        return true;
    }

    @Override
    public void update(T component, RenderWriter out) throws RenderException {
        if (setterName == null) return;
        try {
            Object scriptValue = getScriptValue(component, sourceProp);
            if (scriptValue != null) {
                out.format("if(cmp != null) cmp.%1$s(%2$s);", setterName, scriptValue).ln();
            }
        } catch (Exception e) {
            throw new RenderException(e);
        }
    }

    public void setDefaultIfNull(Object defaultIfNull) {
        this.defaultIfNull = defaultIfNull;

    }

    public void setRenderIffNull(boolean renderIffNull) {
        this.renderIffNull = renderIffNull;
    }

    protected Object getPropertyValue(T component, String prop) {
        try {
            Object propValue = PropertyUtils.getProperty(component, prop);
            return propValue;
        } catch (Exception e) {
            throw new RenderException(e);
        }
    }

    private Object getScriptValue(T component, String prop) throws RenderException {
        try {
            Object propValue = this.getPropertyValue(component, prop);
            if (propValue == null && !renderIffNull) {
                return null;
            } else {
                propValue = ObjectUtils.defaultIfNull(propValue, defaultIfNull);
                Object toRender = ToJsTransformers.getTransformer(targetClass).transform(propValue);
                return toRender;
            }
        } catch (Exception e) {
            throw new RenderException(e);
        }
    }

}
