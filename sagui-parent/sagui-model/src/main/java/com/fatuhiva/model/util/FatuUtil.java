package com.fatuhiva.model.util;

import com.fatuhiva.model.FatuComponent;
import com.fatuhiva.model.container.page.FatuPage;
import com.pw.common.JextContext;

public class FatuUtil {

	public static <T extends FatuComponent, F extends FatuComponent> T getCmp(String id, F component) {
		FatuFindComponentVisitor<F> findVisitor = new FatuFindComponentVisitor<F>(id);
		findVisitor.visit(component);
		return findVisitor.getFound();
	}

	public static FatuPage<?> getPage(FatuComponent cmp) {
		FatuComponent parent = cmp;
		do {
			if(parent instanceof FatuPage) return (FatuPage<?>) parent;
		} while ((parent = parent.getParent()) != null);
		
		FatuPage<?> currPage = JextContext.getValue("CURRENT_PAGE");
		
		return currPage;
	}

}
