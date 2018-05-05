package BaseTest;

import DriverManager.DriverManagerHandler;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;
import static DriverManager.DriverManagerHandler.driver;

public class BaseTest {
    protected WebDriverWait wait;
    private int timeOut = 5;

    @BeforeClass
    public void beforeClass(){
        DriverManagerHandler.getInstance();
        driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, timeOut);
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
