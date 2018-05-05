package DriverManager;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Properties;

public class DriverManagerHandler {
    public static WebDriver driver;

    public static WebDriver getInstance(){
        if (driver == null){
            String browserType = System.getProperty("browser");
            switch(browserType.toUpperCase()){
                case "CHROME":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case "FIREFOX":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case "IE":
                    WebDriverManager.iedriver().setup();
                    driver = new EdgeDriver();
                    break;
            }
        }
        return driver;
    }
}
