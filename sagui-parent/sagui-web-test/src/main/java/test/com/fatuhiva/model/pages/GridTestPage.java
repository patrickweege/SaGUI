package test.com.fatuhiva.model.pages;

import java.awt.Color;
import java.text.DecimalFormat;
import java.util.Locale;
import java.util.UUID;

import com.fatuhiva.model.FatuComponent;
import com.fatuhiva.model.action.IFatuActionEvent;
import com.fatuhiva.model.action.IFatuActionListener;
import com.fatuhiva.model.button.FatuButton;
import com.fatuhiva.model.container.form.FatuForm;
import com.fatuhiva.model.container.page.FatuPage;
import com.fatuhiva.model.datamodel.IFatuCellRender;
import com.fatuhiva.model.datamodel.IFatuColumnModel;
import com.fatuhiva.model.datamodel.impl.FatuArrayDatasetDataModel;
import com.fatuhiva.model.feature.FatuSize;
import com.fatuhiva.model.grid.FatuGrid;
import com.fatuhiva.model.layout.auto.FatuAutoLayout;
import com.fatuhiva.model.layout.auto.FatuAutoLayoutRule;
import com.sagui.dataset.commons.comparator.BeanComparatorUtil;
import com.sagui.dataset.commons.comparator.IBeanComparator;
import com.sagui.dataset.commons.comparator.IFieldComparatorMetadata;
import com.sagui.dataset.commons.dataset.Dataset;
import com.sagui.dataset.commons.dataset.DatasetIndex;
import com.sagui.dataset.commons.dataset.IDataset;
import com.sagui.dataset.commons.field.ArrayBeanFieldImpl;
import com.sagui.dataset.commons.field.I18nFieldImpl;
import com.sagui.dataset.commons.field.IField;
import com.sagui.dataset.commons.i18n.CurrentLanguageResolver;
import com.sagui.dataset.commons.i18n.I18n;

/**
 * test.com.jext.model.pages.GridTestPage
 * 
 * @author F0FP250
 * 
 */
public class GridTestPage extends FatuPage<FatuAutoLayout> {

    private FatuForm<FatuAutoLayout> form1;
    private IFatuColumnModel columnModel;
    private FatuArrayDatasetDataModel<String> dataModel;

    public GridTestPage() {
        super(FatuAutoLayout.AUTO_LAYOUT);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void init() {

        this.form1 = new FatuForm<FatuAutoLayout>(FatuAutoLayout.AUTO_LAYOUT);
        this.form1.setName("form1");
        this.form1.setTitle("Form 1");
        this.form1.setSize(new FatuSize(700, 500));
        addChild(form1, FatuAutoLayoutRule.AUTO_LAYOUT_RULE);

        FatuButton btnAddItem = new FatuButton();
        I18n btnAddItemLbl = new I18n(btnAddItem.getId());
        btnAddItemLbl.setTranslation(CurrentLanguageResolver.getInstance().getCurrentLocale(), "Add Item");
        btnAddItemLbl.setTranslation(Locale.ENGLISH, "Add Item");
        btnAddItemLbl.setTranslation(Locale.GERMAN, "Add Item");
        btnAddItem.setLabel(btnAddItemLbl);

        this.form1.addChild(btnAddItem, FatuAutoLayoutRule.AUTO_LAYOUT_RULE);

        IDataset<String[]> dataset = new Dataset<String[]>();

        I18n uuid = new I18n("").setDefault("UUID");
        IField<String[]> uuIDField = new I18nFieldImpl<String[]>(uuid, uuid, new ArrayBeanFieldImpl<String>(0, "UUID", true));
        uuIDField = dataset.addField(uuIDField);
        IBeanComparator<String[]> beanComparator = BeanComparatorUtil.getBeanComparator(new IFieldComparatorMetadata<String[]>(uuIDField, true));
        //DatasetIndex<String[]> uuidIndex = dataset.addIndex(beanComparator);

        I18n codigo = new I18n("").setDefault("Código");
        IField<String[]> idField = new I18nFieldImpl<String[]>(codigo, codigo, new ArrayBeanFieldImpl<String>(1, "Identificador", true));
        idField = dataset.addField(idField);
        beanComparator = BeanComparatorUtil.getBeanComparator(new IFieldComparatorMetadata<String[]>(idField, true));
        DatasetIndex<String[]> idIndex = dataset.addIndex(beanComparator);

        I18n nome = new I18n("").setDefault("Nome da Pessoa");
        IField<String[]> nameField = new I18nFieldImpl<String[]>(nome, nome, new ArrayBeanFieldImpl<String>(2, "Nome da Pessoa", true));
        nameField = dataset.addField(nameField);

        DecimalFormat df = new DecimalFormat("00000000");
        for (int i = 0; i < 1000; i++) {
            String[] row = new String[3];
            row[0] = UUID.randomUUID().toString();
            row[1] = df.format(i+1);
            row[2] = "Employee " + row[1];
            dataset.add(row);
        }

        IField<String[]>[] dataModelFields = new IField[] { uuIDField, idField, nameField };
        this.dataModel = new FatuArrayDatasetDataModel<String>(dataset, idIndex, dataModelFields);

        IFatuCellRender cellRender = new IFatuCellRender() {
            
            @Override
            public Color getbackgroundColor(FatuComponent component, int row, int col, Object value) {
                if(col == 0) return Color.GREEN;
                if(col == 1) {
                    if((row % 2) > 0) {
                        return Color.YELLOW;
                    }
                }
                return null;
            }
            
            @Override
            public Color getColor(FatuComponent component, int row, int col, Object value) {
                return null;
            }
        };
        
        FatuGrid grid = new FatuGrid(dataModel);
        this.columnModel = grid.getColumnModel();
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            if(dataModel.getColumn(i) == idField) {
                this.columnModel.setVisible(true, i);    
            } else if(dataModel.getColumn(i) == idField) {
                this.columnModel.setVisible(true, i);    
            } else {
                this.columnModel.setVisible(false, i);    
            }
        }
        
        this.columnModel.setCellRenderer(0, cellRender);
        this.columnModel.setCellRenderer(1, cellRender);

        grid.setSize(new FatuSize(300, 400));

        this.form1.addChild(grid, FatuAutoLayoutRule.AUTO_LAYOUT_RULE);

        btnAddItem.addActionListener(new IFatuActionListener() {

            @Override
            public void actionPerformed(IFatuActionEvent evt) {
                DecimalFormat df = new DecimalFormat("00000000");
                int rowCount = dataModel.getRowCount();
                rowCount++;
                String[] bean = new String[] { UUID.randomUUID().toString(), df.format(rowCount), "Employee " + rowCount };
                dataModel.insert(bean);
            }
        });

    }

    @Override
    public String getTitle() {
        return this.getClass().getName();
    }

}
