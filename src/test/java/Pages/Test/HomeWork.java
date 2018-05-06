package Pages.Test;

import BaseTest.BaseTest;
import Pages.*;
import Pages.Utils.DataGenerator;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import static DriverManager.DriverManagerHandler.driver;

public class HomeWork extends BaseTest{
    protected String homePageURL = "http://blazedemo.com/";
    protected String reservePageURL = "http://blazedemo.com/reserve.php";
    protected String purchasePageURL = "http://blazedemo.com/purchase.php";
    protected String confirmationPageURL = "http://blazedemo.com/confirmation.php";
    protected String loginPageURL ="http://blazedemo.com/login";

    @Test
    public void homeWork3test(){
        HomePage homePage = new HomePage(driver);
        homePage.openPage();
        Assert.assertEquals(driver.getCurrentUrl(), homePageURL);
        String departureCity = homePage.genRandomDepartureCity();
        String destinationCity = homePage.genRandomDestinationCity();
        homePage.selectDepartureCity(departureCity);
        homePage.selectDestinationCity(destinationCity);
        ReservePage reservePage = homePage.clickFindFlightsBtn();
        Assert.assertEquals(driver.getCurrentUrl(), reservePageURL);
        Assert.assertTrue(reservePage.getHeaderText().contains(departureCity));
        Assert.assertTrue(reservePage.getHeaderText().contains(destinationCity));
        Assert.assertEquals(reservePage.getDepartureName(), departureCity);
        Assert.assertEquals(reservePage.getDestinationName(), destinationCity);
        reservePage.selectRandomRowInFlightsTable();
        String flightNumber = reservePage.getTable().getFlightNumber();
        String price = reservePage.getTable().getPrice();
        String airLineName = reservePage.getTable().getAirline();
        reservePage.getTable().clickChooseThisFlightBtn();
        PurchasePage purchasePage = new PurchasePage(driver);
        Assert.assertEquals(driver.getCurrentUrl(), purchasePageURL);
        String airLineNamePurchase = purchasePage.getAirline();
        String flightNumberPurchase = purchasePage.getFlightNumber();
        float pricePurchasePage = purchasePage.getPrice();
        float feesAndTaxPurchasePage = purchasePage.getFeesAndTax();
        float totalCostPurchasePage = purchasePage.getTotalCost();
        Assert.assertEquals(airLineName, airLineNamePurchase);
        Assert.assertEquals(flightNumberPurchase, flightNumber);
        Assert.assertEquals(pricePurchasePage, Float.parseFloat(price));
        Assert.assertEquals(totalCostPurchasePage, (pricePurchasePage + feesAndTaxPurchasePage));

        String firstName = DataGenerator.generateFirstName();
        String lastName = DataGenerator.generateLastName();
        String city = DataGenerator.generateCity();
        String address = DataGenerator.generateAddress();
        String state = DataGenerator.generateState();
        String zipCode = DataGenerator.generateZipCode();
        String creditCard = DataGenerator.generateCreditCardNumber();
        String month = DataGenerator.generateMonth();
        String year = DataGenerator.generateYear();

        purchasePage.inputAddress(address);
        purchasePage.inputName(firstName + " " + lastName);
        purchasePage.inputCardHolderName(firstName + " " + lastName);
        purchasePage.inputCity(city);
        purchasePage.inputState(state);
        purchasePage.inputZipCode(zipCode);
        purchasePage.inputCreditCard(creditCard);
        purchasePage.inputCreditCardMonth(month);
        purchasePage.inputCreditCardYear(year);

        ConfirmationPage confirmPage = purchasePage.clickPurchaseFlightBtn();
        Assert.assertEquals(driver.getCurrentUrl(), confirmationPageURL);

        Assert.assertTrue(confirmPage.getOrderId().length() > 0, "Error: order id is empty");
        Assert.assertEquals(confirmPage.getOrderStatus(),
                "PendingCapture", "Error: Status is not equal to 'PendingCapture'");
        Assert.assertEquals(confirmPage.getAmount(), "USD", "Error: Amount is not USD");
        Assert.assertEquals(confirmPage.getCreditCardNumber(), creditCard.substring(creditCard.length() -4),
                "Error: last 4 digits of credit card is not equal");
        Assert.assertEquals(confirmPage.getExpirationDate(), month + " /" + year,
                "Error: expiration date doesn't match");

    }

    @Test
    public void checkLoginBtnDimensionAndColorTest(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        Assert.assertEquals(driver.getCurrentUrl(), loginPageURL);
        Dimension loginBtnWithoutFocus = loginPage.getLoginBtnDimensions();
        loginPage.moveMouseToLoginBtn();
        String loginBtnColor = loginPage.getLoginBtnColor();
        Dimension loginBtnWithFocusOn = loginPage.getLoginBtnDimensions();
        Assert.assertTrue(loginBtnWithFocusOn.equals(loginBtnWithoutFocus));
        Assert.assertEquals(loginBtnColor, "rgba(37, 121, 169, 1)");
    }
}
