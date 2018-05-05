package Pages.ReservePageComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.Random;

import static DriverManager.DriverManagerHandler.driver;

public class ReservePageTable {
    private List<WebElement> tableRows = driver.findElements(By.xpath("//table/tbody"));

    private WebElement row = null;

    public void selectRandomTableRow(){
        int rowIndex = new Random().nextInt(tableRows.size());
        row = tableRows.get(rowIndex);
    }

    public String getFlightNumber(){
        if (row == null){
            throw new IllegalStateException("You need to call selectRandomTableRow() first");
        }
        String flightNumber = row.findElement(By.xpath("//input[@name='flight']")).getAttribute("value");
        return flightNumber.substring(flightNumber.indexOf(":") + 1).trim();
    }

    public String getPrice(){
        String price = row.findElement(By.xpath("//input[@name='price']")).getAttribute("value");
        return price.substring(price.indexOf(":") + 1).trim();
    }

    public String getAirline(){
        String airLineName = row.findElement(By.xpath("//input[@name='airline']")).getAttribute("value");
        return airLineName.substring(airLineName.indexOf(":") + 1).trim();
    }

    public void clickChooseThisFlightBtn(){
        if (row == null){
            throw new IllegalStateException("You need to select row by calling selectRandomTableRow() first");
        }
        row.findElement(By.xpath("//input[@type='submit']")).click();
    }



}
