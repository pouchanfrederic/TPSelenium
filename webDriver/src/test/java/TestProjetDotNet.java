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

public class TestProjetDotNet {

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
    public void verifyTitle(){
        driver.get("https://localhost:44353/");
        assertThat(driver.getTitle(), containsString("AppRestaurants.Web"));
    }

    @Test
    public void verifyTopFive(){
        driver.get("https://localhost:44353/");
        assertThat(driver.findElements(By.tagName("tr")).size(), equalTo(6));
    }

}
