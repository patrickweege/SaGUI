package test.com.fatuhiva.model.pages.employee.tabular;

import com.fatuhiva.model.container.page.FatuPage;
import com.fatuhiva.model.layout.border.FatuBorderLayout;
import com.fatuhiva.model.layout.border.FatuBorderLayoutRule;
import com.fatuhiva.model.layout.border.FatuRegion;

/**
 * test.com.jext.model.pages.crud.CrudTestPage
 * 
 * @author F0FP250
 * 
 */
public class TabularTestPage extends FatuPage<FatuBorderLayout> {

    private EmployeeTabularTestForm form1;

    public TabularTestPage() {
        super(new FatuBorderLayout());
    }

    @Override
    protected void init() {
        form1 = new EmployeeTabularTestForm();
        addChild(form1, new FatuBorderLayoutRule(FatuRegion.CENTER, false));
    }

    @Override
    public String getTitle() {
        return getClass().getName();
    }

}
