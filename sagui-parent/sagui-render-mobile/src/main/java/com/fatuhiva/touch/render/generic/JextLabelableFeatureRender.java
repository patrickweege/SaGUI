package com.fatuhiva.touch.render.generic;

import com.fatuhiva.model.FatuComponent;

@SuppressWarnings("unchecked")
public class JextLabelableFeatureRender<T extends FatuComponent> extends CompositeRender<T> {

    public JextLabelableFeatureRender() {
	    addRender(new JextLabelRender());
        addRender(new JextLabelWidthRender());
	}

	
	public class JextLabelRender extends JextGenericPropertyRender<T> {

	    public JextLabelRender() {
	        super("label", "fieldLabel", null, String.class);
	    }

	}

    public class JextLabelWidthRender extends JextGenericPropertyRender<T> {

        public JextLabelWidthRender() {
            super("labelWidth", "labelWidth", null, Integer.class);
        }

    }
	
}



