package com.fatuhiva.model.layout.box;

import java.awt.Insets;

import com.fatuhiva.model.feature.IFatuPaddingFeature;
import com.fatuhiva.model.layout.IFatuLayoutManager;

public class FatuVBoxLayout implements IFatuLayoutManager<FatuVBoxLayoutRule>, IFatuPaddingFeature {

    public static final FatuVBoxLayout VBOX_DEFAULT = new FatuVBoxLayout(FatuBoxLayoutAlign.START, FatuBoxPosition.START);

    private final FatuBoxLayoutAlign align;
    private final FatuBoxPosition position;
    private Insets padding;

    public FatuVBoxLayout(FatuBoxLayoutAlign align, FatuBoxPosition position) {
        this.align = align;
        this.position = position;
    }

    public FatuBoxLayoutAlign getAlign() {
        return align;
    }

    public FatuBoxPosition getPosition() {
        return position;
    }

    @Override
    public void setRule(String id, FatuVBoxLayoutRule rule) {
        if (!(rule instanceof FatuVBoxLayoutRule)) {
            throw new IllegalArgumentException("Invalid Layout Rule: " + rule);
        }
    }

    @Override
    public FatuVBoxLayoutRule getRule(String id) {
        return FatuVBoxLayoutRule.VBOX_LAYOUT_RULE;
    }

    @Override
    public void setPadding(Insets padding) {
        this.padding = padding;
    }

    @Override
    public Insets getPadding() {
        return this.padding;
    }

}
