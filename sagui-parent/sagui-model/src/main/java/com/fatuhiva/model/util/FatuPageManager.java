package com.fatuhiva.model.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.fatuhiva.model.container.page.FatuPage;

public class FatuPageManager {
	
	public static final String PAGE_MANAGER_KEY = FatuPageManager.class.getName();

	private List<FatuPage<?>> pages;
	
	public FatuPageManager() {
		this.pages = new ArrayList<FatuPage<?>>();
	}
	
	public FatuPage<?> openPage(String pageClassName) {
	    FatuPage<?> page = getPageByClassName(pageClassName);
		pages.add(page);
		return page;
	}
	
	public FatuPage<?> getPageByID(String pageID) {
		for (FatuPage<?> page : pages) {
			if(StringUtils.equals(page.getId(),pageID)) {
				return page;
			}
		}
		throw new RuntimeException("Page Not Found");
	}
	
	private FatuPage<?> getPageByClassName(String pageClassName) {
		int lastSlash = pageClassName.lastIndexOf("/");
		String pageName = pageClassName.substring(lastSlash + 1);
		try {
			Class<FatuPage<?>> pageClass = (Class<FatuPage<?>>) Class.forName(pageName);
			return getPageByClass(pageClass); 
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	private FatuPage<?> getPageByClass(Class<? extends FatuPage<?>> pageClass)  {
	    FatuPage requestedPage = null;
		try {
			requestedPage = pageClass.newInstance();
			return requestedPage;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	
}
