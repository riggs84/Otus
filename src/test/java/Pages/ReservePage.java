package Pages;

import Pages.ReservePageComponents.ReservePageTable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ReservePage{
    WebDriver driver;

    @FindBy(xpath = "//h3")
    WebElement header;

    @FindBy(xpath = "//input[@name='fromPort']")
    WebElement fromPortText;

    @FindBy(xpath = "//input[@name='toPort']")
    WebElement toPortText;

    ReservePageTable table;

    public ReservePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        table = new ReservePageTable();
    }

    public String getDepartureName(){
        return fromPortText.getAttribute("value");
    }

    public String getDestinationName(){
        return toPortText.getAttribute("value");
    }

    public String getHeaderText(){
        return header.getText();
    }

    public void selectRandomRowInFlightsTable(){
        table.selectRandomTableRow();
    }
}
