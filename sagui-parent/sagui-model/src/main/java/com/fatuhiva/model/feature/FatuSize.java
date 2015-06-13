package com.fatuhiva.model.feature;

public class FatuSize {

    public static final Integer NOT_ESPECIFIED = -1;
    
    private final int width;
    private final int height;

    public FatuSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

}
