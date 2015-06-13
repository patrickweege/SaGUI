package com.pw.common.transformer;

import java.util.Collection;

import org.apache.commons.collections.Transformer;
import org.json.JSONArray;

@SuppressWarnings("rawtypes")
public class ToJSONArrayTransformer implements Transformer {

	public ToJSONArrayTransformer() {
	}

	@Override
	public Object transform(Object value) {
		JSONArray transformed = null;
		if (value != null && value.getClass().isArray()) {
			transformed = toJSONArray((Object[]) value);
		} else if (Collection.class.isAssignableFrom(value.getClass())) {
			transformed = toJSONArray((Collection) value);
		}
		return transformed;
	}

	private JSONArray toJSONArray(Object[] values) {
		JSONArray jsonArray = new JSONArray();
		for (Object elem : values) {
			jsonArray.put((String) elem);
		}
		return jsonArray;
	}

	private JSONArray toJSONArray(Collection values) {
		JSONArray jsonArray = new JSONArray();
		for (Object elem : values) {
			jsonArray.put((String) elem);
		}
		return jsonArray;
	}

}
