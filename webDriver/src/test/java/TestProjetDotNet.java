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
import static utils.Forms.submitForm;

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
    }

    @Before
    public void setup() throws MalformedURLException {

        // String Hub = "http://localhost:4444";
        // DesiredCapabilities caps = new DesiredCapabilities();
        // caps.setBrowserName("chrome");
        // driver = new RemoteWebDriver(new URL(Hub), caps);
        driver = new ChromeDriver();
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }


    @Test
    public void verifyDetails2(){
        driver.get("https://localhost:44347/");

        driver.findElement(By.linkText("Gestion des restaurants")).click();
        driver.findElement(By.linkText("Plus d'informations")).click();
        String contentWanted = driver.findElement(By.cssSelector("main > div:nth-child(2) > dl > dt:nth-child(1)")).getText();
        
        assertThat(contentWanted, equalTo("Telephone"));
    }

    @Test
    public void verifyDetails1(){
        driver.get("https://localhost:44347/");

        driver.findElement(By.cssSelector("body > div > main > table > tbody > tr:nth-child(1) > td:nth-child(5) > a")).click();
        String contentWanted = driver.findElement(By.cssSelector("body > div > main > div:nth-child(2) > dl > dd:nth-child(2)")).getText();
        
        assertThat(contentWanted, equalTo("123456789"));  
    }

    @Test
    public void verifyNav(){
        driver.get("https://localhost:44347/");

        String contentNav = driver.findElement(By.cssSelector("body > header > nav > div > div > ul")).getText();
        String contentWanted = "Gestion des restaurants\nGestion des notes";
        assertThat(contentNav, equalTo(contentWanted));
    }


    @Test
    public void verifyFooter(){
        driver.get("https://localhost:44347/");
        
        String contentFooter = driver.findElement(By.cssSelector("body > footer > div")).getText();
        String contentWanted = "2021 - AppRestaurants.Web - Privacy";

        assertThat(contentFooter, containsString(contentWanted));
    }

    @Test
    public void verifyTitle() {
        driver.get("https://localhost:44347/");
        assertThat(driver.getTitle(), containsString("AppRestaurants.Web"));
    }

    @Test
    public void verifyTopFive() {
        driver.get("https://localhost:44347/");
        // Todo : Trouver une autre condition qui ne dépend pas du fait d'avoir 5
        // restaurants en base

        HomePage homePage = new HomePage(driver);
        assertThat(homePage.getNumberOfRestaurants(), equalTo(5));
    }

    @Test
    public void verifyRestaurantCreation() {
        driver.get("https://localhost:44347/Restaurants/Index");

        RestaurantsListPage restaurantsListPage = new RestaurantsListPage(driver);
        CreateRestaurantPage createRestaurantPage = new CreateRestaurantPage(driver);

        int nombreLignesAvant = restaurantsListPage.getNumberOfRestaurants();

        restaurantsListPage.goToCreateRestaurantsPage();

        // On remplit le formulaire
        createRestaurantPage.setNom("restaurant du test1");
        createRestaurantPage.setTelephone("0671615144");
        createRestaurantPage.setEmail("Frederic.poucha@gmail.com");
        createRestaurantPage.setDetails("dzjdzjdzjdz");
        createRestaurantPage.setNumeroAdresse("556");
        createRestaurantPage.setRueAdresse("rue des copains");
        createRestaurantPage.setCodePostalAdresse("38000");
        createRestaurantPage.setVilleAdresse("Grenoble");
        submitForm(driver);

        int nombreLignesApres = restaurantsListPage.getNumberOfRestaurants();
        System.out.println("Nombre de ligne avant l'ajout : " + nombreLignesAvant);
        System.out.println("Nombre de ligne après l'ajout : " + driver.findElements(By.tagName("tr")).size());

        assertThat(nombreLignesApres, equalTo(nombreLignesAvant + 1));

    }


    @Test
    public void deleteFirstRestaurant() {
        driver.get("https://localhost:44347/Restaurants/Index");

        RestaurantsListPage restaurantsListPage = new RestaurantsListPage(driver);

        int nombreLignesAvant = restaurantsListPage.getNumberOfRestaurants();

        restaurantsListPage.deleteFirstRestaurant();
        submitForm(driver);

        int nombreLignesApres = restaurantsListPage.getNumberOfRestaurants();

        System.out.println("Nombre de ligne avant la suppression : " + nombreLignesAvant);
        System.out.println("Nombre de ligne après la suppression : " + nombreLignesApres);

        assertThat(nombreLignesApres, equalTo(nombreLignesAvant - 1));

    }



    @Test
    public void addAGrade() {
        driver.get("https://localhost:44347/Restaurants/GradesList");

        GradesListPage gradesListPage = new GradesListPage(driver);
        GradeRestaurantPage gradeRestaurantPage = new GradeRestaurantPage(driver);

        LocalDate ancienneDate = gradesListPage.getDateOfFirstLine(); 

        gradesListPage.goToGradeRestaurantPage();

        gradeRestaurantPage.setGrade("5");

        gradeRestaurantPage.setDate(ancienneDate.plusDays(1));

        gradeRestaurantPage.setComment("c\'est nul");

        submitForm(driver);

        LocalDate nouvelleDate = gradesListPage.getDateOfFirstLine();

        System.out.println("Avant la modification : " + ancienneDate);
        System.out.println("Après la modification : " + nouvelleDate);

        assertThat(nouvelleDate, not(ancienneDate));

    }
}
