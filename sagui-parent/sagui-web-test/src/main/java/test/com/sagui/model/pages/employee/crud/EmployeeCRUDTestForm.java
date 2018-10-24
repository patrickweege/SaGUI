package test.com.sagui.model.pages.employee.crud;

import java.beans.PropertyChangeEvent;
import java.text.NumberFormat;
import java.util.Collection;
import java.util.Locale;

import org.apache.commons.lang.math.NumberUtils;

import test.com.sagui.model.pages.employee.bean.Employee;
import test.com.sagui.model.pages.employee.dao.EmployeeDAO;
import test.com.sagui.model.pages.employee.model.EmployeeTableModel;

import com.sagui.dataset.commons.dataset.IBookmark;
import com.sagui.dataset.commons.field.BeanFieldHelper;
import com.sagui.dataset.commons.field.FormaterField;
import com.sagui.dataset.commons.field.I18nFieldImpl;
import com.sagui.dataset.commons.field.IField;
import com.sagui.dataset.commons.i18n.CurrentLanguageResolver;
import com.sagui.dataset.commons.i18n.I18n;
import com.sagui.forms.FatuPropertyChangedEventHandler;
import com.sagui.forms.crud.FatuAbstractCrudForm;
import com.sagui.model.container.panel.FatuPanel;
import com.sagui.model.datasource.FatuBookmarkModelDataSource;
import com.sagui.model.editable.editbox.FatuTextBox;
import com.sagui.model.feature.FatuSize;
import com.sagui.model.grid.FatuGrid;
import com.sagui.model.layout.auto.FatuAutoLayoutRule;
import com.sagui.model.selection.IFatuSelectionListener;
import com.sagui.model.selection.IFatuSelectionModel;

public class EmployeeCRUDTestForm extends FatuAbstractCrudForm<Employee> {

    private static final I18n tBoxID_I18N;
    private static final I18n tBoxID_Error_I18;
    private static final I18n tBoxName_I18N;
    private static final I18n tBoxSalary_I18N;
    static {
        tBoxID_I18N = new I18n("tBoxID_I18N");
        tBoxID_I18N.setTranslation(CurrentLanguageResolver.getInstance().getCurrentLocale(), "Employee ID");
        tBoxID_I18N.setTranslation(Locale.ENGLISH, "Employee ID");
        tBoxID_I18N.setTranslation(Locale.GERMAN, "Mitarbeiter ID");

        tBoxID_Error_I18 = new I18n("tBoxID_Error_I18");
        tBoxID_Error_I18.setDefault("N�o � um Numero v�lido");

        tBoxName_I18N = new I18n("tBoxName_I18N");
        tBoxName_I18N.setTranslation(CurrentLanguageResolver.getInstance().getCurrentLocale(), "Employee Name");
        tBoxName_I18N.setTranslation(Locale.ENGLISH, "Employee Name");
        tBoxName_I18N.setTranslation(Locale.GERMAN, "Mitarbeiter Name");

        tBoxSalary_I18N = new I18n("tBoxSalary_I18N");
        tBoxSalary_I18N.setTranslation(CurrentLanguageResolver.getInstance().getCurrentLocale(), "Salary");
        tBoxSalary_I18N.setTranslation(Locale.ENGLISH, "Salary");
        tBoxSalary_I18N.setTranslation(Locale.GERMAN, "Verdienst");
    }

    private final FatuTextBox tBoxID;
    private final FatuTextBox tBoxName;
    private final FatuTextBox tBoxSalary;

    private final FatuGrid tableEmployee;
    private final EmployeeTableModel dataModel;

    private final FatuBookmarkModelDataSource<Employee, String> dsEmpID;
    private final FatuBookmarkModelDataSource<Employee, String> dsEmpName;
    private final FatuBookmarkModelDataSource<Employee, String> dsEmpSalary;

    private final IField<Employee> empIDField;
    private final IField<Employee> empNameField;
    private final IField<Employee> empSalaryField;

    private final EmployeeDAO dao;

    public EmployeeCRUDTestForm() {
        super();
        FatuPanel content = getContentPanel();

        this.dao = new EmployeeDAO();

        IField<Employee> empField;
        empField = BeanFieldHelper.getField("id", Employee.class);
        empField = new FormaterField<Employee>(empField, NumberFormat.getIntegerInstance());
        this.empIDField = new I18nFieldImpl<Employee>("id", tBoxID_I18N, tBoxID_I18N, empField);

        empField = BeanFieldHelper.getField("nome", Employee.class);
        this.empNameField = new I18nFieldImpl<Employee>("nome", tBoxName_I18N, tBoxName_I18N, empField);

        empField = BeanFieldHelper.getField("salary", Employee.class);
        empField = new FormaterField<Employee>(empField, NumberFormat.getNumberInstance());
        this.empSalaryField = new I18nFieldImpl<Employee>("salary", tBoxSalary_I18N, tBoxSalary_I18N, empField);

        this.dataModel = new EmployeeTableModel();
        this.dsEmpID = new FatuBookmarkModelDataSource<Employee, String>(dataModel, null, dataModel.empIDField);
        this.dsEmpID.setEditable(false);
        this.dsEmpName = new FatuBookmarkModelDataSource<Employee, String>(dataModel, null, dataModel.empNameField);
        this.dsEmpSalary = new FatuBookmarkModelDataSource<Employee, String>(dataModel, null, dataModel.empSalaryField);

        this.tBoxID = new FatuTextBox(dsEmpID);
        this.tBoxID.setName("tBoxID");
        tBoxID.setLabel(tBoxID_I18N);
        content.addChild(tBoxID, FatuAutoLayoutRule.AUTO_LAYOUT_RULE);
        tBoxID.addPropertyChangeListener(super.getDefaultPropertyChangeListener());

        this.tBoxName = new FatuTextBox(dsEmpName);
        this.tBoxName.setName("tBoxName");
        tBoxName.setLabel(tBoxName_I18N);
        content.addChild(tBoxName, FatuAutoLayoutRule.AUTO_LAYOUT_RULE);
        tBoxName.addPropertyChangeListener(super.getDefaultPropertyChangeListener());

        this.tBoxSalary = new FatuTextBox(dsEmpSalary);
        tBoxSalary.setLabel(tBoxSalary_I18N);
        content.addChild(tBoxSalary, FatuAutoLayoutRule.AUTO_LAYOUT_RULE);

        tableEmployee = new FatuGrid(dataModel);
        //tableEmployee.setSize(new FatuSize(300, 400));
        content.addChild(tableEmployee, FatuAutoLayoutRule.AUTO_LAYOUT_RULE);

        IFatuSelectionModel<IBookmark<Employee>> selectionModel = tableEmployee.getSelectionModel();
        selectionModel.addSelectionListener(new TableEmployeeSelectionListener());

        //this.setSize(new FatuSize(500, 500));
        
        populate();

    }

    @Override
    protected void doCreate() {
        IBookmark<Employee> created = dataModel.createNewEmployee();
        this.setCurrent(created);
    }

    @Override
    protected void doDelete() {
        IBookmark<Employee> toDelete = getCurrent();
        this.setCurrent(null);
        dataModel.delete(toDelete);
    }

    @Override
    protected void doSave() {
        dataModel.save(getCurrent());
        populate();
    }

    @Override
    protected boolean isCanSave() {
        return true;
    }

    @Override
    protected void doSearch(String value) {
        if (NumberUtils.isDigits(value)) {
            long toSearch = NumberUtils.toLong(value);
            IBookmark<Employee> found = dataModel.findByID(toSearch);
            this.setCurrent(found);
        }
    }

    @Override
    protected void setCurrent(IBookmark<Employee> current) {
        super.setCurrent(current);
        this.dsEmpID.setCurrent(this.getCurrent());
        this.dsEmpName.setCurrent(this.getCurrent());
        this.dsEmpSalary.setCurrent(this.getCurrent());
    }

    private void populate() {
        dataModel.reload();

    }

    /**
     * Requisitos:<br>
     * 1 - Sempre converter o nome em maiusculas
     * 
     * @param evt
     */
    @FatuPropertyChangedEventHandler(components = "tBoxName", properties = "value")
    public void tBoxNameValueChanged(PropertyChangeEvent evt) {
        String newValue = (String) evt.getNewValue();
        if (newValue != null) {
            String nValue = (String) evt.getNewValue();
            nValue = nValue.toUpperCase();
            dsEmpName.setValue(nValue);
        }
    }

    private class TableEmployeeSelectionListener implements IFatuSelectionListener<IBookmark<Employee>> {

        @Override
        public void selectionChanged(Collection<IBookmark<Employee>> newItems, Collection<IBookmark<Employee>> oldItems) {
            if (newItems.size() == 1) {
                IBookmark<Employee> next = newItems.iterator().next();
                EmployeeCRUDTestForm.this.setCurrent(next);
            } else {
                EmployeeCRUDTestForm.this.setCurrent(null);
            }
        }
    }
}
