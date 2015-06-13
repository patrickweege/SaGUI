package com.fatuhiva.model.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.fatuhiva.model.FatuComponent;
import com.fatuhiva.model.FatuContainer;
import com.tuamotu.commons.visitor.HierarchicalVisitor;
import com.tuamotu.commons.visitor.VisitorDirection;

public class FatuCollectAllChildrenVisitor extends HierarchicalVisitor<FatuComponent> {

    private final List<FatuComponent> selected;

    public FatuCollectAllChildrenVisitor() {
        super(VisitorDirection.TO_BOTTON);
        selected = new ArrayList<FatuComponent>();
    }

    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected Collection<FatuComponent> getChildren(FatuComponent parent) {
        if (parent instanceof FatuContainer) {
            return ((FatuContainer) parent).getChildren();
        }
        return Collections.emptyList();
    }

    @Override
    protected FatuContainer<?> getParent(FatuComponent child) {
        return child.getParent();
    }

    @Override
    protected boolean acceptParent(FatuComponent parent) {
        selected.add(parent);
        return true;
    }

    @Override
    protected boolean acceptChild(FatuComponent child) {
        selected.add(child);
        return true;
    }

    public List<FatuComponent> getSelected() {
        return selected;
    }

}
