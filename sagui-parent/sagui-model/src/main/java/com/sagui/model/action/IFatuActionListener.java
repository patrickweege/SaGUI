package com.sagui.model.action;

public interface IFatuActionListener<EVT extends IFatuActionEvent> {

	public void actionPerformed(EVT evt);

}
