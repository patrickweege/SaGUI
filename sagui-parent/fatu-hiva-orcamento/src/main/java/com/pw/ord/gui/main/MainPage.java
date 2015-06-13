package com.pw.ord.gui.main;

import com.fatuhiva.model.container.page.FatuPage;
import com.fatuhiva.model.layout.fit.FatuFitLayout;
import com.fatuhiva.model.layout.fit.FatuFitLayoutRule;

public class MainPage extends FatuPage {

    private MainForm mainForm;

    public MainPage() {
        super(FatuFitLayout.FIT_LAYOUT);
    }

    @Override
    protected void init() {
        this.mainForm = new MainForm();
        super.addChild(mainForm, FatuFitLayoutRule.FIT_LAYOUT_RULE);
    }

}
