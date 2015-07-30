package test.com.sagui.model.pages;

import com.sagui.dataset.commons.i18n.I18n;
import com.sagui.model.FatuComponent;
import com.sagui.model.button.FatuButton;
import com.sagui.model.container.form.FatuForm;
import com.sagui.model.container.page.FatuPage;
import com.sagui.model.container.panel.FatuPanel;
import com.sagui.model.container.toolbar.FatuToolbar;
import com.sagui.model.feature.FatuSize;
import com.sagui.model.layout.auto.FatuAutoLayout;
import com.sagui.model.layout.auto.FatuAutoLayoutRule;
import com.sagui.model.layout.border.FatuBorderLayout;
/**
 *  test.com.jext.model.pages.ToolbarTestPage
 * @author F0FP250
 *
 */
public class ToolbarTestPage extends FatuPage<FatuAutoLayout> {

    private FatuForm<FatuAutoLayout> form1;
    private FatuToolbar<FatuComponent> toolbar;
    
    private FatuForm<FatuAutoLayout> form2;
    private FatuToolbar<FatuComponent> toolbar2;
    private FatuPanel<FatuBorderLayout> panelWithToolbar;

    
    public ToolbarTestPage() {
        super(FatuAutoLayout.AUTO_LAYOUT);
    }
    
    @Override
    protected void init() {
        this.setTitle(this.getClass().getName());

        // Form 1
        this.form1 = new FatuForm<FatuAutoLayout>(FatuAutoLayout.AUTO_LAYOUT);
        this.form1.setName("form1");
        this.form1.setTitle("Form 1");
        this.form1.setSize(new FatuSize(350,100));
        addChild(form1, FatuAutoLayoutRule.AUTO_LAYOUT_RULE);
        
        FatuButton btn1 = new FatuButton();
        btn1.setLabel(new I18n("TESTE.1").setDefault("Teste 1"));
        FatuButton btn2 = new FatuButton();
        btn2.setLabel(new I18n("TESTE.2").setDefault("Teste 2"));
        
        this.toolbar = new FatuToolbar<FatuComponent>();
        this.toolbar.addChild(btn1);
        this.toolbar.addChild(btn2);
        this.form1.addChild(toolbar, FatuAutoLayoutRule.AUTO_LAYOUT_RULE);

        
        // Form 2
        this.form2 = new FatuForm<FatuAutoLayout>(FatuAutoLayout.AUTO_LAYOUT);
        this.form2.setName("form2");
        this.form2.setTitle("Form 2");
        this.form1.setSize(new FatuSize(350,100));
        addChild(form2, FatuAutoLayoutRule.AUTO_LAYOUT_RULE);

        panelWithToolbar = new FatuPanel<FatuBorderLayout>(new FatuBorderLayout());
        panelWithToolbar.setTitle("Panel with Toolbar Property");
        FatuButton btn3 = new FatuButton();
        btn3.setLabel(new I18n("TESTE.3").setDefault("Teste 3"));
        FatuButton btn4 = new FatuButton();
        btn4.setLabel(new I18n("TESTE.4").setDefault("Teste 4"));
        
        this.toolbar2 = new FatuToolbar<FatuComponent>();
        this.toolbar2.addChild(btn3);
        this.toolbar2.addChild(btn4);
        this.form2.addChild(panelWithToolbar, FatuAutoLayoutRule.AUTO_LAYOUT_RULE);
        
        panelWithToolbar.setToolbar(toolbar2);
        
        
    }


}
