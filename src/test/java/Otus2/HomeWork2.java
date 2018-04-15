package Otus2;

import BaseTest.BaseTest;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;
import java.util.Random;

public class HomeWork2 extends BaseTest {

    @Test
    public void test1(){
        // finding elements on home page
        Select chooseDepartureCitySelect = new Select( driver.findElement(By.xpath("//select[@name='fromPort']")) );
        Select chooseDestinationCitySelect = new Select( driver.findElement(By.xpath("//select[@name='toPort']")) );
        WebElement findFlightsBtn = driver.findElement(By.xpath("//input[@type='submit']"));

        // getting all options from departure selection
        List<WebElement> departureCities = chooseDepartureCitySelect.getAllSelectedOptions();
        List<WebElement> destinationCities = chooseDestinationCitySelect.getAllSelectedOptions();

        // Choosing random departure and destination
        String departure = null;
        String destination = null;
        Random rand = new Random();
        int departureRandomSelectIndex = rand.nextInt(departureCities.size());
        int destinationRandomSelectIndex = rand.nextInt(destinationCities.size());

        departure = departureCities.get(departureRandomSelectIndex).getAttribute("value");
        destination = destinationCities.get(destinationRandomSelectIndex).getAttribute("value");

        chooseDepartureCitySelect.selectByVisibleText(departure);
        chooseDestinationCitySelect.selectByVisibleText(destination);
        findFlightsBtn.click();

        // assert that reservation page is loaded
        Assert.assertEquals("http://blazedemo.com/reserve.php", driver.getCurrentUrl());

        // Assert that header has our destination and departure names
        WebElement header = driver.findElement(By.xpath("//h3"));
        Assert.assertTrue(header.getText().contains(departure));
        Assert.assertTrue(header.getText().contains(destination));

        // Checking that table also has departure and destination names
        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='fromPort']")).getAttribute("value"),
                departure);
        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='toPort']")).getAttribute("value"),
                destination);

        // Getting all row from result table
        List<WebElement> tableRows = driver.findElements(By.xpath("//table/tbody"));

        // Select random row
        int rowIndex = rand.nextInt(tableRows.size());
        WebElement row = tableRows.get(rowIndex);

        // Getting flight data
        String flightNumber = row.findElement(By.xpath("//input[@name='flight']")).getAttribute("value");
        flightNumber = flightNumber.substring(flightNumber.indexOf(":") + 1).trim();
        String price = row.findElement(By.xpath("//input[@name='price']")).getAttribute("value");
        price = price.substring(price.indexOf(":") + 1).trim();
        String airLineName = row.findElement(By.xpath("//input[@name='airline']")).getAttribute("value");
        airLineName = airLineName.substring(airLineName.indexOf(":") + 1).trim();

        // Click choose flight btn
        row.findElement(By.xpath("//input[@type='submit']")).click();

        // Validate page loaded
        Assert.assertEquals("http://blazedemo.com/purchase.php", driver.getCurrentUrl());

        // Getting flight information from page
        String airline = driver.findElement(By.xpath("//p[contains(text(), '" + airLineName + "')]")).getText();
        airline = airline.substring(airline.indexOf(":") + 1).trim();
        String flightNum = driver.findElement(By.xpath("//p[contains(text(), '" + flightNumber + "')]")).getText();
        flightNum = flightNum.substring(flightNum.indexOf(":") + 1).trim();
        String pricePurchase = driver.findElement(By.xpath("//p[contains(text(), '" + price + "')]")).getText();
        pricePurchase = pricePurchase.substring(pricePurchase.indexOf(":") + 1).trim();
        float pricePurch = Float.parseFloat(pricePurchase);
        String feesAndTaxes = driver.findElement(By.xpath("//p[contains(text(), 'Arbitrary Fees and Taxes:')]")).getText();
        feesAndTaxes = feesAndTaxes.substring(feesAndTaxes.indexOf(":") + 1).trim();
        float feesAndTax  = Float.parseFloat(feesAndTaxes);
        float totalCost = Float.parseFloat(driver.findElement(By.xpath("//em")).getText().trim());


        // Assert values between reserve and purchase page
        Assert.assertEquals(airLineName, airline);
        Assert.assertEquals(flightNum, flightNumber);
        Assert.assertEquals(price, pricePurchase);
        Assert.assertEquals(totalCost, (pricePurch + feesAndTax));

        // To generate test data Faker lib is used
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String address = faker.address().streetAddress();
        String city = faker.address().city();
        String state = faker.address().state();
        String zipCode = faker.address().zipCode();
        String creditCard = faker.business().creditCardNumber();
        String month = String.valueOf(faker.number().numberBetween(1,12));
        String year = String.valueOf(faker.number().numberBetween(1970, 2070));

        //Filling form
        // first last name
        driver.findElement(By.xpath("//input[@id='inputName']")).sendKeys(firstName + " " + lastName);
        // address
        driver.findElement(By.xpath("//input[@id='address']")).sendKeys(address);
        // city
        driver.findElement(By.xpath("//input[@id='city']")).sendKeys(city);
        // state
        driver.findElement(By.xpath("//input[@id='state']")).sendKeys(state);
        // zip code
        driver.findElement(By.xpath("//input[@id='zipCode']")).sendKeys(zipCode);
        // TODO credit card type random selection
        // credit card number
        driver.findElement(By.xpath("//input[@id='creditCardNumber']")).sendKeys(creditCard);
        // month. We need to clear and then add new value
        WebElement monthInputField = driver.findElement(By.xpath("//input[@id='creditCardMonth']"));
        monthInputField.clear();
        monthInputField.sendKeys(month);
        // year. We need to clear field first and add new value
        WebElement yearInputField = driver.findElement(By.xpath("//input[@id='creditCardYear']"));
        yearInputField.clear();
        yearInputField.sendKeys(year);
        // first + last name of card. Assume that they are same as first name and last name
        driver.findElement(By.xpath("//input[@id='nameOnCard']")).sendKeys(firstName + " " + lastName);
        // sending form
        driver.findElement(By.cssSelector("input.btn")).click();

        // assert that confirmation page is loaded
        Assert.assertEquals("http://blazedemo.com/confirmation.php", driver.getCurrentUrl());

        // Getting purchase data
        String id = driver.findElement(By.xpath("//td[contains(text(), 'Id')]/following-sibling::td"))
                .getText().trim();
        String status = driver.findElement(By.xpath("//td[contains(text(), 'Status')]/following-sibling::td"))
                .getText().trim();
        String amount = driver.findElement(By.xpath("//td[contains(text(), 'Amount')]/following-sibling::td"))
                .getText().trim();
        String cardNumber = driver.findElement(By.xpath("//td[contains(text(), 'Card Number')]/following-sibling::td"))
                .getText().replace("x", "").trim();
        String expirationDate = driver.findElement(By.xpath("//td[contains(text(), 'Expiration')]/following-sibling::td"))
                .getText().trim();
        String date = driver.findElement(By.xpath("//td[contains(text(), 'Date')]/following-sibling::td"))
                .getText().trim();

        // Assert that order id is not null
        Assert.assertTrue((id.length() > 0), "Error: Order id is empty");
        // Check order status
        Assert.assertEquals(status, "PendingCapture", "Error: Status is not equal to 'PendingCapture'");
        // Check amount
        Assert.assertEquals(amount, "USD", "Error: Amount is not USD");
        // Check last 4 digits of credit card
        Assert.assertEquals(cardNumber, creditCard.substring(creditCard.length() - 4),
                "Error: last 4 digits of credit card is not equal" );
        // Check expiration
        Assert.assertEquals(expirationDate, (month + " /" + year), "Error: Expiration date is not equal");







    }
}
