import com.sun.javafx.runtime.SystemProperties;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverFactory {
    private static WebDriver driver;
    private WebDriverWait wait;
    private int waitTimeOut = 5;

    public WebDriverFactory(){
        String browserVer = System.getProperty("webdriver.browser");
        if (browserVer.toUpperCase().equals("CHROME")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            wait = new WebDriverWait(driver, waitTimeOut);
        } else if (browserVer.toUpperCase().equals("FIREFOX")){
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            wait = new WebDriverWait(driver, waitTimeOut);
        }

    }

    public static WebDriver getWebDriver(){
        if (driver == null){
            throw new IllegalStateException("Driver instance is null");
        } else {
            return driver;
        }
    }

    public void webDriverQuit(){
        driver.quit();
        driver = null;
    }

    public WebDriverWait getWebDriverWaitInstance(){
        if (wait == null){
            throw new IllegalStateException("WebDriverWait instance is null");
        }
        return wait;
    }

}
