package Pages;

import org.openqa.selenium.support.PageFactory;

public class BasePage {

    private String pageUrl;

    public BasePage(){
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }
}
