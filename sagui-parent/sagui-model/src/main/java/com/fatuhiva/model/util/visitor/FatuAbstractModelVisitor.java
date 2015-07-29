package com.fatuhiva.model.util.visitor;

import java.util.Collection;
import java.util.Collections;

import org.apache.commons.collections4.Closure;
import org.apache.commons.collections4.Predicate;

import com.fatuhiva.model.FatuComponent;
import com.fatuhiva.model.FatuContainer;
import com.sagui.dataset.commons.visitor.PredicatedHierarchicalVisitor;
import com.sagui.dataset.commons.visitor.VisitorDirection;

public class FatuAbstractModelVisitor extends PredicatedHierarchicalVisitor<FatuComponent> {

    public FatuAbstractModelVisitor(Predicate acceptPredicate, Closure acceptClosure, VisitorDirection... direction) {
        super(acceptPredicate, acceptClosure, direction);
    }

    @Override
    protected Collection<FatuComponent> getChildren(FatuComponent parent) {
        if (parent instanceof FatuContainer) {
            FatuContainer<FatuComponent> container = (FatuContainer<FatuComponent>) parent;
            return container.getChildren();
        }
        return Collections.emptyList();
    }

    @Override
    protected FatuComponent getParent(FatuComponent child) {
        return child.getParent();
    }

}
