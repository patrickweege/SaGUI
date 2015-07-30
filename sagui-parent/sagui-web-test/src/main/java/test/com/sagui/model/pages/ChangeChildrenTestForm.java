package test.com.sagui.model.pages;

import java.util.Locale;

import com.sagui.dataset.commons.i18n.CurrentLanguageResolver;
import com.sagui.dataset.commons.i18n.I18n;
import com.sagui.model.FatuComponent;
import com.sagui.model.action.IFatuActionEvent;
import com.sagui.model.action.IFatuActionListener;
import com.sagui.model.button.FatuButton;
import com.sagui.model.container.form.FatuForm;
import com.sagui.model.feature.FatuSize;
import com.sagui.model.label.FatuLabel;
import com.sagui.model.layout.table.FatuTableLayout;
import com.sagui.model.layout.table.FatuTableLayoutRule;

public class ChangeChildrenTestForm extends FatuForm<FatuTableLayout> {
	
	private static int lblCount = 0;

    private FatuButton btnAddChild;
    private FatuButton btnRemoveChild;

    public ChangeChildrenTestForm() {
        super(new FatuTableLayout(3));

        this.setName("ChangeChildrenTestForm Form");
        this.setTitle("ChangeChildrenTestForm Form");
        this.setSize(new FatuSize(500, 150));

        this.btnAddChild = new FatuButton();
        I18n i18 = new I18n(btnAddChild.getId());
        i18.setTranslation(CurrentLanguageResolver.getInstance().getCurrentLocale(), "Add Child");
        i18.setTranslation(Locale.ENGLISH, "EN Label 2");
        i18.setTranslation(Locale.GERMAN, "DE Label 2");
        this.btnAddChild.setLabel(i18);

        this.btnAddChild.addActionListener(new IFatuActionListener() {

            @Override
            public void actionPerformed(IFatuActionEvent evt) {
                FatuLabel labelText = new FatuLabel();
                I18n i18 = new I18n(labelText.getId());
                lblCount++;
                i18.setTranslation(CurrentLanguageResolver.getInstance().getCurrentLocale(), "Label " + lblCount);
                labelText.setLabel(i18);
                ChangeChildrenTestForm.this.addChild(labelText, FatuTableLayoutRule.TABLE_RULE);
               
                createRemoveButton();
            }
        });

        this.addChild(btnAddChild, FatuTableLayoutRule.TABLE_RULE);

    }

    private void createRemoveButton() {
        if(this.btnRemoveChild != null) return;
        if (this.getChildren().size() >= 2) {
            this.btnRemoveChild = new FatuButton();
            I18n i18 = new I18n(btnRemoveChild.getId());
            i18.setTranslation(CurrentLanguageResolver.getInstance().getCurrentLocale(), "Remove Child");
            i18.setTranslation(Locale.ENGLISH, "EN Label 2");
            i18.setTranslation(Locale.GERMAN, "DE Label 2");
            btnRemoveChild.setLabel(i18);
            this.insertChild(1, btnRemoveChild, FatuTableLayoutRule.TABLE_RULE);

            this.btnRemoveChild.addActionListener(new IFatuActionListener() {

                @Override
                public void actionPerformed(IFatuActionEvent evt) {
                    FatuComponent toRemove = getChildren().get(getChildren().size() - 1);
                    removeChild(toRemove);
                    lblCount--;
                    if (getChildren().size() <= 2 && btnRemoveChild != null) {
                        removeChild(btnRemoveChild);
                        btnRemoveChild = null;
                    }
                }
            });

        }
    }

}
