package com.sagui.model.button;

import java.awt.Insets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.sagui.dataset.commons.i18n.I18n;
import com.sagui.model.FatuComponent;
import com.sagui.model.action.FatuFireActionListenerClosure;
import com.sagui.model.action.IFatuActionListener;
import com.sagui.model.action.IFatuActionListenerFeature;
import com.sagui.model.feature.IFatuEnabledFeature;
import com.sagui.model.feature.IFatuHintFeature;
import com.sagui.model.feature.IFatuLabelFeature;
import com.sagui.model.feature.IFatuMarginFeature;
import com.sagui.model.feature.IFatuVisibleFeature;

public class FatuButton extends FatuComponent implements IFatuHintFeature, IFatuLabelFeature, IFatuActionListenerFeature<FatuButtonClickEvent>, IFatuEnabledFeature, IFatuVisibleFeature, IFatuMarginFeature {

	private final List<IFatuActionListener<FatuButtonClickEvent>> actionListeners;
	
	private I18n label;
	private I18n hint;
    private boolean enabled;
    private boolean visible;

    private Insets margin;
	
	public FatuButton() {
		super();
		this.actionListeners = new ArrayList<IFatuActionListener<FatuButtonClickEvent>>();
		this.visible = true;
		this.enabled = true;
	}
	
	@Override
	public void setLabel(I18n label) {
    	Object oldValue = this.getLabel();
		this.label = label;
    	Object newValue = this.getLabel();
    	
    	super.getPropertyChangeSupport().firePropertyChange(IFatuLabelFeature.LABEL_PROPERTY, oldValue, newValue);
	}
	
	@Override
	public I18n getLabel() {
		return label;
	}
	
	@Override
	public void setHint(I18n hintText) {
    	Object oldValue = this.getHint();
		this.hint = hintText;
    	Object newValue = this.getHint();
    	
    	super.getPropertyChangeSupport().firePropertyChange(IFatuHintFeature.HINT_PROPERTY, oldValue, newValue);
	}
	
	@Override
	public I18n getHint() {
		return hint;
	}

	@Override
	public void addActionListener(IFatuActionListener<FatuButtonClickEvent> listener) {
		this.actionListeners.add(listener);
	}

    @Override
	public boolean removeActionListener(IFatuActionListener<FatuButtonClickEvent> listener) {
		return this.actionListeners.remove(listener);
	}

    @Override
	public Collection<IFatuActionListener<FatuButtonClickEvent>> getActionListeners() {
		return Collections.unmodifiableCollection(actionListeners);
	}

    @Override
	public void fireActionListeners(FatuButtonClickEvent evt) {
		CollectionUtils.forAllDo(actionListeners, new FatuFireActionListenerClosure<FatuButtonClickEvent>(evt));
	}
    
    public void click() {
        this.fireActionListeners(new FatuButtonClickEvent(this));
    }

    @Override
    public void setEnabled(boolean enabled) {
    	Object oldValue = this.isEnabled();
        this.enabled = enabled;
    	Object newValue = this.isEnabled();
    	
    	super.getPropertyChangeSupport().firePropertyChange(IFatuEnabledFeature.ENABLED_PROPERTY, oldValue, newValue);
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public void setVisible(boolean visible) {
    	Object oldValue = this.isVisible();
        this.visible = visible;
    	Object newValue = this.isVisible();
    	
    	super.getPropertyChangeSupport().firePropertyChange(IFatuVisibleFeature.VISIBLE_PROPERTY, oldValue, newValue);
    }

    @Override
    public boolean isVisible() {
        return this.visible;
    }
    
    @Override
    public void setMargins(Insets margin) {
    	Object oldValue = this.getMargins();
        this.margin = margin;
    	Object newValue = this.getMargins();
    	
    	super.getPropertyChangeSupport().firePropertyChange(IFatuMarginFeature.MARGINS_PROPERTY, oldValue, newValue);
    }
    
    @Override
    public Insets getMargins() {
        return this.margin;
    }

}
