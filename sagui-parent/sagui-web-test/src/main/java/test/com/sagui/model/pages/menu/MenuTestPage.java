package test.com.sagui.model.pages.menu;

import java.util.ArrayList;
import java.util.List;

import com.sagui.dataset.commons.i18n.I18n;
import com.sagui.model.container.form.FatuForm;
import com.sagui.model.container.menu.FatuMenu;
import com.sagui.model.container.menu.FatuMenuItem;
import com.sagui.model.container.page.FatuPage;
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
 * @author F0FP250
 *
 */
public class MenuTestPage extends FatuPage<FatuBorderLayout> {

    private FatuForm<FatuFitLayout> menuForm;
    private FatuForm<FatuVBoxLayout> centerForm;

    public MenuTestPage() {
        super(new FatuBorderLayout());
    }
    
    @Override
    protected void init() {
        this.menuForm = new FatuForm<FatuFitLayout>(FatuFitLayout.FIT_LAYOUT);
        this.menuForm.setName("menuForm");
        this.menuForm.setTitle("Menu Form");
        this.menuForm.setSize(new FatuSize(350, 100));
        
        FatuMenu<FatuMenuItem> menu = this.getMenu();
        menuForm.addChild(menu, FatuFitLayoutRule.FIT_LAYOUT_RULE);	
        
        
        addChild(menuForm, new FatuBorderLayoutRule(FatuRegion.WEST, true));

        this.centerForm = new FatuForm<FatuVBoxLayout>(FatuVBoxLayout.VBOX_DEFAULT);
        this.centerForm.setName("form2");
        this.centerForm.setTitle("Form 2");
        this.centerForm.setSize(new FatuSize(300, 150));
        this.centerForm.addChild(new EmployeeForm(), FatuVBoxLayoutRule.VBOX_LAYOUT_RULE);
        this.centerForm.addChild(new EmployeeForm(), FatuVBoxLayoutRule.VBOX_LAYOUT_RULE);

        
        addChild(centerForm, new FatuBorderLayoutRule(FatuRegion.WEST,true));
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
