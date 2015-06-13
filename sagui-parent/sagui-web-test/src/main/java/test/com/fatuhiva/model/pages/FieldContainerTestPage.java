package test.com.fatuhiva.model.pages;

import com.fatuhiva.model.container.field.FatuFieldSet;
import com.fatuhiva.model.container.form.FatuForm;
import com.fatuhiva.model.container.page.FatuPage;
import com.fatuhiva.model.editable.editbox.FatuTextBox;
import com.fatuhiva.model.feature.FatuSize;
import com.fatuhiva.model.layout.auto.FatuAutoLayout;
import com.fatuhiva.model.layout.auto.FatuAutoLayoutRule;
import com.tuamotu.commons.i18n.I18n;

public class FieldContainerTestPage extends FatuPage<FatuAutoLayout> {

    private FatuForm<FatuAutoLayout> form1;

    public FieldContainerTestPage() {
        super(FatuAutoLayout.AUTO_LAYOUT);
    }
    
    @Override
    protected void init() {
        this.setTitle(this.getClass().getName());

        // Form 1
        this.form1 = new FatuForm<FatuAutoLayout>(FatuAutoLayout.AUTO_LAYOUT);
        this.form1.setName("form1");
        this.form1.setTitle("Form 1");
        this.form1.setSize(new FatuSize(600,300));
        addChild(form1, FatuAutoLayoutRule.AUTO_LAYOUT_RULE);
        

        FatuTextBox tBoxFirstName = new FatuTextBox();
        tBoxFirstName.setLabel(new I18n("Teste").setDefault("First Name:"));
        FatuTextBox tBoxLastName = new FatuTextBox();
        tBoxLastName.setLabel(new I18n("Teste").setDefault("Last Name:"));

        this.form1.addChild(tBoxFirstName, FatuAutoLayoutRule.AUTO_LAYOUT_RULE);
        this.form1.addChild(tBoxLastName, FatuAutoLayoutRule.AUTO_LAYOUT_RULE);


        FatuTextBox tBoxFatherFirstName = new FatuTextBox();
        FatuTextBox tBoxFatherLastName = new FatuTextBox();
        
        FatuFieldSet<FatuAutoLayout> fatherContainer = new FatuFieldSet<FatuAutoLayout>(FatuAutoLayout.AUTO_LAYOUT);
        fatherContainer.setSize(new FatuSize(400,FatuSize.NOT_ESPECIFIED));
        fatherContainer.setTitle("Father First and Last name:");
        fatherContainer.addChild(tBoxFatherFirstName, FatuAutoLayoutRule.AUTO_LAYOUT_RULE);
        fatherContainer.addChild(tBoxFatherLastName, FatuAutoLayoutRule.AUTO_LAYOUT_RULE);
        this.form1.addChild(fatherContainer, FatuAutoLayoutRule.AUTO_LAYOUT_RULE);
        
        
    }


}
