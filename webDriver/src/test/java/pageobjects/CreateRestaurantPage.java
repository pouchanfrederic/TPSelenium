package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CreateRestaurantPage {

    private WebDriver driver;

    public CreateRestaurantPage(WebDriver driver){
        this.driver = driver;
    }

    public void setNom(String value){
        driver.findElement(By.id("Nom")).sendKeys(value);
    }
    public void setTelephone(String value){
        driver.findElement(By.id("Telephone")).sendKeys(value);
    }
    public void setEmail(String value){
        driver.findElement(By.id("Email")).sendKeys(value);
    }
    public void setDetails(String value){
        driver.findElement(By.id("Details")).sendKeys(value);
    }
    public void setNumeroAdresse(String value){
        driver.findElement(By.id("Adresse_Numero")).sendKeys(value);
    }
    public void setRueAdresse(String value){
        driver.findElement(By.id("Adresse_Rue")).sendKeys(value);
    }
    public void setVilleAdresse(String value){
        driver.findElement(By.id("Adresse_Ville")).sendKeys(value);
    }
    public void setCodePostalAdresse(String value){
        driver.findElement(By.id("Adresse_CodePostal")).sendKeys(value);
    }


}