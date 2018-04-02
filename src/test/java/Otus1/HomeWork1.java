package Otus1;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

import java.util.List;

public class HomeWork1 {
    WebDriver driver;

    @Test
    public void getPageLoadedTest(){
        System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://BlazeDemo.com");
        WebElement findFlightsBtn = driver.findElement(By.cssSelector(".btn.btn-primary"));
        findFlightsBtn.click();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        // waiting for page load table
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//tbody")));
        List<WebElement> table = driver.findElements(By.xpath("//tbody"));
        Assert.assertFalse(table.isEmpty(), "table with results is empty");
    }

    @AfterMethod
    public void afterMethod(){
        driver.quit();
    }
}
