package com.pw.common.transformer;

import org.apache.commons.collections.Transformer;

import com.sagui.dataset.commons.i18n.I18n;

public class ToJsIntegerTransformer implements Transformer {

	private static final String INT_FORMAT = "%1$s";


	@Override
	public Object transform(Object value) {
		return toInteger(value);
	}

	private Object toInteger(Object value) {
		String toFormat = null;
		if (value instanceof String) {
			toFormat = ((I18n) value).getDefault();
		} else if (value instanceof Integer) {
			toFormat = Integer.toString((Integer) value);
		}
		return String.format(INT_FORMAT, toFormat);
	}






}
