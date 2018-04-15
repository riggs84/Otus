package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class HomePage extends BasePage {
    @FindBy(xpath = "//select[@name='fromPort']")
    Select chooseDepartureCity;

    @FindBy(xpath = "//select[@name='toPort']")
    Select chooseDestinationCity;

    @FindBy(css = "btn btn-primary")
    WebElement findFlightBtn;

    public HomePage(){
        super();
        setPageUrl("http://blazedemo.com");
    }

    public Select getChooseDepartureCity() {
        return chooseDepartureCity;
    }

    public Select getChooseDestinationCity() {
        return chooseDestinationCity;
    }

    public WebElement getFindFlightBtn() {
        return findFlightBtn;
    }
}
