package test.com.fatuhiva.model.pages.empty;

import com.fatuhiva.model.container.page.FatuPage;
import com.fatuhiva.model.layout.auto.FatuAutoLayout;

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
