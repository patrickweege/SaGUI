package com.fatuhiva.touch.render.container;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.fatuhiva.ext.common.render.ChangesManager;
import com.fatuhiva.ext.common.render.IComponentRender;
import com.fatuhiva.ext.common.render.IRender;
import com.fatuhiva.ext.common.render.IRenderManager;
import com.fatuhiva.ext.common.render.RenderException;
import com.fatuhiva.ext.common.render.ChangesManager.ComponentChange;
import com.fatuhiva.ext.common.render.ChangesManager.PropertyChange;
import com.fatuhiva.ext.common.render.util.RenderWriter;
import com.fatuhiva.model.FatuComponent;
import com.fatuhiva.model.FatuContainer;
import com.fatuhiva.model.util.FatuCollectAllChildrenVisitor;
import com.pw.common.JextContext;

@SuppressWarnings({"rawtypes","unchecked"})
public class JextChildrenRender<T extends FatuContainer> implements IComponentRender<T> {

	@Override
	public boolean render(T component, RenderWriter out) throws RenderException {
        IRenderManager renderManager = JextContext.getValue(IRenderManager.RENDER_MANAGER_KEY);
        boolean renderedAny = false;
		try {
			List<FatuComponent> children = component.getChildren();
			if(!children.isEmpty()) {
				out.tab().append("items : [").ln();
				out.ident();
				for (FatuComponent child : children) {
					IRender<FatuComponent> render = renderManager.getRender(child);
					if (render != null) {
						if(render.render(child, out)) {
							out.pushComma();
							renderedAny = true;
						}
					}
				}
				if(renderedAny) out.popComma();
				out.udent();
				out.tab().append("]").ln();
			}
		} catch (IOException e) {
			throw new RenderException(e);
		}
		return renderedAny;
	}

	@Override
    public void update(T component, RenderWriter out) throws RenderException {
        ChangesManager changeMgr = JextContext.getValue(ChangesManager.CHANGED_PROPERTIES_KEY);
        IRenderManager renderManager = JextContext.getValue(IRenderManager.RENDER_MANAGER_KEY);
        ComponentChange cmpChange = changeMgr.getChanges(component.getId());
        try {
            if(cmpChange != null && cmpChange.getPropertyChanges("children") != null) {
	             PropertyChange propChange = cmpChange.getPropertyChanges("children");
	
	            List<FatuComponent> oldChidren = (List<FatuComponent>) propChange.getOldValue();
	            List<FatuComponent> newChidren = (List<FatuComponent>) component.getChildren();
	
	            Collection<FatuComponent> toDelete = CollectionUtils.subtract(oldChidren, newChidren);
	
	            // Remove all Deleted Objects
	            for (FatuComponent cmpToDelete : toDelete) {
	                out.append("var cmp = Ext.getCmp('").append(component.getId()).append("');").ln();
	                out.format("if(cmp != null) cmp.remove('%1$s');",cmpToDelete.getId());
	            }
	            clearChanges(toDelete);
	            
	            Collection<FatuComponent> renderAsNew = CollectionUtils.subtract(newChidren, oldChidren);
	            for (FatuComponent newCmp : renderAsNew) {
	                out.append("var cmp = Ext.getCmp('").append(component.getId()).append("');").ln();
	                out.format("if(cmp != null) cmp.insert(%1$s,",newChidren.indexOf(newCmp)).ln();
	            	IRender<FatuComponent> render = renderManager.getRender(newCmp);
	                render.render(newCmp, out);
	                out.append(");").ln();
	            }
	            clearChanges(renderAsNew);
        	}

        } catch (Exception e) {
            throw new RenderException(e);
        }
    }

    
    private void clearChanges(Collection<FatuComponent> components) {
        FatuCollectAllChildrenVisitor visitor = new FatuCollectAllChildrenVisitor();
        visitor.visit(components);
        List<FatuComponent> selected = visitor.getSelected();
        for (FatuComponent toClear : selected) {
            ChangesManager changeMgr = JextContext.getValue(ChangesManager.CHANGED_PROPERTIES_KEY);
            changeMgr.removeChanges(toClear.getId());
        }
    }
    
}
