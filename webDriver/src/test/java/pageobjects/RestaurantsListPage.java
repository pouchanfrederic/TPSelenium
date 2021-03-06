package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RestaurantsListPage {

    private WebDriver driver;

    public RestaurantsListPage(WebDriver driver){
        this.driver = driver;
    }

    public int getNumberOfRestaurants() {
        return driver.findElements(By.tagName("tr")).size() - 1;
    }

    public void goToCreateRestaurantsPage() {
        driver.findElement(By.linkText("Creer un restaurant")).click();
    }

    public void deleteFirstRestaurant() {
        driver.findElement(By.linkText("Supprimer")).click();
    }

    public String getFirstRestaurantName() {
        return driver.findElement(By.cssSelector("tbody > tr:nth-child(1) > td:nth-child(1)")).getText();
    }

    public void goToModifyRestaurantPage() {
        driver.findElement(By.linkText("Modifier")).click();
    }
}