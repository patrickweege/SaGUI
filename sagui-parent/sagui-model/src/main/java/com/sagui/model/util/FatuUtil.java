package com.sagui.model.util;

import com.pw.common.JextContext;
import com.sagui.model.FatuComponent;
import com.sagui.model.container.page.FatuPage;

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
