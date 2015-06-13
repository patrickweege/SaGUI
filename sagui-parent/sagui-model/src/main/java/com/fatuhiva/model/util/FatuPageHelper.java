package com.fatuhiva.model.util;

import com.fatuhiva.model.container.page.FatuPage;

public class FatuPageHelper {

    @SuppressWarnings("unchecked")
	static FatuPage<?> getPageByClassName(String pageClassName) {
		int lastSlash = pageClassName.lastIndexOf("/");
		String pageName = pageClassName.substring(lastSlash + 1);
		try {
            Class<FatuPage<?>> pageClass = (Class<FatuPage<?>>) Class.forName(pageName);
			return getPageByClass(pageClass); 
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	static FatuPage<?> getPageByClass(Class<? extends FatuPage<?>> pageClass)  {
	    FatuPage<?> requestedPage;
		try {
			requestedPage = pageClass.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return requestedPage;
	}

}
