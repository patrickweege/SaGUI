package test.com.sagui.model.pages;

import com.sagui.dataset.commons.i18n.CurrentLanguageResolver;
import com.sagui.dataset.commons.i18n.I18n;
import com.sagui.model.container.form.FatuForm;
import com.sagui.model.container.page.FatuPage;
import com.sagui.model.editable.list.checkbox.FatuCheckbox;
import com.sagui.model.feature.FatuSize;
import com.sagui.model.layout.auto.FatuAutoLayout;
import com.sagui.model.layout.auto.FatuAutoLayoutRule;
/**
 *  test.com.jext.model.pages.CheckboxTestPage
 * @author patrick.weege
 *
 */
public class CheckboxTestPage extends FatuPage<FatuAutoLayout> {

    private FatuForm<FatuAutoLayout> form1;

    public CheckboxTestPage() {
        super(FatuAutoLayout.AUTO_LAYOUT);
    }
    
    @Override
    protected void init() {

        this.form1 = new FatuForm<FatuAutoLayout>(FatuAutoLayout.AUTO_LAYOUT);
        this.form1.setName("form1");
        this.form1.setTitle("Form 1");
        this.form1.setSize(new FatuSize(350,100));
        addChild(form1, FatuAutoLayoutRule.AUTO_LAYOUT_RULE);

        final FatuCheckbox cBox1 = new FatuCheckbox(null);
        cBox1.setValue(true);
        I18n i18cBox1 = new I18n(cBox1.getId());
        i18cBox1.setTranslation(CurrentLanguageResolver.getInstance().getCurrentLocale(), "Check Box 1");
        cBox1.setLabel(i18cBox1);
        this.form1.addChild(cBox1, FatuAutoLayoutRule.AUTO_LAYOUT_RULE);
        
        final FatuCheckbox cBox2 = new FatuCheckbox(null);
        cBox2.setValue(false);
        I18n i18cBox2 = new I18n(cBox2.getId());
        i18cBox2.setTranslation(CurrentLanguageResolver.getInstance().getCurrentLocale(), "Check Box 2");
        cBox2.setLabel(i18cBox2);

        this.form1.addChild(cBox2, FatuAutoLayoutRule.AUTO_LAYOUT_RULE);
        

    }    

    @Override
    public String getTitle() {
        return "Combo Box Test Page";
    }

}
