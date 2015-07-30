package com.sagui.model.feature;

import java.awt.Insets;

/**
 * Interface that define the Margin Feature for a Component
 * 
 * @author patrick.weege
 * 
 */
public interface IFatuMarginFeature {

	public static final String MARGINS_PROPERTY = "margins";

	/**
	 * Margin for the Component.<br>
	 * This Feature define the space Between this component and the parent
	 * component.
	 * 
	 * @param padding
	 */
	public void setMargins(Insets margin);

	public Insets getMargins();

}
