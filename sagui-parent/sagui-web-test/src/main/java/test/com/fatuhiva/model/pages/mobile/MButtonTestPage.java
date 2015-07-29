package test.com.fatuhiva.model.pages.mobile;

import com.fatuhiva.model.action.IFatuActionEvent;
import com.fatuhiva.model.action.IFatuActionListener;
import com.fatuhiva.model.button.FatuButton;
import com.fatuhiva.model.container.form.FatuForm;
import com.fatuhiva.model.container.page.FatuPage;
import com.fatuhiva.model.feature.FatuSize;
import com.fatuhiva.model.label.FatuLabel;
import com.fatuhiva.model.layout.auto.FatuAutoLayout;
import com.fatuhiva.model.layout.auto.FatuAutoLayoutRule;
import com.sagui.dataset.commons.i18n.I18n;

public class MButtonTestPage extends FatuPage<FatuAutoLayout> {

    private FatuLabel lbl;
    
    private int count = 0;
    
    public MButtonTestPage() {
        super(FatuAutoLayout.AUTO_LAYOUT);
    }

    @Override
    protected void init() {

        FatuForm<FatuAutoLayout> form1 = new FatuForm<FatuAutoLayout>(FatuAutoLayout.AUTO_LAYOUT);
        form1.setTitle("Form 1");
        form1.setSize(new FatuSize(150, 100));
        this.addChild(form1, FatuAutoLayoutRule.AUTO_LAYOUT_RULE);

        FatuButton btn = new FatuButton();
        btn.setLabel(new I18n("teste").setDefault("Test Button"));
        form1.addChild(btn, FatuAutoLayoutRule.AUTO_LAYOUT_RULE);
        
        btn.addActionListener(new IFatuActionListener() {
            
            @Override
            public void actionPerformed(IFatuActionEvent evt) {
                System.out.println("Button Click");
                count++;
                refreshCount();
            }
        });
        
        lbl = new FatuLabel();
        form1.addChild(lbl, FatuAutoLayoutRule.AUTO_LAYOUT_RULE);
        
        refreshCount();

    }

    private void refreshCount() {
        lbl.setLabel(new I18n("count").setDefault("Count: " + count));
    }
    
    @Override
    public String getTitle() {
        return "This is a Empty Page";
    }

}
