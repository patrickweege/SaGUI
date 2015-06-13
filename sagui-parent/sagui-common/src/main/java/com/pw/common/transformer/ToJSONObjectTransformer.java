package com.pw.common.transformer;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.Transformer;
import org.json.JSONObject;

public class ToJSONObjectTransformer implements Transformer {

	private final Map<String, String> properties;

	public ToJSONObjectTransformer() {
		this.properties = new LinkedHashMap<String, String>();
	}

	@Override
	public Object transform(Object value) {
		try {
			JSONObject jsonObject = new JSONObject();
			for (Entry<String, String> entry : properties.entrySet()) {
				Object propValue = PropertyUtils.getProperty(value, entry.getKey());
				jsonObject.put(entry.getValue(), propValue);
			}
			return jsonObject;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void addProperty(String beanProperty, String jsProperty) {
		this.properties.put(beanProperty, jsProperty);
	}

}
