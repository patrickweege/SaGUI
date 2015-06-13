package com.fatuhiva.ext.common.render.generic;

import org.apache.commons.lang.StringUtils;

import com.fatuhiva.ext.common.render.IRender;
import com.fatuhiva.ext.common.render.RenderException;
import com.fatuhiva.ext.common.render.util.RenderWriter;

public abstract class FatuObjectPropertyRender<T> implements IRender<T> {

    protected final FatuCompositeRender<T> composite;
    protected final String extProperty;
    protected final String extType;
    protected final FatuExtCreationMode creationMode;

    /**
     * With this constructor the Creation will be defined as {@link FatuExtCreationMode#CONFIG} and no extType
     * 
     * @param extProperty
     */
    @SuppressWarnings("unchecked")
    public FatuObjectPropertyRender(String extProperty) {
        this.composite = new FatuCompositeRender<T>();
        this.extProperty = extProperty;
        this.extType = null;
        this.creationMode = FatuExtCreationMode.CONFIG;
    }

    @SuppressWarnings("unchecked")
    public FatuObjectPropertyRender(String extProperty, String extType, FatuExtCreationMode creationMode) {
        this.composite = new FatuCompositeRender<T>();
        this.extProperty = extProperty;
        this.extType = extType;
        this.creationMode = creationMode;
    }

    @Override
    public final boolean render(T component, RenderWriter out) throws RenderException {
        if (composite.size() > 0) {
            try {

                if (creationMode == FatuExtCreationMode.EXT_CREATE) {
                    out.writeConfigFmt(extProperty, "Ext.create('%1$s', {", extType).ln();
                    out.ident();
                    composite.render(component, out);
                    out.udent();
                    out.append("})").ln();
                } else if (creationMode == FatuExtCreationMode.XTYPE) {
                    out.writeConfig(extProperty, "{").ln();
                    out.ident();
                    out.writeConfigAsString("xtype", extType).commaLn();
                    composite.render(component, out);
                    out.udent();
                    out.append("}").ln();
                } else if (creationMode == FatuExtCreationMode.NEW) {
                    out.writeConfigFmt(extProperty, "new %1$s({", extType).ln();
                    out.ident();
                    composite.render(component, out);
                    out.udent();
                    out.append("})").ln();
                } else if (creationMode == FatuExtCreationMode.CONFIG) {
                    out.writeConfig(extProperty, "{").ln();
                    out.ident();
                    if (StringUtils.isNotBlank(extType)) {
                        out.writeConfigAsString("xtype", extType).commaLn();
                    }
                    composite.render(component, out);
                    out.udent();
                    out.append("}").ln();
                }
                return true;
            } catch (Exception e) {
                throw new RenderException(e);
            }
        }
        return false;
    }

    @Override
    public abstract void update(T component, RenderWriter out) throws RenderException;

}
