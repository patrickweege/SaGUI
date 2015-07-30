package com.sagui.model.feature;

import com.sagui.dataset.commons.i18n.I18n;

public interface IFatuLabelableFeature {

	public static final String LABEL_PROPERTY = "label";
	public static final String LABEL_WIDTH_PROPERTY = "labelWidth";
	public static final String LABEL_POSITION_PROPERTY = "labelPosition";

	public void setLabelWidth(Integer labelWidth);

	public Integer getLabelWidth();

	public I18n getLabel();

	public void setLabel(I18n label);

	public void setLabel(I18n label, FatuLabelPosition position);

	public FatuLabelPosition getLabelPosition();
}
