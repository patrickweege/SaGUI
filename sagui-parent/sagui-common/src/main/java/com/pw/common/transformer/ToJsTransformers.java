package com.pw.common.transformer;

import org.apache.commons.collections.Transformer;
import org.json.JSONArray;


@SuppressWarnings("rawtypes")
public class ToJsTransformers {

	private static final Transformer TO_JS_STRING_TRANSFORMER = new ToJsStringTransformer();
	private static final Transformer TO_JS_INT_TRANSFORMER = new ToJsIntegerTransformer();
	private static final Transformer TO_JS_BOOLEAN_TRANSFORMER = new ToJsBooleanTransformer();
	private static final Transformer TO_JS_STRING_ARRAY_TRANSFORMER = new ToJSONArrayTransformer();

	public static final Transformer getTransformer(Class jsClass) {
		if (jsClass == String.class) {
			return TO_JS_STRING_TRANSFORMER;
		} else if (jsClass == Integer.class) {
			return TO_JS_INT_TRANSFORMER;
		} else if (jsClass == Boolean.class) {
			return TO_JS_BOOLEAN_TRANSFORMER;
		} else if (jsClass == JSONArray.class) {
			return TO_JS_STRING_ARRAY_TRANSFORMER;
		}
		return null;
	}

}
