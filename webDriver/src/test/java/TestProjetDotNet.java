import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.util.Random;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
// import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

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
         //Todo : Trouver une autre condition qui ne dépend pas du fait d'avoir 5 restaurants en base 
        driver.get("https://localhost:44353/");
        assertThat(driver.findElements(By.tagName("tr")).size(), equalTo(6));
    }

    @Test
    public void verifyRestaurantCreation(){

    driver.get("https://localhost:44377/");
    // 2 | setWindowSize | 1219x736 | 
    driver.manage().window().setSize(new Dimension(1219, 736));
    // 3 | click | linkText=Gestion des Restaurants | 
    driver.findElement(By.linkText("Gestion des Restaurants")).click();

    var nombreLigne = driver.findElements(By.tagName("tr")).size();
    // 4 | click | linkText=Ajouter un nouveau restaurant | 
    driver.findElement(By.linkText("Ajouter un nouveau restaurant")).click();
    // 5 | click | id=nom | 
    // 6 | type | id=nom | restaurant du bonheur
    driver.findElement(By.id("nom")).sendKeys("restaurant du test1");
    // 7 | click | id=numero_telephone | 
    // 8 | type | id=numero_telephone | 0671615144
    driver.findElement(By.id("numero_telephone")).sendKeys("0671615144");
    // 9 | type | id=adresse_mail | Frederic.pouchan@gmail.com
    driver.findElement(By.id("adresse_mail")).sendKeys("Frederic.poucha@gmail.com");
    // 10 | click | css=.row | 
    // 11 | click | css=.form-group:nth-child(4) | 
    driver.findElement(By.cssSelector(".form-group:nth-child(4)")).click();
    // 12 | click | id=detail | 
    // 13 | type | id=detail | dzjdzjdzjdz
    driver.findElement(By.id("detail")).sendKeys("dzjdzjdzjdz");
    // 14 | type | id=address_street | 556 rue des copains
    driver.findElement(By.id("address_street")).sendKeys("556 rue des copains");
    // 15 | type | id=address_codePostal | 87667
    driver.findElement(By.id("address_codePostal")).sendKeys("87667");
    // 16 | type | id=address_ville | Fontaine
    driver.findElement(By.id("address_ville")).sendKeys("Fontaine");
    // 17 | click | css=.btn | 
    // driver.findElement(By.cssSelector(".btn")).click();


    submitForm();

    System.out.println("Nombre de ligne avant l'ajout : " + nombreLigne );
    System.out.println("Nombre de ligne après l'ajout : " +  driver.findElements(By.tagName("tr")).size());

    assertThat(driver.findElements(By.tagName("tr")).size(), equalTo(nombreLigne + 1));

    }

    @Test
    public void deleteFirstRestaurant(){
        // Test name: testSuppressionRestaurant
        // Step # | name | target | value
        // 1 | open | / | 
        driver.get("https://localhost:44377/");
        // 2 | setWindowSize | 1219x736 | 
        driver.manage().window().setSize(new Dimension(1219, 736));
        // 3 | click | linkText=Gestion des Restaurants | 
        driver.findElement(By.linkText("Gestion des Restaurants")).click();

        var nombreLigne = driver.findElements(By.tagName("tr")).size();

        // 4 | click | linkText=Supprimer | 
        driver.findElement(By.linkText("Supprimer")).click();

        // 5 | click | css=.btn | 
        submitForm();

        System.out.println("Nombre de ligne avant la suppression : " + nombreLigne );
        System.out.println("Nombre de ligne après la suppression : " +  driver.findElements(By.tagName("tr")).size());

        assertThat(driver.findElements(By.tagName("tr")).size(), equalTo(nombreLigne - 1));

    }


    @Test
    public void modifyARestaurant(){
        // Test name: modifierUnRestaurant
        // Step # | name | target | value
        // 1 | open | / | 
        driver.get("https://localhost:44377/");
        // 2 | setWindowSize | 1219x736 | 
        driver.manage().window().setSize(new Dimension(1219, 736));
        // 3 | click | linkText=Gestion des Restaurants | 
        driver.findElement(By.linkText("Gestion des Restaurants")).click();

        var nomDuPremierRestaurant = driver.findElement(By.cssSelector("body > div > main > table > tbody > tr:nth-child(1) > td:nth-child(1)")).getText();

        // 4 | click | linkText=Modifier | 
        driver.findElement(By.linkText("Modifier")).click();
        // 6 | type | id=nom | Le Restaurant est modifie
        driver.findElement(By.id("nom")).clear();

        Random random = new Random();

        driver.findElement(By.id("nom")).sendKeys("Le Restaurant est modifié " + random.nextInt(200)); //Pour s'assurer que le nom avant et après soient toujours différents 
        // 7 | click | css=.btn-success | 
        driver.findElement(By.cssSelector(".btn-success")).click();

        System.out.println("Avant la modification : " + nomDuPremierRestaurant);
        System.out.println("Après la modification : " + driver.findElement(By.cssSelector("body > div > main > table > tbody > tr:nth-child(1) > td:nth-child(1)")).getText());

        assertThat(driver.findElement(By.cssSelector("body > div > main > table > tbody > tr:nth-child(1) > td:nth-child(1)")).getText(), not(nomDuPremierRestaurant));
    }

    @Test
    public void addAGrade(){
        driver.get("https://localhost:44377/");
        driver.manage().window().setSize(new Dimension(1219, 736));
        driver.findElement(By.linkText("Gestion des Restaurants")).click();
        driver.findElement(By.linkText("Noter vos Restaurants préférés")).click();

        var dateDeLaNote = driver.findElement(By.cssSelector("body > div > main > table > tbody > tr:nth-child(1) > td:nth-child(2)")).getText();


        driver.findElement(By.cssSelector("tr:nth-child(1) .btn")).click();
        driver.findElement(By.id("note_derniere_visite_score")).clear();
        driver.findElement(By.id("note_derniere_visite_score")).sendKeys("3");
        driver.findElement(By.id("note_derniere_visite_commentaire")).clear();
        driver.findElement(By.id("note_derniere_visite_commentaire")).sendKeys("super expérience !");
        submitForm();
        driver.findElement(By.linkText("Noter vos Restaurants préférés")).click(); //On retourne sur la page pour pouvoir comparer les notes

        System.out.println("Avant la modification : " + dateDeLaNote);
        System.out.println("Après la modification : " + driver.findElement(By.cssSelector("body > div > main > table > tbody > tr:nth-child(1) > td:nth-child(2)")).getText());

        assertThat(driver.findElement(By.cssSelector("body > div > main > table > tbody > tr:nth-child(1) > td:nth-child(2)")).getText(), not(dateDeLaNote));

    }

    private void submitForm(){
        
        // WebElement form = driver.findElement(By.tagName("form"));
        // form.findElement(By.className("btn-primary")).click();

        driver.findElement(By.cssSelector("form input[type=submit]")).click();
    }





}
