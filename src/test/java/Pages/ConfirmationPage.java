package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage {
    private WebDriver driver;

    public ConfirmationPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getOrderId(){
        return driver.findElement(By.xpath("//td[contains(text(), 'Id')]/following-sibling::td"))
                .getText().trim();
    }

    public String getOrderStatus(){
        return driver.findElement(By.xpath("//td[contains(text(), 'Status')]/following-sibling::td"))
                .getText().trim();
    }

    public String getAmount(){
        return driver.findElement(By.xpath("//td[contains(text(), 'Amount')]/following-sibling::td"))
                .getText().trim();
    }

    public String getCreditCardNumber(){
        return driver.findElement(By.xpath("//td[contains(text(), 'Card Number')]/following-sibling::td"))
                .getText().replace("x", "").trim();
    }

    public String getExpirationDate(){
        return driver.findElement(By.xpath("//td[contains(text(), 'Expiration')]/following-sibling::td"))
                .getText().trim();
    }
}
