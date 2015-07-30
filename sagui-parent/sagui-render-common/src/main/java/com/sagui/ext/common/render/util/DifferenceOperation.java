package com.sagui.ext.common.render.util;

public class DifferenceOperation<E> {

	public static final Object INSERT = new Object();
	public static final Object DELETE = new Object();

	private E element;
	private Object operation;
	private Integer position;

	public static <E> DifferenceOperation<E> getDeleteCommand(E element) {
		DifferenceOperation<E> cmd = new DifferenceOperation<E>();
		cmd.setElement(element);
		cmd.setOperation(DifferenceOperation.DELETE);
		return cmd;
	}

	public static <E> DifferenceOperation<E> getInsertCommand(E element, Integer position) {
		DifferenceOperation<E> cmd = new DifferenceOperation<E>();
		cmd.setElement(element);
		cmd.setOperation(DifferenceOperation.INSERT);
		cmd.setPosition(position);
		return cmd;
	}

	public Object getOperation() {
		return this.operation;
	}

	public E getElement() {
		return this.element;
	}

	public Integer getPosition() {
		return this.position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	private void setOperation(Object operation) {
		this.operation = operation;
	}

	private void setElement(E element) {
		this.element = element;
	}

}
