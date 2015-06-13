package com.fatuhiva.touch.render.combobox;

import com.fatuhiva.ext.common.render.IComponentRender;
import com.fatuhiva.ext.common.render.RenderException;
import com.fatuhiva.ext.common.render.util.RenderWriter;
import com.fatuhiva.model.FatuElement;
import com.fatuhiva.model.container.page.FatuPage;
import com.fatuhiva.model.list.combo.editable.FatuComboBox;
import com.fatuhiva.model.util.FatuUtil;

public class JextComboBoxOnSelectListener implements IComponentRender<FatuComboBox<? extends FatuElement>> {

	@Override
	public boolean render(FatuComboBox<? extends FatuElement> component, RenderWriter out) throws RenderException {
		try {
			FatuPage page = FatuUtil.getPage(component);
			out.tab().appendLn("'select' : function(combo, records, eOpts) {");
			out.ident();
			out.tab().appendLn("	var selection;"); 
			out.tab().appendLn("	Ext.Array.each(records, function(record) {");
			out.ident();
			out.tab().appendLn("		selection = (selection == undefined ? record.get('id') :  ';' + record.get('value'));");
			out.udent();
			out.tab().appendLn("	});");
			out.tab().appendLn("	Ext.Ajax.request({");
			out.ident();
			out.tab().appendLn("		url: '../action',");
			out.tab().formatLn("		params: { PAGE_ID: '%1s', COMPONENT_ID: '%2s', NEW_VALUE: selection},", page.getId(), component.getId());
			out.tab().appendLn("		success: function(response, opts) {");
			out.ident();
			out.tab().appendLn("			eval(response.responseText);");
			out.udent();
			out.tab().appendLn("		}").ln();
			out.udent();
			out.tab().appendLn("	});").ln();
			out.udent();
			out.tab().appendLn("}").ln();
		} catch (Exception e) {
			throw new RenderException(e);
		}
		return true;
	}

	@Override
	public void update(FatuComboBox<? extends FatuElement> component, RenderWriter out) throws RenderException {
	}
}
