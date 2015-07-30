package com.sagui.model.feature;

import com.sagui.dataset.commons.i18n.I18n;

public interface IFatuErrorMsgFeature {

	public static final String ERROR_MSG_PROPERTY = "errorMsg";

	public I18n getErrorMsg();

	public void setErrorMsg(I18n errorMsg);

}
