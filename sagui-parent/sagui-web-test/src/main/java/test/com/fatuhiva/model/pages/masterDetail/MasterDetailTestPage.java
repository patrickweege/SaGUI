package test.com.fatuhiva.model.pages.masterDetail;

import java.text.DecimalFormat;

import com.fatuhiva.model.container.form.FatuForm;
import com.fatuhiva.model.container.page.FatuPage;
import com.fatuhiva.model.datamodel.IFatuColumnModel;
import com.fatuhiva.model.datamodel.impl.FatuDefaultDatasetTableModel;
import com.fatuhiva.model.datamodel.masterdetail.FatuMasterDetailTableModel;
import com.fatuhiva.model.datasource.FatuAbstractDataSource;
import com.fatuhiva.model.datasource.FatuSelectedDataModelDataSource;
import com.fatuhiva.model.editable.editbox.FatuTextBox;
import com.fatuhiva.model.feature.FatuSize;
import com.fatuhiva.model.grid.FatuGrid;
import com.fatuhiva.model.layout.auto.FatuAutoLayout;
import com.fatuhiva.model.layout.auto.FatuAutoLayoutRule;
import com.fatuhiva.model.selection.FatuSimpleSelectionModel;
import com.fatuhiva.model.selection.IFatuSelectionModel;
import com.sagui.dataset.commons.comparator.BeanComparatorUtil;
import com.sagui.dataset.commons.comparator.IBeanComparator;
import com.sagui.dataset.commons.comparator.IFieldComparatorMetadata;
import com.sagui.dataset.commons.dataset.Dataset;
import com.sagui.dataset.commons.dataset.DatasetIndex;
import com.sagui.dataset.commons.dataset.IBookmark;
import com.sagui.dataset.commons.field.BeanFieldHelper;
import com.sagui.dataset.commons.field.I18nFieldImpl;
import com.sagui.dataset.commons.field.IField;
import com.sagui.dataset.commons.i18n.I18n;

public class MasterDetailTestPage extends FatuPage<FatuAutoLayout> {

    private FatuForm<FatuAutoLayout> formProjects;
    private IFatuColumnModel columnModelProject;
    private FatuDefaultDatasetTableModel<Project> tmProjects;
    private Dataset<Project> dsProjects;
    private DatasetIndex<Project> dsProjectIndex;
    private FatuGrid gridProjects;

    private FatuSimpleSelectionModel<IBookmark<ProjectMember>> smMembers;
    private FatuMasterDetailTableModel<Project, ProjectMember> tmMembers;
    private FatuForm<FatuAutoLayout> formMembers;
    private FatuGrid gridMembers;
    private IFatuColumnModel columnModelMembers;

    public MasterDetailTestPage() {
        super(FatuAutoLayout.AUTO_LAYOUT);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void init() {

        this.dsProjects = new Dataset<Project>();

        I18n i18ProjectID = new I18n("").setDefault("Project ID");
        IField<Project> prjIDField = new I18nFieldImpl<Project>(i18ProjectID, i18ProjectID, BeanFieldHelper.getField("id", Project.class));
        prjIDField = dsProjects.addField(prjIDField);
        IBeanComparator<Project> beanComparator = BeanComparatorUtil.getBeanComparator(new IFieldComparatorMetadata<Project>(prjIDField, true));
        this.dsProjectIndex = dsProjects.addIndex(beanComparator);

        I18n i18ProjectName = new I18n("").setDefault("Project Name");
        IField<Project> prjNameField = new I18nFieldImpl<Project>(i18ProjectName, i18ProjectName, BeanFieldHelper.getField("name", Project.class));
        prjNameField = dsProjects.addField(prjNameField);

        IField<Project> prjMembersField = BeanFieldHelper.getField("members", Project.class);
        prjMembersField = dsProjects.addField(prjMembersField);

        DecimalFormat df = new DecimalFormat("00000000");
        for (int iProj = 1; iProj <= 1000; iProj++) {
            Project aProject = new Project();
            aProject.setId(iProj);
            aProject.setName("Project " + df.format(iProj));
            for (int iMemb = 1; iMemb <= 10; iMemb++) {
                ProjectMember aMember = new ProjectMember();
                aMember.setId(iProj * 1000 + iMemb);
                aMember.setName("Project Member " + df.format(aMember.getId()));
                aMember.setEmail(df.format(aMember.getId()) + "@test.com");
                aProject.getMembers().add(aMember);
            }
            dsProjects.add(aProject);
        }

        IField<Project>[] prjDataModelFields = new IField[] { prjIDField, prjNameField, prjMembersField };
        IField<Project>[] prjColumnModelFields = new IField[] { prjIDField, prjNameField };

        this.tmProjects = new FatuDefaultDatasetTableModel<Project>(dsProjects, dsProjectIndex, prjDataModelFields);

        IFatuSelectionModel<IBookmark<Project>> smProjects = new FatuSimpleSelectionModel<IBookmark<Project>>();

        this.gridProjects = new FatuGrid(tmProjects, smProjects);
        this.columnModelProject = gridProjects.getColumnModel();
        this.columnModelProject.setVisible(false, tmProjects.getColumnIndex(prjMembersField));
        
        gridProjects.setSize(new FatuSize(500, 270));

        this.formProjects = new FatuForm<FatuAutoLayout>(FatuAutoLayout.AUTO_LAYOUT);
        this.formProjects.setName("ProjectsTestForm");
        this.formProjects.setTitle("Master Detail Test Page - Projects Form");
        this.formProjects.setSize(new FatuSize(700, 300));
        this.addChild(formProjects, FatuAutoLayoutRule.AUTO_LAYOUT_RULE);
        this.formProjects.addChild(gridProjects, FatuAutoLayoutRule.AUTO_LAYOUT_RULE);

        I18n i18MemberID = new I18n("").setDefault("Member ID");
        IField<ProjectMember> memberIDField = new I18nFieldImpl<ProjectMember>(i18MemberID, i18MemberID, BeanFieldHelper.getField("id", ProjectMember.class));

        I18n i18MemberName = new I18n("").setDefault("Member Name");
        IField<ProjectMember> memberNameField = new I18nFieldImpl<ProjectMember>(i18MemberName, i18MemberName, BeanFieldHelper.getField("name", ProjectMember.class));

        I18n i18MemberMail = new I18n("").setDefault("Member Mail");
        IField<ProjectMember> memberMailField = new I18nFieldImpl<ProjectMember>(i18MemberMail, i18MemberMail, BeanFieldHelper.getField("email", ProjectMember.class));
        
        IField<ProjectMember>[] membDataModelFields = new IField[] { memberIDField, memberNameField, memberMailField };

        this.tmMembers = new FatuMasterDetailTableModel<Project,ProjectMember>(tmProjects, smProjects, prjMembersField, membDataModelFields, memberIDField);
        
        this.smMembers = new FatuSimpleSelectionModel<IBookmark<ProjectMember>>();
        this.gridMembers = new FatuGrid(tmMembers, smMembers);
        this.gridMembers.setSize(new FatuSize(500, 270));
        
        this.formMembers = new FatuForm<FatuAutoLayout>(FatuAutoLayout.AUTO_LAYOUT);
        this.formMembers.setName("MembersTestForm");
        this.formMembers.setTitle("Master Detail Test Page - Members Form");
        this.formMembers.setSize(new FatuSize(700, 400));
        this.formMembers.addChild(gridMembers, FatuAutoLayoutRule.AUTO_LAYOUT_RULE);
        
        memberIDField = tmMembers.getColumn(0);
        FatuAbstractDataSource<String> dsID = new FatuSelectedDataModelDataSource<ProjectMember,String>(String.class, memberIDField, tmMembers, smMembers);
        FatuTextBox tBoxMemberID = new FatuTextBox(dsID);
        tBoxMemberID.setLabel(new I18n("").setDefault("Member ID:"));
        this.formMembers.addChild(tBoxMemberID, FatuAutoLayoutRule.AUTO_LAYOUT_RULE);

        memberNameField = tmMembers.getColumn(1);
        FatuAbstractDataSource<String> dsName = new FatuSelectedDataModelDataSource<ProjectMember,String>(String.class, memberNameField, tmMembers, smMembers);
        FatuTextBox tBoxMemberName = new FatuTextBox(dsName);
        tBoxMemberName.setLabel(new I18n("").setDefault("Member Name:"));
        this.formMembers.addChild(tBoxMemberName, FatuAutoLayoutRule.AUTO_LAYOUT_RULE);

        this.addChild(formMembers, FatuAutoLayoutRule.AUTO_LAYOUT_RULE);

    }

    @Override
    public String getTitle() {
        return this.getClass().getName();
    }

}
