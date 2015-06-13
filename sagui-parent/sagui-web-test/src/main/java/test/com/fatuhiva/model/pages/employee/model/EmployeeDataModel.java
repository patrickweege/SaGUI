package test.com.fatuhiva.model.pages.employee.model;

import java.text.NumberFormat;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;

import org.apache.commons.lang.ObjectUtils;

import test.com.fatuhiva.model.pages.crud.employee.Employee;
import test.com.fatuhiva.model.pages.employee.crud.EmployeeDAO;

import com.fatuhiva.model.datamodel.impl.FatuDefaultDatasetTableModel;
import com.tuamotu.commons.comparator.BeanComparatorUtil;
import com.tuamotu.commons.comparator.IBeanComparator;
import com.tuamotu.commons.comparator.IBeanComparator.Order;
import com.tuamotu.commons.comparator.IFieldComparatorMetadata;
import com.tuamotu.commons.dataset.Dataset;
import com.tuamotu.commons.dataset.DatasetIndex;
import com.tuamotu.commons.dataset.IBookmark;
import com.tuamotu.commons.dataset.IDataset;
import com.tuamotu.commons.field.BeanFieldHelper;
import com.tuamotu.commons.field.FormaterField;
import com.tuamotu.commons.field.I18nFieldImpl;
import com.tuamotu.commons.field.IField;
import com.tuamotu.commons.i18n.I18n;

public class EmployeeDataModel extends FatuDefaultDatasetTableModel<Employee> {

    private static final long serialVersionUID = 1L;

    public final IField<Employee> selectField;

    public final IField<Employee> empIDField;
    public final IField<Employee> empNameField;
    public final IField<Employee> empSalaryField;

    private final I18n tBoxID_I18N;
    private final I18n tBoxName_I18N;
    private final I18n tBoxSalary_I18N;

    private final EmployeeDAO employeeDAO;

    public EmployeeDataModel() {
        super(new Dataset<Employee>(), null);

        this.employeeDAO = new EmployeeDAO();

        this.tBoxID_I18N = new I18n("tBoxID_I18N");
        this.tBoxID_I18N.setDefault("Employee ID");
        this.tBoxID_I18N.setTranslation(Locale.ENGLISH, "Employee ID");
        this.tBoxID_I18N.setTranslation(Locale.GERMAN, "Mitarbeiter ID");

        this.tBoxName_I18N = new I18n("tBoxName_I18N");
        this.tBoxName_I18N.setDefault("Employee Name");
        this.tBoxName_I18N.setTranslation(Locale.ENGLISH, "Employee Name");
        this.tBoxName_I18N.setTranslation(Locale.GERMAN, "Mitarbeiter Name");

        this.tBoxSalary_I18N = new I18n("tBoxSalary_I18N");
        this.tBoxSalary_I18N.setDefault("Salary");
        this.tBoxSalary_I18N.setTranslation(Locale.ENGLISH, "Salary");
        this.tBoxSalary_I18N.setTranslation(Locale.GERMAN, "Verdienst");

        Dataset<Employee> theDataset = (Dataset<Employee>) this.getDataset();

        this.selectField = theDataset.addField("SELECT", String.class);
        this.addField(selectField);

        IField<Employee> empField;

        empField = BeanFieldHelper.getField("id", Employee.class);
        empField = new FormaterField<Employee>(empField, NumberFormat.getIntegerInstance());
        empField = new I18nFieldImpl<Employee>("id", tBoxID_I18N, tBoxID_I18N, empField);
        this.empIDField = theDataset.addField(empField);
        this.addField(empIDField);

        empField = BeanFieldHelper.getField("nome", Employee.class);
        empField = new I18nFieldImpl<Employee>("nome", tBoxName_I18N, tBoxName_I18N, empField);
        this.empNameField = theDataset.addField(empField);
        this.addField(empNameField);

        empField = BeanFieldHelper.getField("salary", Employee.class);
        empField = new FormaterField<Employee>(empField, NumberFormat.getNumberInstance());
        empField = new I18nFieldImpl<Employee>("salary", tBoxSalary_I18N, tBoxSalary_I18N, empField);
        this.empSalaryField = theDataset.addField(empField);
        this.addField(empSalaryField);

        IBeanComparator<Employee> empIDComparator = BeanComparatorUtil.getBeanComparator(new IFieldComparatorMetadata<Employee>(empIDField, Order.ASC, true));
        DatasetIndex<Employee> theIndex = theDataset.addIndex(empIDComparator);
        super.setDatasetIndex(theIndex);
    }

    public boolean isSelected(int row) {
        Boolean b = getValueAt(row, selectField);
        return (Boolean) ObjectUtils.defaultIfNull(b, Boolean.FALSE);
    }

    public void reload() {
        Collection<Employee> data = employeeDAO.getAll();
        super.setData(data, false);
    }

    public IBookmark<Employee> createNewEmployee() {
        Employee newEmp = employeeDAO.getNew();
        return this.insert(newEmp);
    }

    public void saveAll() {
        IDataset<Employee> theDataset = this.getDataset();
        DatasetIndex<Employee> theIndex = this.getDatasetIndex();
        Iterator<Employee> dataIter = theDataset.getIterator(theIndex);
        while (dataIter.hasNext()) {
            Employee emp = dataIter.next();
            employeeDAO.save(emp);
        }
    }

    public void save(IBookmark<Employee> toSave) {
        IDataset<Employee> theDataset = this.getDataset();
        Employee empToSave = theDataset.getRow(toSave);
        this.employeeDAO.save(empToSave);
    }

    public void delete(IBookmark<Employee> toDelete) {
        IDataset<Employee> theDataset = this.getDataset();
        Employee empToDelete = theDataset.getRow(toDelete);
        this.employeeDAO.delete(empToDelete.getId());
    }

    public IBookmark<Employee> findByID(long toFind) {
        Employee example = new Employee();
        example.setId(toFind);
        IBookmark<Employee> found = getDataset().findFirst(example, super.getDatasetIndex());
        return found;
    }
    
    

}
