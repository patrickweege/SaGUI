package com.pw.common;

import java.util.HashMap;
import java.util.Map;

public class JextContext {

	private static final ThreadLocal<JextContext> threadLocal = new ThreadLocal<JextContext>();

	private final Map<String, Object> map;

	private JextContext() {
		this.map = new HashMap<String, Object>();
	}

	private static JextContext getContext() {
		return JextContext.threadLocal.get();
	}

	public static void initialize() {
		JextContext.threadLocal.set(new JextContext());
	}

	public static void release() {
		JextContext.threadLocal.set(null);
	}
		
	public static void setValue(String key, Object value) {
		getContext().map.put(key, value);
	}

	@SuppressWarnings("unchecked")
    public static <T> T getValue(String key) {
		return (T) getContext().map.get(key);
	}
	
}
