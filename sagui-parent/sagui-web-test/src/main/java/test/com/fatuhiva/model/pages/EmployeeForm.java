package test.com.fatuhiva.model.pages;

import java.text.NumberFormat;
import java.util.Locale;

import test.com.fatuhiva.model.pages.crud.employee.Employee;

import com.fatuhiva.model.container.form.FatuForm;
import com.fatuhiva.model.datasource.FatuBeanDataSource;
import com.fatuhiva.model.editable.editbox.FatuTextBox;
import com.fatuhiva.model.feature.FatuSize;
import com.fatuhiva.model.layout.auto.FatuAutoLayout;
import com.fatuhiva.model.layout.auto.FatuAutoLayoutRule;
import com.sagui.dataset.commons.field.BeanFieldHelper;
import com.sagui.dataset.commons.field.FormaterField;
import com.sagui.dataset.commons.field.IField;
import com.sagui.dataset.commons.i18n.CurrentLanguageResolver;
import com.sagui.dataset.commons.i18n.I18n;

/**
 * test.com.jext.model.pages.button.EmployeeForm
 * @author F0FP250
 *
 */
public class EmployeeForm extends FatuForm<FatuAutoLayout> {

    private final FatuTextBox tBoxID;
    private final FatuTextBox tBoxName;
    private final FatuTextBox tBoxSalary;

    private final Employee employee;
///
    private final NumberFormat INTEGER_FORMATTER;
    public EmployeeForm() {
        super(FatuAutoLayout.AUTO_LAYOUT);

        INTEGER_FORMATTER = NumberFormat.getIntegerInstance();
        INTEGER_FORMATTER.setParseIntegerOnly(true);
        
        
        this.setName("Employee Form");
        this.setTitle("Employee Form");
        this.setSize(new FatuSize(300,150));

        this.employee = new Employee();
        {
            IField<Employee> empIDField = BeanFieldHelper.getField("id", Employee.class);
            empIDField = new FormaterField<Employee>(empIDField, NumberFormat.getIntegerInstance());
            FatuBeanDataSource<Employee, String> dsEmpID = new FatuBeanDataSource<Employee, String>(empIDField, employee);
            this.tBoxID = new FatuTextBox(dsEmpID);
            I18n i18 = new I18n(tBoxID.getId());
            i18.setTranslation(CurrentLanguageResolver.getInstance().getCurrentLocale(), "Employee ID");
            i18.setTranslation(Locale.ENGLISH, "Employee ID");
            i18.setTranslation(Locale.GERMAN, "Mitarbeiter ID");
            tBoxID.setLabel(i18);
            
            this.addChild(tBoxID,FatuAutoLayoutRule.AUTO_LAYOUT_RULE);
        }
        {
            IField<Employee> empNameField = BeanFieldHelper.getField("nome", Employee.class);
            FatuBeanDataSource<Employee, String> dsEmpName = new FatuBeanDataSource<Employee, String>(empNameField, employee);
            this.tBoxName = new FatuTextBox(dsEmpName);
            I18n i18 = new I18n(tBoxName.getId());
            i18.setTranslation(CurrentLanguageResolver.getInstance().getCurrentLocale(), "Employee Name");
            i18.setTranslation(Locale.ENGLISH, "Employee Name");
            i18.setTranslation(Locale.GERMAN, "Mitarbeiter Name");
            tBoxName.setLabel(i18);
            this.addChild(tBoxName,FatuAutoLayoutRule.AUTO_LAYOUT_RULE);
        }
        {
            IField<Employee> empSalaryField = BeanFieldHelper.getField("salary", Employee.class);
            empSalaryField = new FormaterField<Employee>(empSalaryField, NumberFormat.getNumberInstance());
            FatuBeanDataSource<Employee, String> dsEmpSalary = new FatuBeanDataSource<Employee, String>(empSalaryField, employee);
            this.tBoxSalary = new FatuTextBox(dsEmpSalary);
            I18n i18 = new I18n(tBoxSalary.getId());
            i18.setTranslation(CurrentLanguageResolver.getInstance().getCurrentLocale(), "Salary");
            i18.setTranslation(Locale.ENGLISH, "Salary");
            i18.setTranslation(Locale.GERMAN, "Salary");
            tBoxSalary.setLabel(i18);

            this.addChild(tBoxSalary,FatuAutoLayoutRule.AUTO_LAYOUT_RULE);
        }

    }
}
