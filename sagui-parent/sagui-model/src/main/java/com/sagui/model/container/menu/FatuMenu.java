package com.sagui.model.container.menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.sagui.model.FatuContainer;
import com.sagui.model.action.FatuFireActionListenerClosure;
import com.sagui.model.action.IFatuActionListener;
import com.sagui.model.action.IFatuActionListenerFeature;
import com.sagui.model.feature.FatuOrientation;
import com.sagui.model.feature.FatuSize;
import com.sagui.model.feature.IFatuOrientationFeature;
import com.sagui.model.feature.IFatuSizeFeature;

public class FatuMenu<T extends FatuMenuItem> extends FatuContainer<T> implements IFatuOrientationFeature, IFatuSizeFeature, IFatuActionListenerFeature<FatuMenuActionEvent> {
	
	
	private final List<IFatuActionListener<FatuMenuActionEvent>> actionListeners;
	private FatuSize size;
	private FatuOrientation orientation;

	public FatuMenu(List<T> menuItems) {
		this.actionListeners = new ArrayList<>();
		this.internalAddItems(menuItems);
	}
	
	@SafeVarargs
	public FatuMenu(T ...menuItems) {
		this(Arrays.asList(menuItems));
	}
	
	
	@Override
	public void setSize(FatuSize size) {
		this.size = size; 
	}

	@Override
	public FatuSize getSize() {
		return this.size;
	}
	
	@Override
	public FatuOrientation getOrientation() {
		return orientation;
	}
	
	@Override
	public void setOrientation(FatuOrientation orientation) {
		this.orientation = orientation;
	}
	
	@Override
	public void addActionListener(IFatuActionListener<FatuMenuActionEvent> listener) {
		this.actionListeners.add(listener);
	}

    @Override
	public boolean removeActionListener(IFatuActionListener<FatuMenuActionEvent> listener) {
		return this.actionListeners.remove(listener);
	}

    @Override
	public Collection<IFatuActionListener<FatuMenuActionEvent>> getActionListeners() {
		return Collections.unmodifiableCollection(actionListeners);
	}

    @Override
	public void fireActionListeners(FatuMenuActionEvent evt) {
		CollectionUtils.forAllDo(actionListeners, new FatuFireActionListenerClosure<>(evt));
	}

	private void internalAddItems(List<T> menuItems) {
		for (T mItem : menuItems) {
			this.addChild(mItem);
		}
	}
	
}
