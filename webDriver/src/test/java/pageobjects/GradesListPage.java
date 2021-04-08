package pageobjects;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GradesListPage {

    private WebDriver driver;
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");

    public GradesListPage(WebDriver driver) {
        this.driver = driver;
    }

    public LocalDate getDateOfFirstLine() {
        String test = driver
                .findElement(By.cssSelector("body > div > main > table > tbody > tr:nth-child(1) > td:nth-child(2)"))
                .getText();
        return LocalDate.parse(driver
        .findElement(By.cssSelector("body > div > main > table > tbody > tr:nth-child(1) > td:nth-child(2)"))
        .getText(), dateFormatter);
    }

    public void goToGradeRestaurantPage() {
        driver.findElement(By.linkText("Noter un restaurant")).click();
    }


}
