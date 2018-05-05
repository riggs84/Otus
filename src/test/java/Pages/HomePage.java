package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;

public class HomePage {
    WebDriver driver;

    @FindBy(xpath = "//select[@name='fromPort']")
    WebElement departureCity;

    @FindBy(xpath = "//select[@name='toPort']")
    WebElement destinationCity;

    @FindBy(css = "btn btn-primary")
    WebElement findFlightBtn;

    Select chooseDepartureCitySelect;
    Select chooseDestinationCitySelect;

    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        chooseDepartureCitySelect = new Select(departureCity);
        chooseDestinationCitySelect = new Select(destinationCity);
    }

    public String genRandomDepartureCity(){
        List<WebElement> departureCities = chooseDepartureCitySelect.getAllSelectedOptions();
        Random rand = new Random();
        int departureRandomSelectIndex = rand.nextInt(departureCities.size());
        return departureCities.get(departureRandomSelectIndex).getAttribute("value");
    }

    public String genRandomDestinationCity(){
        List<WebElement> destinationCities = chooseDestinationCitySelect.getAllSelectedOptions();
        Random rand = new Random();
        int destinationRandomSelectIndex = rand.nextInt(destinationCities.size());
        return destinationCities.get(destinationRandomSelectIndex).getAttribute("value");
    }

    public void selectDepartureCity(String str){
        chooseDepartureCitySelect.selectByVisibleText(str);
    }

    public void selectDestinationCity(String str){
        chooseDestinationCitySelect.selectByVisibleText(str);
    }

    public ReservePage clickFindFlightsBtn(){
        findFlightBtn.click();
        return new ReservePage(driver);
    }

}
