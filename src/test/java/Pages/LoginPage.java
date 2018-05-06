package Pages;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private WebDriver driver;

    @FindBy(css = "button[type=submit]")
    WebElement loginBtn;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void openPage(){
        driver.get("http://blazedemo.com/login");
    }

    public void moveMouseToLoginBtn(){
        Actions builder = new Actions(driver);
        builder.moveToElement(loginBtn);
        builder.build().perform();
    }

    public Dimension getLoginBtnDimensions(){
        return loginBtn.getSize();
    }

    public String getLoginBtnColor(){
        return loginBtn.getCssValue("background-color");
    }
}
