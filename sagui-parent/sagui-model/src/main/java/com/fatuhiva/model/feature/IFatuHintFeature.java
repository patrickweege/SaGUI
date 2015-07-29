package com.fatuhiva.model.feature;

import com.sagui.dataset.commons.i18n.I18n;


public interface IFatuHintFeature {

	public static final String HINT_PROPERTY = "hint";

	public void setHint(I18n label);

	public I18n getHint();

}
