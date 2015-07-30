package test.com.sagui.model.datamodel.masterdetail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.sagui.dataset.commons.comparator.BeanComparatorUtil;
import com.sagui.dataset.commons.comparator.IBeanComparator;
import com.sagui.dataset.commons.comparator.IFieldComparatorMetadata;
import com.sagui.dataset.commons.comparator.IBeanComparator.Order;
import com.sagui.dataset.commons.dataset.Dataset;
import com.sagui.dataset.commons.dataset.DatasetIndex;
import com.sagui.dataset.commons.dataset.IBookmark;
import com.sagui.dataset.commons.dataset.IDatasetField;
import com.sagui.dataset.commons.field.BeanFieldHelper;
import com.sagui.dataset.commons.field.IField;
import com.sagui.model.datamodel.impl.FatuDefaultDatasetTableModel;
import com.sagui.model.datamodel.masterdetail.FatuMasterDetailTableModel;
import com.sagui.model.selection.FatuSimpleSelectionModel;

public class FatuMasterDetailTableModel_TDDTest {

    private List<MDTestEmployee> database;
    private Dataset<MDTestEmployee> dataset;
    private DatasetIndex<MDTestEmployee> index;

    private IDatasetField<MDTestEmployee> ID_FIELD;
    private IDatasetField<MDTestEmployee> NAME_FIELD;
    private IDatasetField<MDTestEmployee> SUBORDINATES_FIELD;
    
    private static final int DET_ID_INDEX = 0; 
    private static final int DET_NAME_INDEX = 1; 
    

    @Before
    public void setUp() {

        this.database = new ArrayList<MDTestEmployee>();
        this.dataset = new Dataset<MDTestEmployee>();

        IField<MDTestEmployee> field;
        field = BeanFieldHelper.getField("id", MDTestEmployee.class);
        this.ID_FIELD = this.dataset.addField(field);

        field = BeanFieldHelper.getField("name", MDTestEmployee.class);
        this.NAME_FIELD = this.dataset.addField(field);

        field = BeanFieldHelper.getField("subordinates", MDTestEmployee.class);
        this.SUBORDINATES_FIELD = this.dataset.addField(field);

        IBeanComparator<MDTestEmployee> comp = BeanComparatorUtil.getBeanComparator(new IFieldComparatorMetadata<MDTestEmployee>(ID_FIELD, Order.ASC, true));
        this.index = dataset.addIndex(comp);

        for (int i = 1; i <= 10; i++) {
            MDTestEmployee emp = new MDTestEmployee();
            emp.setId(i);
            emp.setName("Employee " + i);
            for (int ii = 1; ii <= 3; ii++) {
                MDTestEmployee sub = new MDTestEmployee();
                sub.setId(Integer.parseInt("" + i + ii));
                sub.setName("Subordinate " + i + "." + ii);
                emp.getSubordinates().add(sub);
            }
            database.add(emp);
            dataset.add(emp);
        }

    }

    @Test
    public void selectFirstAtMasterAndCount() {

        FatuDefaultDatasetTableModel<MDTestEmployee> tmMaster = new FatuDefaultDatasetTableModel<MDTestEmployee>(dataset, index, ID_FIELD, NAME_FIELD, SUBORDINATES_FIELD);
        FatuSimpleSelectionModel<IBookmark<MDTestEmployee>> smMaster = new FatuSimpleSelectionModel<IBookmark<MDTestEmployee>>();

        IField<MDTestEmployee>[] fields = new IField[0];
        FatuMasterDetailTableModel<MDTestEmployee, MDTestEmployee> tmDetail = new FatuMasterDetailTableModel<MDTestEmployee, MDTestEmployee>(tmMaster, smMaster, SUBORDINATES_FIELD, fields, null);

        IBookmark<MDTestEmployee> bkmFirst = tmMaster.getBookmark(0);
        smMaster.select(bkmFirst);

        int count = tmDetail.getRowCount();

        Assert.assertEquals(3, count);

    }

    @Test
    public void selectFirstAtMasterAndCheckNamesNotNull() {

        FatuDefaultDatasetTableModel<MDTestEmployee> tmMaster = new FatuDefaultDatasetTableModel<MDTestEmployee>(dataset, index, ID_FIELD, NAME_FIELD, SUBORDINATES_FIELD);
        FatuSimpleSelectionModel<IBookmark<MDTestEmployee>> smMaster = new FatuSimpleSelectionModel<IBookmark<MDTestEmployee>>();

        IField<MDTestEmployee>[] fields = new IField[2];
        fields[DET_ID_INDEX] = BeanFieldHelper.getField("id", MDTestEmployee.class);
        fields[DET_NAME_INDEX] = BeanFieldHelper.getField("name", MDTestEmployee.class);
        FatuMasterDetailTableModel<MDTestEmployee, MDTestEmployee> tmDetail = new FatuMasterDetailTableModel<MDTestEmployee, MDTestEmployee>(tmMaster, smMaster, SUBORDINATES_FIELD, fields, fields[DET_ID_INDEX]);

        IBookmark<MDTestEmployee> bkmFirst = tmMaster.getBookmark(0);
        smMaster.select(bkmFirst);

        int count = tmDetail.getRowCount();

        Assert.assertEquals(3, count);

        for (int iSub = 0; iSub < count; iSub++) {
            String name = tmDetail.getValueAt(iSub, DET_NAME_INDEX);
            Assert.assertNotNull(name);
        }

    }
    
    @Test
    public void selectFirstAtMasterAndCheckIDs() {

        FatuDefaultDatasetTableModel<MDTestEmployee> tmMaster = new FatuDefaultDatasetTableModel<MDTestEmployee>(dataset, index, ID_FIELD, NAME_FIELD, SUBORDINATES_FIELD);
        FatuSimpleSelectionModel<IBookmark<MDTestEmployee>> smMaster = new FatuSimpleSelectionModel<IBookmark<MDTestEmployee>>();

        IField<MDTestEmployee>[] fields = new IField[2];
        fields[DET_ID_INDEX] = BeanFieldHelper.getField("id", MDTestEmployee.class);
        fields[DET_NAME_INDEX] = BeanFieldHelper.getField("name", MDTestEmployee.class);
        FatuMasterDetailTableModel<MDTestEmployee, MDTestEmployee> tmDetail = new FatuMasterDetailTableModel<MDTestEmployee, MDTestEmployee>(tmMaster, smMaster, SUBORDINATES_FIELD, fields, fields[DET_ID_INDEX]);

        IBookmark<MDTestEmployee> bkmFirst = tmMaster.getBookmark(0);
        smMaster.select(bkmFirst);

        int count = tmDetail.getRowCount();

        Assert.assertEquals(3, count);

        Integer masterID = tmMaster.getValueAt(bkmFirst, ID_FIELD);
        
        for (int iSub = 0; iSub < count; iSub++) {
            Integer expectedID = Integer.parseInt("" + masterID + (iSub+1));
            Integer id = tmDetail.getValueAt(iSub, DET_ID_INDEX);

            String expectedName = "Subordinate " + masterID + "." + (iSub+1);
            String name = tmDetail.getValueAt(iSub, DET_NAME_INDEX);
            
            Assert.assertNotNull(id);
            Assert.assertEquals(expectedID, id);
            Assert.assertEquals(expectedName, name);
        }

    }
    
    @Test
    public void selectFirstAtMasterAndChangeDetailName() {

        FatuDefaultDatasetTableModel<MDTestEmployee> tmMaster = new FatuDefaultDatasetTableModel<MDTestEmployee>(dataset, index, ID_FIELD, NAME_FIELD, SUBORDINATES_FIELD);
        FatuSimpleSelectionModel<IBookmark<MDTestEmployee>> smMaster = new FatuSimpleSelectionModel<IBookmark<MDTestEmployee>>();

        IField<MDTestEmployee>[] fields = new IField[2];
        fields[0] = BeanFieldHelper.getField("id", MDTestEmployee.class);
        fields[1] = BeanFieldHelper.getField("name", MDTestEmployee.class);
        FatuMasterDetailTableModel<MDTestEmployee, MDTestEmployee> tmDetail = new FatuMasterDetailTableModel<MDTestEmployee, MDTestEmployee>(tmMaster, smMaster, SUBORDINATES_FIELD, fields, fields[0]);
        

        IBookmark<MDTestEmployee> bkmFirst = tmMaster.getBookmark(0);
        smMaster.select(bkmFirst);

        int count = tmDetail.getRowCount();

        Assert.assertEquals(3, count);

        final String newName = "New Name";
        tmDetail.setValueAt(newName, 0, DET_NAME_INDEX);
        
        // Check The Name
        String currName = tmDetail.getValueAt(0, DET_NAME_INDEX);
        Assert.assertEquals(newName, currName);
        
        // Now Check The Name at The Master
        Integer currID = tmDetail.getValueAt(0, DET_ID_INDEX);
        List<MDTestEmployee> subordinates = tmMaster.getValueAt(bkmFirst, SUBORDINATES_FIELD);
        for (MDTestEmployee subordinate : subordinates) {
            if(subordinate.getId().equals(currID)) {
                Assert.assertEquals(newName, subordinate.getName());
            }
        }
    }

    @Test
    public void selectFirstAtMasterAndInsertDetailThenCount() {

        FatuDefaultDatasetTableModel<MDTestEmployee> tmMaster = new FatuDefaultDatasetTableModel<MDTestEmployee>(dataset, index, ID_FIELD, NAME_FIELD, SUBORDINATES_FIELD);
        FatuSimpleSelectionModel<IBookmark<MDTestEmployee>> smMaster = new FatuSimpleSelectionModel<IBookmark<MDTestEmployee>>();

        IField<MDTestEmployee>[] fields = new IField[2];
        fields[0] = BeanFieldHelper.getField("id", MDTestEmployee.class);
        fields[1] = BeanFieldHelper.getField("name", MDTestEmployee.class);
        FatuMasterDetailTableModel<MDTestEmployee, MDTestEmployee> tmDetail = new FatuMasterDetailTableModel<MDTestEmployee, MDTestEmployee>(tmMaster, smMaster, SUBORDINATES_FIELD, fields, fields[0]);
        

        IBookmark<MDTestEmployee> bkmFirst = tmMaster.getBookmark(0);
        smMaster.select(bkmFirst);

        int count = tmDetail.getRowCount();

        Assert.assertEquals(3, count);
        
        
        MDTestEmployee theNew = new MDTestEmployee();
        theNew.setId(999999);
        theNew.setName("Test Insert at Detail");
        tmDetail.insert(theNew);

        List<MDTestEmployee> subordinates = tmMaster.getValueAt(bkmFirst, SUBORDINATES_FIELD);
        Assert.assertEquals(4, subordinates.size());
        

    }
    


}
