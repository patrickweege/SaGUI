package com.fatuhiva.model.feature;

import java.awt.Insets;

/**
 * Interface that define the Padding Feature contract of a component
 * 
 * @author patrick.weege
 * 
 */
public interface IFatuPaddingFeature {

    /**
     * Padding values for the Component.<br>
     * This Feature define the space Between the Border of This component and his children.
     *
     * @param padding
     */
    public void setPadding(Insets padding);

    public Insets getPadding();

}
