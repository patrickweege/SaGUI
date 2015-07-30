package com.sagui.ext.render.container.page;

import java.io.IOException;

import com.sagui.ext.common.render.IComponentRender;
import com.sagui.ext.common.render.RenderException;
import com.sagui.ext.common.render.generic.FatuCompositeRender;
import com.sagui.ext.common.render.generic.FatuIDPropertyRender;
import com.sagui.ext.common.render.util.RenderWriter;
import com.sagui.ext.render.container.JextChildrenRender;
import com.sagui.ext.render.layout.FatuExtLayoutRender;
import com.sagui.model.container.page.FatuPage;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class JextPageRender implements IComponentRender<FatuPage> {
	
	private final FatuCompositeRender<FatuPage> composite;

    public JextPageRender() {
		this.composite = new FatuCompositeRender<FatuPage>();
		composite.addRender(new FatuIDPropertyRender<FatuPage>());
		composite.addRender(new FatuExtLayoutRender());
		composite.addRender(new JextChildrenRender<FatuPage>());
	}
	
	
	@Override
	public boolean render(FatuPage component, RenderWriter out) throws RenderException {
		try {
			out.appendLn("Ext.create('Ext.container.Viewport', {");
			out.ident();
			composite.render(component, out);
			out.udent();
			out.append("});");
		} catch (IOException e) {
			throw new RenderException(e);
		}
		return true;
	}

	

	@Override
	public void update(FatuPage component, RenderWriter out)
			throws RenderException {
		// TODO Auto-generated method stub

	}

	// public JextPageRender() {
	// super(new ClassToExtMapping<JextPage>(JextPage.class,
	// "Ext.panel.Panel"));
	// }
	//
	// @Override
	// protected void renderBody(JextPage component, RenderWriter w) throws
	// IOException {
	// super.renderBody(component, w);
	// w.tab().format(",%1$s : %2$s", "height", "600").ln();
	// w.tab().append(",renderTo : Ext.getBody()").ln();
	// }
	//
	// @Override
	// protected void renderFooter(JextPage cmp, RenderWriter w) throws
	// IOException {
	// w.tab().appendLn("});");
	// w.append("Ext.tip.QuickTipManager.init;").ln();
	// }

	// @Override
	// public void render(JextPage cmp, RenderWriter w) throws RenderException {
	// try {
	// w.ident();
	//
	//
	//
	// w.append("var panel = new Ext.panel.Panel({").ln();
	// w.tab().format("%1$s : '%2$s'", "id", cmp.getId()).ln();
	// w.tab().format(",%1$s : %2$s", "height", "600").ln();
	// w.tab().format(",%1$s : %2$s", "layout", "'fit'").ln();
	// w.tab().append(",renderTo : Ext.getBody()").ln();
	// w.append("});").ln();
	// w.unident();
	// w.append("Ext.tip.QuickTipManager.init;").ln();
	//
	// for (JextForm form : cmp.getChildren()) {
	// Render<JextForm> render = RenderManager.getInstance().getRender(form);
	// render.render(form, w);
	// }
	// } catch (IOException e) {
	// throw new RenderException(e);
	// }
	// }

}
