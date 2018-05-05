package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PurchasePage {
    private WebDriver driver;

    @FindBy(xpath = "//input[@id='inputName']")
    WebElement nameInputField;

    @FindBy(xpath = "//input[@id='address']")
    WebElement addressInputField;

    @FindBy(xpath = "//input[@id='city']")
    WebElement cityInputField;

    @FindBy(xpath = "//input[@id='state']")
    WebElement stateInputField;

    @FindBy(xpath = "//input[@id='zipCode']")
    WebElement zipCodeInputField;

    @FindBy(xpath = "//input[@id='creditCardNumber']")
    WebElement creditCardInputField;

    @FindBy(xpath = "//input[@id='creditCardMonth']")
    WebElement creditCardMonthInputField;

    @FindBy(xpath = "//input[@id='creditCardYear']")
    WebElement creditCardYearInputField;

    @FindBy(xpath = "//input[@id='nameOnCard']")
    WebElement cardHolderNameInputFiled;

    @FindBy(css = "input.btn")
    WebElement purchaseFlightBtn;

    public PurchasePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getAirline(){
        String airline = driver.findElement(By.xpath("//p[contains(text(), 'Airline')]")).getText();
        return airline.substring(airline.indexOf(":") + 1).trim();
    }

    public String getFlightNumber(){
        String flightNum = driver.findElement(By.xpath("//p[contains(text(), 'Flight Number')]")).getText();
        return flightNum.substring(flightNum.indexOf(":") + 1).trim();
    }

    public float getPrice(){
        String pricePurchase = driver.findElement(By.xpath("//p[contains(text(), 'Price')]")).getText();
        pricePurchase = pricePurchase.substring(pricePurchase.indexOf(":") + 1).trim();
        return Float.parseFloat(pricePurchase);
    }

    public float getFeesAndTax(){
        String feesAndTaxes = driver.findElement(By.xpath("//p[contains(text(), 'Arbitrary Fees and Taxes:')]")).getText();
        feesAndTaxes = feesAndTaxes.substring(feesAndTaxes.indexOf(":") + 1).trim();
        return Float.parseFloat(feesAndTaxes);
    }

    public float getTotalCost(){
        return Float.parseFloat(driver.findElement(By.xpath("//em")).getText().trim());
    }

    public void inputName(String str){
        nameInputField.clear();
        nameInputField.sendKeys(str);
    }

    public void inputAddress(String str){
        addressInputField.clear();
        addressInputField.sendKeys(str);
    }

    public void inputCity(String str){
        cityInputField.clear();
        cityInputField.sendKeys(str);
    }

    public void inputState(String str){
        stateInputField.clear();
        stateInputField.sendKeys(str);
    }

    public void inputZipCode(String str){
        zipCodeInputField.clear();
        zipCodeInputField.sendKeys(str);
    }

    public void inputCreditCard(String str){
        creditCardInputField.clear();
        creditCardInputField.sendKeys(str);
    }

    public void inputCreditCardMonth(String str){
        creditCardMonthInputField.clear();
        creditCardMonthInputField.sendKeys(str);
    }

    public void inputCreditCardYear(String str){
        creditCardYearInputField.clear();
        creditCardYearInputField.sendKeys(str);
    }

    public void inputCardHolderName(String str){
        cardHolderNameInputFiled.clear();
        cardHolderNameInputFiled.sendKeys(str);
    }

    public ConfirmationPage clickPurchaseFlightBtn(){
        purchaseFlightBtn.click();
        return new ConfirmationPage(driver);
    }
}
