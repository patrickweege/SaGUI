package test.com.sagui.model.pages.menu;

import java.util.ArrayList;
import java.util.List;

import com.sagui.dataset.commons.i18n.I18n;
import com.sagui.model.container.form.FatuForm;
import com.sagui.model.container.menu.FatuMenu;
import com.sagui.model.container.menu.FatuMenuItem;
import com.sagui.model.container.page.FatuPage;
import com.sagui.model.feature.FatuOrientation;
import com.sagui.model.feature.FatuSize;
import com.sagui.model.layout.border.FatuBorderLayout;
import com.sagui.model.layout.border.FatuBorderLayoutRule;
import com.sagui.model.layout.border.FatuRegion;
import com.sagui.model.layout.box.FatuVBoxLayout;
import com.sagui.model.layout.box.FatuVBoxLayoutRule;
import com.sagui.model.layout.fit.FatuFitLayout;
import com.sagui.model.layout.fit.FatuFitLayoutRule;

import test.com.sagui.model.pages.EmployeeForm;
/**
 *  test.com.jext.model.pages.BorderLayoutTestPage
 * @author patrick.weege
 *
 */
public class MenuTestPage extends FatuPage<FatuBorderLayout> {

	private FatuForm<FatuFitLayout> horizontalMenuForm;
    private FatuForm<FatuFitLayout> verticalMenuForm;
    private FatuForm<FatuVBoxLayout> centerForm;

    public MenuTestPage() {
        super(new FatuBorderLayout());
    }
    
    @Override
    protected void init() {
    	
    	/*
    	 * Define The Horizontal Menu
    	 */
        this.horizontalMenuForm = new FatuForm<FatuFitLayout>(FatuFitLayout.FIT_LAYOUT);
        this.horizontalMenuForm.setName("horizontalMenuForm");
        this.horizontalMenuForm.setTitle("Horizontal Menu Form");
        this.horizontalMenuForm.setSize(new FatuSize(FatuSize.NOT_ESPECIFIED, 100));
        
        FatuMenu<FatuMenuItem> hMenu = this.getMenu();
        hMenu.setOrientation(FatuOrientation.HORIZONTAL);
        this.horizontalMenuForm.addChild(hMenu, FatuFitLayoutRule.FIT_LAYOUT_RULE);	

        
        /*
         * Define The Vertical Menu
         */
        this.verticalMenuForm = new FatuForm<FatuFitLayout>(FatuFitLayout.FIT_LAYOUT);
        this.verticalMenuForm.setName("verticalMenuForm");
        this.verticalMenuForm.setTitle("Vertical Menu Form");
        this.verticalMenuForm.setSize(new FatuSize(350, FatuSize.NOT_ESPECIFIED));
        
        FatuMenu<FatuMenuItem> vMenu = this.getMenu();
        vMenu.setOrientation(FatuOrientation.VERTICAL);
        this.verticalMenuForm.addChild(vMenu, FatuFitLayoutRule.FIT_LAYOUT_RULE);	
        
        
        /*
         * The Center Form 
         */
        this.centerForm = new FatuForm<FatuVBoxLayout>(FatuVBoxLayout.VBOX_STRETCH_START);
        this.centerForm.setName("form2");
        this.centerForm.setTitle("Form 2");
        
        //this.centerForm.setSize(new FatuSize(300, 150));
        EmployeeForm empForm1 = new EmployeeForm();
        //empForm1.setSize(new FatuSize(FatuSize.NOT_ESPECIFIED, 200));
        empForm1.setSize(null);
        
        EmployeeForm empForm2 = new EmployeeForm();
        //empForm2.setSize(new FatuSize(FatuSize.NOT_ESPECIFIED, 200));
        empForm2.setSize(null);
        this.centerForm.addChild(empForm1, FatuVBoxLayoutRule.VBOX_LAYOUT_RULE);
        this.centerForm.addChild(empForm2, FatuVBoxLayoutRule.VBOX_LAYOUT_RULE);

        
        this.addChild(horizontalMenuForm, new FatuBorderLayoutRule(FatuRegion.NORTH, true));
        this.addChild(verticalMenuForm, new FatuBorderLayoutRule(FatuRegion.WEST, true));
        this.addChild(centerForm, new FatuBorderLayoutRule(FatuRegion.CENTER,true));
    }

    private FatuMenu<FatuMenuItem> getMenu() {

    	List<FatuMenuItem> menuItems = new ArrayList<FatuMenuItem>();
    	for(int i = 0; i < 10; i++) {
    		I18n menuLabel1 = new I18n("item."+i).setDefault("Menu Item (" + i + ")");
    		FatuMenuItem fatuMenuItem1 = new FatuMenuItem(menuLabel1);
    		menuItems.add(fatuMenuItem1);
    	}
    	
    	return new FatuMenu<FatuMenuItem>(menuItems);
    	
    }
    
    
    @Override
    public String getTitle() {
        return "Border Layout Test Page";
    }

}
