package com.sagui.ext.common.render.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class ListDiference<E> {

	private final Comparator<E> comparator;
	private final List<E> newList;
	private final List<E> oldList;

	private final List<DifferenceOperation<E>> operations;

	private ListDiference(List<E> oldList, List<E> newList, Comparator<E> comparator) {
		this.oldList = Collections.unmodifiableList(oldList);
		this.newList = Collections.unmodifiableList(newList);
		this.comparator = comparator;

		this.operations = new ArrayList<DifferenceOperation<E>>();
	}

	public static <T> ListDiference<T> getDiference(List<T> oldList, List<T> newList, Comparator<T> comparator) {
		ListDiference<T> ld = new ListDiference<T>(oldList, newList, comparator);
		ld.checkDiference();
		return ld;
	}

	private void checkDiference() {
		Set<E> tempSet = new TreeSet<E>(comparator);
		tempSet.addAll(newList);
		for (E elem : oldList) {
			if (!tempSet.contains(elem)) {
				operations.add(DifferenceOperation.getDeleteCommand(elem));
			}
		}

		tempSet.clear();
		tempSet.addAll(oldList);
		int index = 0;
		for (E elem : newList) {
			if (!tempSet.contains(elem)) {
				operations.add(DifferenceOperation.getInsertCommand(elem, index));
			}
			index++;
		}
	}


}
