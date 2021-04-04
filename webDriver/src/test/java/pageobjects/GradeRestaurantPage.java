package pageobjects;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GradeRestaurantPage {

    private WebDriver driver;
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
    
    public GradeRestaurantPage(WebDriver driver) {
        this.driver = driver;
    }
    
    public void setGrade(String value){
        driver.findElement(By.id("Note")).sendKeys(value);
    }
    public void setDate(LocalDate value){
        driver.findElement(By.id("DateDerniereVisite")).sendKeys(value.format(dateFormatter));
    }
    public void setComment(String value){
        driver.findElement(By.id("Commentaire")).sendKeys(value);
    }
}
