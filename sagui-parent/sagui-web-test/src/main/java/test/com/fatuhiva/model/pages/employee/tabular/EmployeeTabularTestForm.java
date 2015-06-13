package test.com.fatuhiva.model.pages.employee.tabular;

import java.util.ArrayList;
import java.util.List;

import test.com.fatuhiva.model.pages.crud.employee.Employee;
import test.com.fatuhiva.model.pages.employee.model.EmployeeDataModel;

import com.fatuhiva.forms.tabular.FatuAbstractTabularForm;
import com.fatuhiva.model.FatuComponent;
import com.fatuhiva.model.action.IFatuActionEvent;
import com.fatuhiva.model.datamodel.FatuAbstractTableModel;
import com.fatuhiva.model.datasource.FatuAbstractDataSource;
import com.fatuhiva.model.datasource.FatuRowIndexDataModelDataSource;
import com.fatuhiva.model.editable.editbox.FatuTextBox;
import com.fatuhiva.model.editable.list.checkbox.FatuCheckbox;
import com.tuamotu.commons.dataset.IBookmark;
import com.tuamotu.commons.field.IField;

public class EmployeeTabularTestForm extends FatuAbstractTabularForm {

    private final EmployeeDataModel dataModel;

    public EmployeeTabularTestForm() {
        this.dataModel = new EmployeeDataModel();
        this.dataModel.reload();
        super.createUI();
    }

    @Override
    public void btnNewOnClick(IFatuActionEvent evt) {
        this.dataModel.createNewEmployee();
    }

    @Override
    public void btnSaveOnClick(IFatuActionEvent evt) {
        this.dataModel.saveAll();
    }

    @Override
    public void btnDeleteOnClick(IFatuActionEvent evt) {
        List<IBookmark<Employee>> toRemoveList = new ArrayList<IBookmark<Employee>>();
        for (int row = 0; row < dataModel.getRowCount(); row++) {
            Boolean value = dataModel.isSelected(row);
            if (value != null && value == true) {
                IBookmark<Employee> emp = dataModel.getBookmark(row);
                toRemoveList.add(emp);
            }
        }
        for (IBookmark<Employee> toRemove : toRemoveList) {
            this.dataModel.remove(toRemove);
        }
    }

    @Override
    protected <V> FatuComponent getCellComponent(int row, int col, FatuAbstractDataSource<V> datasource) {
        IField<Employee> theField = dataModel.getColumn(col);
        if (theField == dataModel.selectField) {
            FatuAbstractDataSource<Boolean> ds = new FatuRowIndexDataModelDataSource<Employee, Boolean>(dataModel, row, theField);
            FatuCheckbox ckBox = new FatuCheckbox(ds);
            return ckBox;
        } else {
            FatuAbstractDataSource<String> ds = new FatuRowIndexDataModelDataSource<Employee, String>(dataModel, row, theField);
            return new FatuTextBox(ds);
        }
    }

    @Override
    protected <T> FatuAbstractTableModel<T> getDataModel() {
        return (FatuAbstractTableModel<T>) this.dataModel;
    }

    @Override
    protected FatuComponent getColumnHeader(int i) {
        return null;
    }

}
