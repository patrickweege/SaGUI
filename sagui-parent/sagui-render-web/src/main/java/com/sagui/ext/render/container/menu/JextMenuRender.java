package com.sagui.ext.render.container.menu;

import org.apache.commons.lang.StringUtils;

import com.sagui.ext.common.render.RenderException;
import com.sagui.ext.common.render.generic.FatuAbstractRender;
import com.sagui.ext.common.render.generic.FatuExtFixedValuePropertyRender;
import com.sagui.ext.common.render.generic.FatuGenericMappedPropertyRender;
import com.sagui.ext.common.render.util.RenderWriter;
import com.sagui.ext.render.container.JextChildrenRender;
import com.sagui.ext.render.generic.JextSizeFeatureRender;
import com.sagui.model.container.menu.FatuMenu;
import com.sagui.model.feature.FatuOrientation;
import com.sagui.model.feature.IFatuOrientationFeature;

@SuppressWarnings({ "rawtypes" })
public class JextMenuRender extends FatuAbstractRender<FatuMenu> {

	public JextMenuRender() {
		super("Ext.menu.Menu");

		super.composite.addRender(new FatuExtFixedValuePropertyRender<FatuMenu>("floating", false));
		super.composite.addRender(new FatuExtFixedValuePropertyRender<FatuMenu>("docked", "left"));
		super.composite.addRender(new JextSizeFeatureRender<FatuMenu>());

		
		//Orientation
		super.composite.addRender(new JextMenuOrientationPropertyRender());
		// Properties
		// super.composite.addRender(new JextSizeFeatureRender());
		// Layout and Layout Rules
		// super.composite.addRender(new FatuExtLayoutRender());
		// Children
		super.composite.addRender(new JextChildrenRender<FatuMenu>());
	}

	@Override
	public boolean render(FatuMenu component, RenderWriter out) throws RenderException {
		return super.render(component, out);
	}

	private class JextMenuOrientationPropertyRender extends FatuGenericMappedPropertyRender<FatuMenu> {

		public JextMenuOrientationPropertyRender() {
			super(FatuMenu.class,  IFatuOrientationFeature.ORIENTATION_PROPERTY, "layout", null, String.class);
			super.setRenderIffNull(true);
			super.setDefaultIfNull(StringUtils.EMPTY);
			super.mapSrcToTargetValue(FatuOrientation.HORIZONTAL, "hbox");
			super.mapSrcToTargetValue(FatuOrientation.VERTICAL, "vbox");
		}
		
	}

}
