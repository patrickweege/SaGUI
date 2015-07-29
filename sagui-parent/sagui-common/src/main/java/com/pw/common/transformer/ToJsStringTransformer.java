package com.pw.common.transformer;

import org.apache.commons.collections.Transformer;
import org.apache.commons.lang.StringEscapeUtils;

import com.sagui.dataset.commons.i18n.I18n;

public class ToJsStringTransformer implements Transformer {

	private static final String STR_FORMAT = "'%1$s'";

	@Override
	public Object transform(Object value) {
		return toString(value);
	}

	private Object toString(Object value) {
		String toFormat = null;
		if (value instanceof I18n) {
			toFormat = ((I18n) value).getDefault();
		} else if (value instanceof Integer) {
			toFormat = Integer.toString((Integer) value);
        } else if (value instanceof Double) {
            toFormat = ((Double) value).toString();
		} else if (value instanceof String) {
			toFormat = (String) value;
		} else if (value instanceof Boolean) {
			toFormat = ((Boolean) value).toString();
		}
		
		//toFormat = StringEscapeUtils.escapeHtml(toFormat);
		toFormat = StringEscapeUtils.escapeJavaScript(toFormat);
		return String.format(STR_FORMAT, toFormat);
	}
	
	
	public static void main(String[] args) {
		
		System.out.println(StringEscapeUtils.escapeHtml("Or�amento"));
		
	}


}
