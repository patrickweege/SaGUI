package com.fatuhiva.model.button;

import java.awt.Insets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.apache.commons.collections.CollectionUtils;

import com.fatuhiva.model.FatuComponent;
import com.fatuhiva.model.action.IFatuActionEvent;
import com.fatuhiva.model.action.IFatuActionListener;
import com.fatuhiva.model.action.IFatuActionListenerFeature;
import com.fatuhiva.model.feature.IFatuEnabledFeature;
import com.fatuhiva.model.feature.IFatuHintFeature;
import com.fatuhiva.model.feature.IFatuLabelFeature;
import com.fatuhiva.model.feature.IFatuMarginFeature;
import com.fatuhiva.model.feature.IFatuVisibleFeature;
import com.sagui.dataset.commons.i18n.I18n;

public class FatuButton extends FatuComponent implements IFatuHintFeature, IFatuLabelFeature, IFatuActionListenerFeature, IFatuEnabledFeature, IFatuVisibleFeature, IFatuMarginFeature {

	private final ArrayList<IFatuActionListener> actionListeners;
	
	private I18n label;
	private I18n hint;
    private boolean enabled;
    private boolean visible;

    private Insets margin;
	
	public FatuButton() {
		super();
		this.actionListeners = new ArrayList<IFatuActionListener>();
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
	public void addActionListener(IFatuActionListener listener) {
		this.actionListeners.add(listener);
	}

    @Override
	public boolean removeActionListener(IFatuActionListener listener) {
		return this.actionListeners.remove(listener);
	}

    @Override
	public Collection<IFatuActionListener> getActionListeners() {
		return Collections.unmodifiableCollection(actionListeners);
	}

    @Override
	public void fireActionListeners(IFatuActionEvent evt) {
		CollectionUtils.forAllDo(actionListeners, new FatuFireActionListenerClosure(evt));
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
