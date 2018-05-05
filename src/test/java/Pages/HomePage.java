package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;

public class HomePage {
    WebDriver driver;

    @FindBy(xpath = "//select[@name='fromPort']")
    WebElement departureCity;

    @FindBy(xpath = "//select[@name='toPort']")
    WebElement destinationCity;

    @FindBy(css = "input[type=submit]")
    WebElement findFlightBtn;

    Select chooseDepartureCitySelect;
    Select chooseDestinationCitySelect;

    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String genRandomDepartureCity(){
        List<WebElement> departureCities = new Select(departureCity).getAllSelectedOptions();
        Random rand = new Random();
        int departureRandomSelectIndex = rand.nextInt(departureCities.size());
        return departureCities.get(departureRandomSelectIndex).getAttribute("value");
    }

    public String genRandomDestinationCity(){
        List<WebElement> destinationCities = new Select(destinationCity).getAllSelectedOptions();
        Random rand = new Random();
        int destinationRandomSelectIndex = rand.nextInt(destinationCities.size());
        return destinationCities.get(destinationRandomSelectIndex).getAttribute("value");
    }

    public void selectDepartureCity(String str){
        new Select(departureCity).selectByVisibleText(str);
    }

    public void selectDestinationCity(String str){
        new Select(destinationCity).selectByVisibleText(str);
    }

    public ReservePage clickFindFlightsBtn(){
        findFlightBtn.click();
        return new ReservePage(driver);
    }

    public void openPage(){
        driver.get("http://blazedemo.com");
    }

}
