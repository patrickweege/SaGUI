package com.sagui.model.layout.box;

import java.awt.Insets;

import com.sagui.model.feature.IFatuPaddingFeature;
import com.sagui.model.layout.IFatuLayoutManager;

public class FatuHBoxLayout implements IFatuLayoutManager<FatuHBoxLayoutRule>, IFatuPaddingFeature {

    public static final FatuHBoxLayout HBOX_DEFAULT = new FatuHBoxLayout(FatuBoxLayoutAlign.START, FatuBoxPosition.START);

    private final FatuBoxLayoutAlign align;
    private final FatuBoxPosition position;
    private Insets padding;


    public FatuHBoxLayout(FatuBoxLayoutAlign align, FatuBoxPosition position) {
        this.align = align;
        this.position = position;
    }

    @Override
    public void setRule(String id, FatuHBoxLayoutRule rule) {
        if (!(rule instanceof FatuHBoxLayoutRule)) {
            throw new IllegalArgumentException("Invalid Layout Rule: " + rule);
        }
    }

    @Override
    public FatuHBoxLayoutRule getRule(String id) {
        return FatuHBoxLayoutRule.HBOX_LAYOUT_RULE;
    }

    public FatuBoxLayoutAlign getAlign() {
        return align;
    }

    public FatuBoxPosition getPosition() {
        return position;
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
