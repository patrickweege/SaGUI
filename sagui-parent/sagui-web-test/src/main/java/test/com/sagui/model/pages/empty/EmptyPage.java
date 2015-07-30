package test.com.sagui.model.pages.empty;

import com.sagui.model.container.page.FatuPage;
import com.sagui.model.layout.auto.FatuAutoLayout;

public class EmptyPage extends FatuPage<FatuAutoLayout> {

    public EmptyPage() {
        super(FatuAutoLayout.AUTO_LAYOUT);
    }

    @Override
    protected void init() {
    }

    @Override
    public String getTitle() {
        return "This is a Empty Page";
    }

}
