package test.com.sagui.model.pages;

import com.sagui.model.container.form.FatuForm;
import com.sagui.model.container.page.FatuPage;
import com.sagui.model.feature.FatuSize;
import com.sagui.model.layout.auto.FatuAutoLayout;
import com.sagui.model.layout.border.FatuBorderLayout;
import com.sagui.model.layout.border.FatuBorderLayoutRule;
import com.sagui.model.layout.border.FatuRegion;
import com.sagui.model.layout.box.FatuHBoxLayout;
import com.sagui.model.layout.box.FatuHBoxLayoutRule;
import com.sagui.model.layout.box.FatuVBoxLayout;
import com.sagui.model.layout.box.FatuVBoxLayoutRule;
/**
 *  test.com.jext.model.pages.BorderLayoutTestPage
 * @author F0FP250
 *
 */
public class BorderLayoutTestPage extends FatuPage<FatuBorderLayout> {

    private FatuForm<FatuHBoxLayout> form1;
    private FatuForm<FatuVBoxLayout> form2;
    private EmployeeForm employeeForm;

    public BorderLayoutTestPage() {
        super(new FatuBorderLayout());
    }
    
    @Override
    protected void init() {
        this.employeeForm = new EmployeeForm();
        addChild(employeeForm, new  FatuBorderLayoutRule(FatuRegion.CENTER, false));

        this.form1 = new FatuForm<FatuHBoxLayout>(FatuHBoxLayout.HBOX_DEFAULT);
        this.form1.setName("form1");
        this.form1.setTitle("Form 1");
        this.form1.setSize(new FatuSize(350, 100));
        this.form1.addChild(new EmployeeForm(), FatuHBoxLayoutRule.HBOX_LAYOUT_RULE);
        this.form1.addChild(new EmployeeForm(), FatuHBoxLayoutRule.HBOX_LAYOUT_RULE);
        
        addChild(form1, new FatuBorderLayoutRule(FatuRegion.SOUTH, true));

        this.form2 = new FatuForm<FatuVBoxLayout>(FatuVBoxLayout.VBOX_DEFAULT);
        this.form2.setName("form2");
        this.form2.setTitle("Form 2");
        this.form2.setSize(new FatuSize(300, 150));
        this.form2.addChild(new EmployeeForm(), FatuVBoxLayoutRule.VBOX_LAYOUT_RULE);
        this.form2.addChild(new EmployeeForm(), FatuVBoxLayoutRule.VBOX_LAYOUT_RULE);

        
        addChild(form2, new FatuBorderLayoutRule(FatuRegion.WEST,true));
    }

    @Override
    public String getTitle() {
        return "Border Layout Test Page";
    }

}
