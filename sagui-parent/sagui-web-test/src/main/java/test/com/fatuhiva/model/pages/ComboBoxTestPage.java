package test.com.fatuhiva.model.pages;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import com.fatuhiva.model.FatuElement;
import com.fatuhiva.model.action.IFatuActionEvent;
import com.fatuhiva.model.action.IFatuActionListener;
import com.fatuhiva.model.button.FatuButton;
import com.fatuhiva.model.container.form.FatuForm;
import com.fatuhiva.model.container.page.FatuPage;
import com.fatuhiva.model.feature.FatuSize;
import com.fatuhiva.model.label.FatuLabel;
import com.fatuhiva.model.layout.auto.FatuAutoLayout;
import com.fatuhiva.model.layout.auto.FatuAutoLayoutRule;
import com.fatuhiva.model.list.combo.editable.FatuComboBox;
import com.fatuhiva.model.list.editable.impl.FatuSimpleListModel;
import com.fatuhiva.model.selection.FatuSimpleSelectionModel;
import com.fatuhiva.model.selection.IFatuSelectionListener;
import com.sagui.dataset.commons.dataset.IBookmark;
import com.sagui.dataset.commons.i18n.CurrentLanguageResolver;
import com.sagui.dataset.commons.i18n.I18n;

/**
 * test.com.jext.model.pages.ComboBoxTestPage
 * 
 * @author F0FP250
 * 
 */
public class ComboBoxTestPage extends FatuPage<FatuAutoLayout> {

    private FatuForm<FatuAutoLayout> form1;

    public ComboBoxTestPage() {
        super(FatuAutoLayout.AUTO_LAYOUT);
    }

    @Override
    protected void init() {

        this.form1 = new FatuForm<FatuAutoLayout>(FatuAutoLayout.AUTO_LAYOUT);
        this.form1.setName("form1");
        this.form1.setTitle("Form 1");
        this.form1.setSize(new FatuSize(350, 100));
        addChild(form1, FatuAutoLayoutRule.AUTO_LAYOUT_RULE);

        List<ComboItem> items = new ArrayList<ComboItem>();
        for (int i = 0; i < 10; i++) {
            items.add(new ComboItem("Item " + i));
        }

        final FatuSimpleListModel<ComboItem> model = new FatuSimpleListModel<ComboItem>();
        model.setItems(items);
        FatuSimpleSelectionModel<IBookmark<ComboItem>> selectionModel = new FatuSimpleSelectionModel<IBookmark<ComboItem>>();
        IBookmark<ComboItem> selected = model.getBookmark(5);
        selectionModel.select(selected);

        final FatuComboBox<ComboItem> combo = new FatuComboBox<ComboItem>(model, selectionModel);
        I18n i18 = new I18n(combo.getId());
        i18.setTranslation(CurrentLanguageResolver.getInstance().getCurrentLocale(), "Combo Box");
        i18.setTranslation(Locale.ENGLISH, "EN Combo Box");
        i18.setTranslation(Locale.GERMAN, "DE Combo Box");
        combo.setLabel(i18);

        this.form1.addChild(combo, FatuAutoLayoutRule.AUTO_LAYOUT_RULE);

        FatuButton btnAddItem = new FatuButton();
        I18n btnAddItemLbl = new I18n(btnAddItem.getId());
        btnAddItemLbl.setTranslation(CurrentLanguageResolver.getInstance().getCurrentLocale(), "Add Item");
        btnAddItemLbl.setTranslation(Locale.ENGLISH, "Add Item");
        btnAddItemLbl.setTranslation(Locale.GERMAN, "Add Item");
        btnAddItem.setLabel(btnAddItemLbl);

        this.form1.addChild(btnAddItem, FatuAutoLayoutRule.AUTO_LAYOUT_RULE);

        final FatuLabel selectedItem = new FatuLabel();
        I18n i18nSelectedItem = new I18n(selectedItem.getId());
        i18nSelectedItem.setTranslation(CurrentLanguageResolver.getInstance().getCurrentLocale(), "Selected Item:");
        selectedItem.setLabel(i18nSelectedItem);
        this.form1.addChild(selectedItem, FatuAutoLayoutRule.AUTO_LAYOUT_RULE);

        IFatuSelectionListener<IBookmark<ComboItem>> iSelectionListener = new IFatuSelectionListener<IBookmark<ComboItem>>() {

            @Override
            public void selectionChanged(Collection<IBookmark<ComboItem>> newItems, Collection<IBookmark<ComboItem>> oldItems) {
                StringBuilder sb = new StringBuilder();
                for (IBookmark<ComboItem> anSelected : newItems) {
                    int rowIndex = model.getRowIndex(anSelected);
                    String ID = model.getKey(rowIndex);
                    String label = model.getLabel(rowIndex);
                    sb.append(ID + "- " + label + ";");
                }
                I18n i18n = new I18n(selectedItem.getId());
                i18n.setTranslation(CurrentLanguageResolver.getInstance().getCurrentLocale(), sb.toString());
                selectedItem.setLabel(i18n);
            }

        };

        selectionModel.addSelectionListener(iSelectionListener);
        btnAddItem.addActionListener(new IFatuActionListener() {

            @Override
            public void actionPerformed(IFatuActionEvent evt) {
                model.insert(new ComboItem("Item " + combo.getListModel().getRowCount() + 1));
            }
        });

    }

    private class ComboItem extends FatuElement {

        private final String label;

        public ComboItem(String label) {
            this.label = label;
        }

        @Override
        public String toString() {
            return label;
        }

    }

    @Override
    public String getTitle() {
        return "Combo Box Test Page";
    }

}
