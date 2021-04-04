import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import pageobjects.CreateRestaurantPage;
import pageobjects.GradeRestaurantPage;
import pageobjects.GradesListPage;
import pageobjects.HomePage;
import pageobjects.RestaurantsListPage;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Random;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
// import static org.junit.Assert.*;
import org.openqa.selenium.By;

public class TestProjetDotNet {

    private WebDriver driver;

    @BeforeClass
    public static void setupWebdriverChromeDriver() {
        System.setProperty("webdriver.chrome.driver",
                System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe");
        // for Firefox
        // System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") +
        // "/src/test/resources/geckodriver");
    }

    @Before
    public void setup() throws MalformedURLException {

        // String Hub = "http://localhost:4444";
        // DesiredCapabilities caps = new DesiredCapabilities();
        // caps.setBrowserName("chrome");
        // //caps.setBrowserName("firefox");
        // driver = new RemoteWebDriver(new URL(Hub), caps);

        driver = new ChromeDriver();

        driver.get("https://localhost:44347/");
        // for Firefox
        // driver = new FirefoxDriver();
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // TODO : tests simples : présence des boutons dans la navbar ; texte du footer
    // ; navigation sur la page détails * 2

    @Test
    public void verifyTitle() {
        assertThat(driver.getTitle(), containsString("AppRestaurants.Web"));
    }

    @Test
    public void verifyTopFive() {
        // Todo : Trouver une autre condition qui ne dépend pas du fait d'avoir 5
        // restaurants en base

        HomePage homePage = new HomePage(driver);
        assertThat(homePage.getNumberOfRestaurants(), equalTo(5));
    }

    @Test
    public void verifyRestaurantCreation() {

        HomePage homePage = new HomePage(driver);
        RestaurantsListPage restaurantsListPage = new RestaurantsListPage(driver);
        CreateRestaurantPage createRestaurantPage = new CreateRestaurantPage(driver);

        homePage.goToRestaurantsListPage();

        int nombreLignesAvant = restaurantsListPage.getNumberOfRestaurants();

        restaurantsListPage.goToCreateRestaurantsPage();

        // On remplit le formulaire
        createRestaurantPage.setNom("restaurant du test1");
        createRestaurantPage.setTelephone("0671615144");
        createRestaurantPage.setEmail("Frederic.poucha@gmail.com");
        createRestaurantPage.setDetails("dzjdzjdzjdz");
        createRestaurantPage.setNumeroAdresse("556");
        createRestaurantPage.setRueAdresse("rue des copains");
        createRestaurantPage.setCodePostalAdresse("87667");
        createRestaurantPage.setVilleAdresse("Fontaine");
        submitForm();

        int nombreLignesApres = restaurantsListPage.getNumberOfRestaurants();
        System.out.println("Nombre de ligne avant l'ajout : " + nombreLignesAvant);
        System.out.println("Nombre de ligne après l'ajout : " + driver.findElements(By.tagName("tr")).size());

        assertThat(nombreLignesApres, equalTo(nombreLignesAvant + 1));

    }

    @Test
    public void deleteFirstRestaurant() {
        HomePage homePage = new HomePage(driver);
        RestaurantsListPage restaurantsListPage = new RestaurantsListPage(driver);

        homePage.goToRestaurantsListPage();

        int nombreLignesAvant = restaurantsListPage.getNumberOfRestaurants();

        restaurantsListPage.deleteFirstRestaurant();
        submitForm();

        int nombreLignesApres = restaurantsListPage.getNumberOfRestaurants();

        System.out.println("Nombre de ligne avant la suppression : " + nombreLignesAvant);
        System.out.println("Nombre de ligne après la suppression : " + nombreLignesApres);

        assertThat(nombreLignesApres, equalTo(nombreLignesAvant - 1));

    }

    // @Test
    // public void modifyARestaurant(){
    // // Test name: modifierUnRestaurant
    // // Step # | name | target | value
    // // 1 | open | / |
    // // 2 | setWindowSize | 1219x736 |
    // driver.manage().window().setSize(new Dimension(1219, 736));
    // // 3 | click | linkText=Gestion des Restaurants |
    // driver.findElement(By.linkText("Gestion des Restaurants")).click();

    // String nomDuPremierRestaurant = driver.findElement(By.cssSelector("body > div
    // > main > table > tbody > tr:nth-child(1) > td:nth-child(1)")).getText();

    // // 4 | click | linkText=Modifier |
    // driver.findElement(By.linkText("Modifier")).click();
    // // 6 | type | id=nom | Le Restaurant est modifie
    // driver.findElement(By.id("nom")).clear();

    // Random random = new Random();

    // driver.findElement(By.id("nom")).sendKeys("Le Restaurant est modifié " +
    // random.nextInt(200)); //Pour s'assurer que le nom avant et après soient
    // toujours différents
    // // 7 | click | css=.btn-success |
    // driver.findElement(By.cssSelector(".btn-success")).click();

    // System.out.println("Avant la modification : " + nomDuPremierRestaurant);
    // System.out.println("Après la modification : " +
    // driver.findElement(By.cssSelector("body > div > main > table > tbody >
    // tr:nth-child(1) > td:nth-child(1)")).getText());

    // assertThat(driver.findElement(By.cssSelector("body > div > main > table >
    // tbody > tr:nth-child(1) > td:nth-child(1)")).getText(),
    // not(nomDuPremierRestaurant));
    // }

    @Test
    public void addAGrade() {
        
        HomePage homePage = new HomePage(driver);
        GradesListPage gradesListPage = new GradesListPage(driver);
        GradeRestaurantPage gradeRestaurantPage = new GradeRestaurantPage(driver);

        homePage.goToGradesPage();

        LocalDate ancienneDate = gradesListPage.getDateOfFirstLine(); 

        gradesListPage.goToGradeRestaurantPage();

        gradeRestaurantPage.setGrade("5");

        gradeRestaurantPage.setDate(ancienneDate.plusDays(1));

        gradeRestaurantPage.setComment("c\'est nul");

        submitForm();

        LocalDate nouvelleDate = gradesListPage.getDateOfFirstLine();

        System.out.println("Avant la modification : " + ancienneDate);
        System.out.println("Après la modification : " + nouvelleDate);

        assertThat(nouvelleDate, not(ancienneDate));

    }

    /* Méthodes utilitaires non liées à une page en pariculier*/

    private void submitForm() {
        driver.findElement(By.cssSelector("form input[type=submit]")).click();
    }

}
