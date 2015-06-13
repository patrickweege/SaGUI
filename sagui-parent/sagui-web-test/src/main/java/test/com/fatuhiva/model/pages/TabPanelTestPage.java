package test.com.fatuhiva.model.pages;

import com.fatuhiva.model.button.FatuButton;
import com.fatuhiva.model.container.form.FatuForm;
import com.fatuhiva.model.container.page.FatuPage;
import com.fatuhiva.model.container.panel.FatuPanel;
import com.fatuhiva.model.container.tabpanel.FatuTabPanel;
import com.fatuhiva.model.layout.box.FatuHBoxLayout;
import com.fatuhiva.model.layout.box.FatuHBoxLayoutRule;
import com.fatuhiva.model.layout.box.FatuVBoxLayout;
import com.fatuhiva.model.layout.box.FatuVBoxLayoutRule;
import com.fatuhiva.model.layout.fit.FatuFitLayout;
import com.fatuhiva.model.layout.fit.FatuFitLayoutRule;
import com.tuamotu.commons.i18n.I18n;

public class TabPanelTestPage extends FatuPage<FatuFitLayout> {

    private FatuTabPanel<FatuPanel<?>>  tPanel;
    private FatuForm<FatuFitLayout> form1;

    public TabPanelTestPage() {
        super(FatuFitLayout.FIT_LAYOUT);
    }

    @Override
    protected void init() {

        this.form1 = new FatuForm<FatuFitLayout>(FatuFitLayout.FIT_LAYOUT);
        this.form1.setName("form1");
        this.form1.setTitle("Form 1");
        this.addChild(form1, FatuFitLayoutRule.FIT_LAYOUT_RULE);

        tPanel = new FatuTabPanel<FatuPanel<?>>();
        this.form1.addChild(tPanel, FatuFitLayoutRule.FIT_LAYOUT_RULE);

        
        // Criando a TAB 1
        FatuPanel<FatuHBoxLayout> tab1Content = new FatuPanel<FatuHBoxLayout>(FatuHBoxLayout.HBOX_DEFAULT);
        tab1Content.setTitle("Tab 1");
        tPanel.addChild(tab1Content);

        FatuButton btn = new FatuButton();
        btn.setLabel(new I18n("btn11").setDefault("Tab 1 / Button 1"));
        tab1Content.addChild(btn, FatuHBoxLayoutRule.HBOX_LAYOUT_RULE);
        
        btn = new FatuButton();
        btn.setLabel(new I18n("btn12").setDefault("Tab 1 / Button 2"));
        tab1Content.addChild(btn, FatuHBoxLayoutRule.HBOX_LAYOUT_RULE);

        btn = new FatuButton();
        btn.setLabel(new I18n("btn13").setDefault("Tab 1 / Button 3"));
        tab1Content.addChild(btn, FatuHBoxLayoutRule.HBOX_LAYOUT_RULE);
        

        // Criando a TAB 2
        FatuPanel<FatuVBoxLayout> tab2Content = new FatuPanel<FatuVBoxLayout>(FatuVBoxLayout.VBOX_DEFAULT);
        tab2Content.setTitle("Tab 2");
        tPanel.addChild(tab2Content);

        btn = new FatuButton();
        btn.setLabel(new I18n("btn21").setDefault("Tab 2 / Button 1"));
        tab2Content.addChild(btn, FatuVBoxLayoutRule.VBOX_LAYOUT_RULE);
        
        btn = new FatuButton();
        btn.setLabel(new I18n("btn22").setDefault("Tab 2 / Button 2"));
        tab2Content.addChild(btn, FatuVBoxLayoutRule.VBOX_LAYOUT_RULE);

        btn = new FatuButton();
        btn.setLabel(new I18n("btn23").setDefault("Tab 2 / Button 3"));
        tab2Content.addChild(btn, FatuVBoxLayoutRule.VBOX_LAYOUT_RULE);

    }

    @Override
    public String getTitle() {
        return "Tab Panel Test Page";
    }

}
