package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    
    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void goToRestaurantsListPage() {
        driver.findElement(By.linkText("Gestion des restaurants")).click();
    }

    public int getNumberOfRestaurants() {
        return driver.findElements(By.tagName("tr")).size() - 1;
    }

    public void goToGradesPage() {
        driver.findElement(By.linkText("Gestion des notes")).click();
    }



}
