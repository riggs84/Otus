package BaseTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected WebDriverWait wait;
    protected WebDriver driver;
    private int timeOut = 5;

    @BeforeClass
    public void beforeClass(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, timeOut);
    }

    @BeforeMethod
    public void beforeMethod(){
        driver.get("http://blazedemo.com");
        Assert.assertEquals("http://blazedemo.com/", driver.getCurrentUrl());
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
