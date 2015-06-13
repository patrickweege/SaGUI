package test.com.fatuhiva.model.pages;

import java.util.Locale;

import com.fatuhiva.model.action.IFatuActionEvent;
import com.fatuhiva.model.action.IFatuActionListener;
import com.fatuhiva.model.button.FatuButton;
import com.fatuhiva.model.container.form.FatuForm;
import com.fatuhiva.model.container.page.FatuPage;
import com.fatuhiva.model.editable.editbox.FatuTextBox;
import com.fatuhiva.model.feature.FatuSize;
import com.fatuhiva.model.label.FatuLabel;
import com.fatuhiva.model.layout.auto.FatuAutoLayout;
import com.fatuhiva.model.layout.auto.FatuAutoLayoutRule;
import com.tuamotu.commons.i18n.CurrentLanguageResolver;
import com.tuamotu.commons.i18n.I18n;
/**
 *  test.com.jext.model.pages.button.ButtonTestPage
 * @author F0FP250
 *
 */
public class ButtonTestPage extends FatuPage<FatuAutoLayout> {

    private FatuForm<FatuAutoLayout> form1;
    private FatuForm<FatuAutoLayout> form2;
    private FatuForm<FatuAutoLayout> employeeForm;

    private FatuButton btnMakeGreater;
    private FatuButton btnMakeLess;
    private FatuButton btn2;
    private FatuLabel labelText;
    private FatuTextBox form2TextBox1;
    private FatuTextBox form2TextBox2;

    
    public ButtonTestPage() {
        super(FatuAutoLayout.AUTO_LAYOUT);
    }
    
    @Override
    protected void init() {

        this.form1 = new FatuForm<FatuAutoLayout>(FatuAutoLayout.AUTO_LAYOUT);
        this.form1.setName("form1");
        this.form1.setTitle("Form 1");
        this.form1.setSize(new FatuSize(350, 100));
        addChild(form1, FatuAutoLayoutRule.AUTO_LAYOUT_RULE);

        this.form2 = new FatuForm<FatuAutoLayout>(FatuAutoLayout.AUTO_LAYOUT);
        this.form2.setName("form2");
        this.form2.setTitle("Form 2");
        this.form2.setSize(new FatuSize(300, 100));
        addChild(form2, FatuAutoLayoutRule.AUTO_LAYOUT_RULE);

        this.employeeForm = new EmployeeForm();
        addChild(employeeForm, FatuAutoLayoutRule.AUTO_LAYOUT_RULE);

        ChangeChildrenTestForm changeChildrenTestForm = new ChangeChildrenTestForm();
        addChild(changeChildrenTestForm, FatuAutoLayoutRule.AUTO_LAYOUT_RULE);

        {
            btnMakeGreater = new FatuButton();
            I18n i18 = new I18n(btnMakeGreater.getId());
            i18.setTranslation(CurrentLanguageResolver.getInstance().getCurrentLocale(), "Make Form 2 Greater");
            i18.setTranslation(Locale.ENGLISH, "EN Label");
            i18.setTranslation(Locale.GERMAN, "DE Label");
            btnMakeGreater.setLabel(i18);

            i18 = new I18n(btnMakeGreater.getId());
            i18.setTranslation(CurrentLanguageResolver.getInstance().getCurrentLocale(), "Make Form 2 Greater");
            i18.setTranslation(Locale.ENGLISH, "EN Hint");
            i18.setTranslation(Locale.GERMAN, "DE Hint");
            btnMakeGreater.setHint(i18);

            btnMakeGreater.addActionListener(new IFatuActionListener() {

                @Override
                public void actionPerformed(IFatuActionEvent evt) {
                	form2.setSize(new FatuSize(form2.getSize().getWidth(), form2.getSize().getHeight() + 10));
                    form2.setTitle("Height of Form 2 is: " + form2.getSize().getHeight());
                }
            });

            form1.addChild(btnMakeGreater, FatuAutoLayoutRule.AUTO_LAYOUT_RULE);
        }

        {
            btnMakeLess = new FatuButton();
            I18n i18 = new I18n(btnMakeLess.getId());
            i18.setTranslation(CurrentLanguageResolver.getInstance().getCurrentLocale(), "Make Form 2 Less");
            i18.setTranslation(Locale.ENGLISH, "EN Label");
            i18.setTranslation(Locale.GERMAN, "DE Label");
            btnMakeLess.setLabel(i18);

            i18 = new I18n(btnMakeLess.getId());
            i18.setTranslation(CurrentLanguageResolver.getInstance().getCurrentLocale(), "Make Form 2 Less");
            i18.setTranslation(Locale.ENGLISH, "EN Hint");
            i18.setTranslation(Locale.GERMAN, "DE Hint");
            btnMakeLess.setHint(i18);

            btnMakeLess.addActionListener(new IFatuActionListener() {

                @Override
                public void actionPerformed(IFatuActionEvent evt) {
                    form2.setSize(new FatuSize(form2.getSize().getWidth(), form2.getSize().getHeight() - 10));
                    form2.setTitle("Height of Form 2 is: " + form2.getSize().getHeight());
                    I18n i18n = new I18n(labelText.getId());
                    i18n.setTranslation(CurrentLanguageResolver.getInstance().getCurrentLocale(), "The size of Form 2 is:" + form2.getSize().getHeight());
                    labelText.setLabel(i18n);
                }
            });

            form1.addChild(btnMakeLess, FatuAutoLayoutRule.AUTO_LAYOUT_RULE);

        }

        {
            labelText = new FatuLabel();
            I18n i18 = new I18n(labelText.getId());
            i18.setTranslation(CurrentLanguageResolver.getInstance().getCurrentLocale(), "Text for Label");
            i18.setTranslation(Locale.ENGLISH, "EN Label");
            i18.setTranslation(Locale.GERMAN, "DE Label");
            labelText.setLabel(i18);
            form1.addChild(labelText, FatuAutoLayoutRule.AUTO_LAYOUT_RULE);

        }

        //////////////////////// FORM 2 ///////////////////////////////

        {
            this.form2TextBox1 = new FatuTextBox();
            I18n i18 = new I18n(form2TextBox1.getId());
            i18.setTranslation(CurrentLanguageResolver.getInstance().getCurrentLocale(), "Lbl For TextBox");
            i18.setTranslation(Locale.ENGLISH, "EN Label 2");
            i18.setTranslation(Locale.GERMAN, "DE Label 2");
            form2TextBox1.setLabel(i18);
            form2.addChild(form2TextBox1, FatuAutoLayoutRule.AUTO_LAYOUT_RULE);

        }

        {
            this.form2TextBox2 = new FatuTextBox();
            I18n i18 = new I18n(form2TextBox2.getId());
            i18.setTranslation(CurrentLanguageResolver.getInstance().getCurrentLocale(), "Lbl For TextBox 2");
            i18.setTranslation(Locale.ENGLISH, "EN Label form2TextBox2");
            i18.setTranslation(Locale.GERMAN, "DE Label form2TextBox2");
            form2TextBox2.setLabel(i18);
            form2.addChild(form2TextBox2, FatuAutoLayoutRule.AUTO_LAYOUT_RULE);

        }

        {
            this.btn2 = new FatuButton();
            I18n i18 = new I18n(btn2.getId());
            i18.setTranslation(CurrentLanguageResolver.getInstance().getCurrentLocale(), "Default Label 2");
            i18.setTranslation(Locale.ENGLISH, "EN Label 2");
            i18.setTranslation(Locale.GERMAN, "DE Label 2");
            this.btn2.setLabel(i18);

            i18 = new I18n(btnMakeGreater.getId());
            i18.setTranslation(CurrentLanguageResolver.getInstance().getCurrentLocale(), "Default Hint 2");
            i18.setTranslation(Locale.ENGLISH, "EN Hint 2");
            i18.setTranslation(Locale.GERMAN, "DE Hint 2");

            this.btn2.addActionListener(new IFatuActionListener() {

                @Override
                public void actionPerformed(IFatuActionEvent evt) {
                    form2TextBox1.setValue(form2TextBox1.getValue() + "; Batatinha");
                }
            });

            btn2.setHint(i18);
            form2.addChild(btn2, FatuAutoLayoutRule.AUTO_LAYOUT_RULE);
        }

    }

    @Override
    public String getTitle() {
        return "Button Test Page";
    }

}
