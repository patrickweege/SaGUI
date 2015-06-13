package com.fatuhiva.model.feature;

import com.tuamotu.commons.i18n.I18n;


public interface IFatuLabelFeature {

	public static final String LABEL_PROPERTY = "label";

	public void setLabel(I18n label);

	public I18n getLabel();

}
