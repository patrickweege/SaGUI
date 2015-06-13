package com.pw.common.transformer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.apache.commons.collections.Transformer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressWarnings("unchecked")
public class ToCollectionTransformer<T> implements Transformer {

    @Override
    public Collection<T> transform(Object obj) {
        if (obj instanceof JSONArray) {
            return transformJSONArray((JSONArray) obj);
        } else if (obj instanceof String) {
            return transformString((String) obj);
        } else {
            return transformObject(obj);
        }
    }

    private Collection<T> transformObject(Object obj) {
        return Collections.singletonList((T) obj);
    }

    private Collection<T> transformString(String obj) {
        // Try convert do String > JSONArray > Collection 
        try {
            JSONArray json = new JSONArray(obj);
            return transformJSONArray(json);
        } catch (JSONException e) {
            // Nothing to do
        }

        // Try convert do String > JSONObject > Collection
        try {
            JSONObject json = new JSONObject(obj);
            return transformObject(json);
        } catch (JSONException e) {
            // Nothing to do
        }
        
        // If the previous Conversions Fails. Contert to Singletion List.
        return transformObject(obj);
    }

    private Collection<T> transformJSONArray(JSONArray obj) {
        Collection<T> theCollection = null;
        try {
            if (obj != null) {
                theCollection = new ArrayList<T>();
                for (int i = 0; i < obj.length(); i++) {
                    theCollection.add((T) obj.get(i));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return theCollection;
    }

}
