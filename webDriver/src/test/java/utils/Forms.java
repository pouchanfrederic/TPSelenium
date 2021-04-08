package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Forms {

    public static void submitForm(WebDriver driver) {
        driver.findElement(By.cssSelector("form input[type=submit]")).click();
    }
}
