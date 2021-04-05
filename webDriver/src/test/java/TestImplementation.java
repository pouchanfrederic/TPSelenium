import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
// import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;

public class TestImplementation {

    private WebDriver driver;

    @BeforeClass
    public static void setupWebdriverFirefoxDriver() {
         System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") +
         "/src/test/resources/geckodriver.exe");

    }

    @Before
    public void setup() throws MalformedURLException {
        // String Hub = "http://localhost:4444";
        // DesiredCapabilities caps = new DesiredCapabilities();
        // caps.setBrowserName("firefox");
        // driver = new RemoteWebDriver(new URL(Hub), caps);
        driver = new FirefoxDriver();
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void verifyGitHubTitle() {
        driver.get("https://www.github.com");
        assertThat(driver.getTitle(), containsString("GitHub"));
    }

    @Test
    public void verifyTestTitle() {
        driver.get("https://lambdatest.github.io/sample-todo-app/");
        assertThat(driver.getTitle(), containsString("Sample"));
    }

    /**
     * Test qui ajoute une nouvelle checkbox, puis test si elle existe bien une fois
     * qu'elle est suposée avoir été créée
     */

    @Test
    public void verifySixthItem() {

        driver.get("https://lambdatest.github.io/sample-todo-app/");
        // 2 | setWindowSize | 1874x1096 | |
        driver.manage().window().setSize(new Dimension(1874, 1096));
        // 3 | click | id=sampletodotext | |
        driver.findElement(By.id("sampletodotext")).click();
        // 4 | type | id=sampletodotext | Sixth Item |
        driver.findElement(By.id("sampletodotext")).sendKeys("Sixth Item");
        // 5 | sendKeys | id=sampletodotext | ${KEY_ENTER} |
        driver.findElement(By.id("sampletodotext")).sendKeys(Keys.ENTER);

        assertThat(driver.findElement(By.name("li6")), notNullValue());
    }
}
