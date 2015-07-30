package test.com.sagui.model.pages;

import com.sagui.dataset.commons.i18n.I18n;
import com.sagui.model.action.IFatuActionEvent;
import com.sagui.model.action.IFatuActionListener;
import com.sagui.model.button.FatuButton;
import com.sagui.model.container.form.FatuForm;
import com.sagui.model.container.page.FatuPage;
import com.sagui.model.container.window.FatuWindow;
import com.sagui.model.feature.FatuSize;
import com.sagui.model.layout.auto.FatuAutoLayout;
import com.sagui.model.layout.auto.FatuAutoLayoutRule;

public class WindowTestPage extends FatuPage<FatuAutoLayout> {

    private FatuForm<FatuAutoLayout> form1;
    private FatuWindow<FatuAutoLayout> window;

    public WindowTestPage() {
        super(FatuAutoLayout.AUTO_LAYOUT);
    }

    @Override
    protected void init() {
        this.setTitle(this.getClass().getName());

        this.form1 = new FatuForm<FatuAutoLayout>(FatuAutoLayout.AUTO_LAYOUT);
        this.form1.setName("form1");
        this.form1.setTitle("Form 1");
        this.form1.setSize(new FatuSize(600, 300));
        addChild(form1, FatuAutoLayoutRule.AUTO_LAYOUT_RULE);

        this.window = new FatuWindow<FatuAutoLayout>(FatuAutoLayout.AUTO_LAYOUT);
        this.window.setSize(new FatuSize(200, 200));
        this.window.setTitle("Janela");
        this.window.setVisible(true);
        this.window.setModal(true);

        this.form1.addChild(window, FatuAutoLayoutRule.AUTO_LAYOUT_RULE);

        FatuButton showButton = new FatuButton();
        showButton.setEnabled(true);
        showButton.setLabel(new I18n("").setDefault("Show Test Window"));
        showButton.addActionListener(new IFatuActionListener() {

            @Override
            public void actionPerformed(IFatuActionEvent evt) {
                WindowTestPage.this.window.setVisible(true);
            }
        });
        this.form1.addChild(showButton, FatuAutoLayoutRule.AUTO_LAYOUT_RULE);

        FatuButton hideButton = new FatuButton();
        hideButton.setEnabled(true);
        hideButton.setLabel(new I18n("").setDefault("Hide Test Window"));
        hideButton.addActionListener(new IFatuActionListener() {

            @Override
            public void actionPerformed(IFatuActionEvent evt) {
                WindowTestPage.this.window.setVisible(false);
            }
        });
        window.addChild(hideButton, FatuAutoLayoutRule.AUTO_LAYOUT_RULE);

        FatuButton newWindowButton = new FatuButton();
        newWindowButton.setEnabled(true);
        newWindowButton.setLabel(new I18n("").setDefault("Modal Child Window"));
        newWindowButton.addActionListener(new IFatuActionListener() {

            @Override
            public void actionPerformed(IFatuActionEvent evt) {
                FatuWindow<FatuAutoLayout> win = new FatuWindow<FatuAutoLayout>(FatuAutoLayout.AUTO_LAYOUT);
                win.setTitle("Child Window");
                win.setSize(new FatuSize(300, 300));
                win.setModal(true);
                win.setVisible(true);
                WindowTestPage.this.window.addChild(win, FatuAutoLayoutRule.AUTO_LAYOUT_RULE);

                FatuButton win2HideButton = new FatuButton();
                win2HideButton.setEnabled(true);
                win2HideButton.setLabel(new I18n("").setDefault("Hide Test Window"));
                win2HideButton.addActionListener(new IFatuActionListener() {

                    @Override
                    public void actionPerformed(IFatuActionEvent evt) {
                        WindowTestPage.this.window.setVisible(false);
                    }
                });
                win.addChild(win2HideButton, FatuAutoLayoutRule.AUTO_LAYOUT_RULE);

            }
        });
        window.addChild(newWindowButton, FatuAutoLayoutRule.AUTO_LAYOUT_RULE);

    }

}
