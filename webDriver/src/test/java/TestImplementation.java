import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

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
    public static void setupWebdriverChromeDriver() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe");
        // for Firefox 
	// System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/src/test/resources/geckodriver");
    }

    @Before
    public void setup() {
        driver = new ChromeDriver();
        // for Firefox
	// driver = new FirefoxDriver();
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
    public void verifyTestTitle(){
        driver.get("https://lambdatest.github.io/sample-todo-app/");
        assertThat(driver.getTitle(), containsString("Sample"));
    }


    /**
     * Test qui ajoute une nouvelle checkbox, puis test si elle existe bien une fois qu'elle est suposée avoir été créée 
     */

    @Test
    public void verifySixthItem(){

        driver.get("https://lambdatest.github.io/sample-todo-app/");
        // 2 | setWindowSize | 1874x1096 |  |
        driver.manage().window().setSize(new Dimension(1874, 1096));
        // 3 | click | id=sampletodotext |  |
        driver.findElement(By.id("sampletodotext")).click();
        // 4 | type | id=sampletodotext | Sixth Item |
        driver.findElement(By.id("sampletodotext")).sendKeys("Sixth Item");
        // 5 | sendKeys | id=sampletodotext | ${KEY_ENTER} |
        driver.findElement(By.id("sampletodotext")).sendKeys(Keys.ENTER);

        assertThat(driver.findElement(By.name("li6")) , notNullValue());
    }
}
