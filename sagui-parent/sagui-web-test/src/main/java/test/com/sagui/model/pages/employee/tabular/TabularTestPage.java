package test.com.sagui.model.pages.employee.tabular;

import com.sagui.model.container.page.FatuPage;
import com.sagui.model.layout.border.FatuBorderLayout;
import com.sagui.model.layout.border.FatuBorderLayoutRule;
import com.sagui.model.layout.border.FatuRegion;

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
