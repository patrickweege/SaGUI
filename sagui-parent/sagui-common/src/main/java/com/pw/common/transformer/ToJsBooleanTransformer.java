package com.pw.common.transformer;

import org.apache.commons.collections.Transformer;

public class ToJsBooleanTransformer implements Transformer {

	private static final String BOOL_FORMAT = "%1$s";

	@Override
	public Object transform(Object value) {
		return toBoolean(value);
	}

	private Object toBoolean(Object value) {
		Boolean toFormat = Boolean.FALSE;
		if (value instanceof String) {
			toFormat = Boolean.valueOf((String) value);
		} else if (value instanceof Integer) {
			toFormat = ((Integer) value).intValue() == 1;
		} else if (value instanceof Boolean) {
			toFormat = ((Boolean) value);
		}
		return String.format(BOOL_FORMAT, toFormat);
	}

}
