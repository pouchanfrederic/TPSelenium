package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Random;

public class ModifyRestaurantPage {

    private WebDriver driver;

    public ModifyRestaurantPage(WebDriver driver){
        this.driver = driver;
    }

    public void setName(String name) {
        driver.findElement(By.id("Nom")).clear();
        Random random = new Random();
        driver.findElement(By.id("Nom")).sendKeys(name + random.nextInt(200)); //Pour s'assurer que le nom avant et après soient toujours différents
    }
}
