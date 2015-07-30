package com.sagui.model.grid;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sagui.dataset.commons.field.IField;
import com.sagui.model.FatuComponent;
import com.sagui.model.datamodel.IFatuCellRender;
import com.sagui.model.datamodel.IFatuColumnModel;
import com.sagui.model.datamodel.IFatuTableModel;

class FatuFieldColumnModel implements IFatuColumnModel {

    private static final IFatuCellRender DEFAULT_CELL_RENDER = new InternalDefaultCellRender();

    private final List<IField<?>> fields;
    private final Map<IField<?>, IFatuCellRender> cellRenders;
    private final Set<IField<?>> visibleFields;

    private IFatuCellRender defaultCellRender;

    public FatuFieldColumnModel(IFatuTableModel<?> tableModel) {
        this.fields = new ArrayList<IField<?>>();
        this.cellRenders = new HashMap<IField<?>, IFatuCellRender>();
        this.defaultCellRender = new InternalDefaultCellRender();
        this.visibleFields = new HashSet<IField<?>>();
        for (int i = 0; i < tableModel.getColumnCount(); i++) {
            IField<?> theField = tableModel.getColumn(i);
            this.fields.add(theField);
            this.cellRenders.put(theField, DEFAULT_CELL_RENDER);
            this.visibleFields.add(theField);
        }
    }

    @Override
    public int getColumnCount() {
        return fields.size();
    }

    @Override
    public String getColumnName(int columnIndex) {
        IField<?> iField = fields.get(columnIndex);
        return iField.getName();
    }

    @Override
    public String getColumnLabel(int columnIndex) {
        IField<?> iField = fields.get(columnIndex);
        return iField.getLabel();
    }

    @Override
    public String getColumnHint(int columnIndex) {
        IField<?> iField = fields.get(columnIndex);
        return iField.getDescription();
    }

    @Override
    public IFatuCellRender getCellRenderer(int columnIndex) {
        IField<?> field = fields.get(columnIndex);
        IFatuCellRender cellRender = cellRenders.get(field);
        return cellRender == null ? defaultCellRender : cellRender;
    }

    public void setCellRenderer(int columnIndex, IFatuCellRender cellRender) {
        IField<?> iField = fields.get(columnIndex);
        this.cellRenders.put(iField, cellRender);
    }

    @Override
    public boolean isVisible(int columIndex) {
        IField<?> theField = fields.get(columIndex);
        return visibleFields.contains(theField);
    }

    @Override
    public void setVisible(boolean visible, int columIndex) {
        IField<?> theField = fields.get(columIndex);
        if (visible) {
            this.visibleFields.add(theField);
        } else {
            this.visibleFields.remove(theField);
        }
    }

    private static final class InternalDefaultCellRender implements IFatuCellRender {

        @Override
        public Color getColor(FatuComponent component, int row, int col, Object value) {
            return null;
        }

        @Override
        public Color getbackgroundColor(FatuComponent component, int row, int col, Object value) {
            return null;
        }

    }

}
