package com.fatuhiva.ext.common.render.util;

import java.awt.Color;

import org.apache.commons.beanutils.Converter;

public class ColorHexConverter implements Converter {

    private static final ColorHexConverter SINGLETON = new ColorHexConverter();

    private ColorHexConverter() {

    }

    @Override
    @SuppressWarnings("rawtypes")
    public Object convert(Class targetClass, Object value) {
        if (targetClass == String.class) {
            return toHexString((Color) value);
        }
        throw new UnsupportedOperationException("Canot convert to Target Type: " + targetClass);
    }

    public static final ColorHexConverter getInstance() {
        return SINGLETON;
    }

    private final String toHexString(Color colour) throws NullPointerException {
        String hexColour = Integer.toHexString(colour.getRGB() & 0xffffff);
        if (hexColour.length() < 6) {
            hexColour = "000000".substring(0, 6 - hexColour.length()) + hexColour;
        }
        return "#" + hexColour;
    }
}
