import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    // TODO : tests simples : présence des boutons dans la navbar ; texte du footer ; navigation sur la page détails * 2

    @Test
    public void verifyTitle(){
        // driver.get("https://localhost:44347/");
        assertThat(driver.getTitle(), containsString("AppRestaurants.Web"));
    }

    @Test
    public void verifyTopFive(){
         //Todo : Trouver une autre condition qui ne dépend pas du fait d'avoir 5 restaurants en base 
        // driver.get("https://localhost:44353/");
        assertThat(driver.findElements(By.tagName("tr")).size(), equalTo(6));
    }

    @Test
    public void verifyRestaurantCreation(){

    // 2 | setWindowSize | 1219x736 | 
    driver.manage().window().setSize(new Dimension(1219, 736));
    // 3 | click | linkText=Gestion des Restaurants | 
    driver.findElement(By.linkText("Gestion des restaurants")).click();

    int nombreLigne = driver.findElements(By.tagName("tr")).size();
    // 4 | click | linkText=Ajouter un nouveau restaurant | 
    driver.findElement(By.linkText("Créer un restaurant")).click();
    // 5 | click | id=nom | 
    // 6 | type | id=nom | restaurant du bonheur
    driver.findElement(By.id("Nom")).sendKeys("restaurant du test1");
    // 7 | click | id=numero_telephone | 
    // 8 | type | id=numero_telephone | 0671615144
    driver.findElement(By.id("Telephone")).sendKeys("0671615144");
    // 9 | type | id=adresse_mail | Frederic.pouchan@gmail.com
    driver.findElement(By.id("Email")).sendKeys("Frederic.poucha@gmail.com");
    driver.findElement(By.id("Details")).sendKeys("dzjdzjdzjdz");
    // 14 | type | id=address_street | 556 rue des copains
    driver.findElement(By.id("Adresse_Numero")).sendKeys("556");
    driver.findElement(By.id("Adresse_Rue")).sendKeys("rue des copains");
    // 15 | type | id=address_codePostal | 87667
    driver.findElement(By.id("Adresse_CodePostal")).sendKeys("87667");
    // 16 | type | id=address_ville | Fontaine
    driver.findElement(By.id("Adresse_Ville")).sendKeys("Fontaine");
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
        // 2 | setWindowSize | 1219x736 | 
        driver.manage().window().setSize(new Dimension(1219, 736));
        // 3 | click | linkText=Gestion des Restaurants | 
        driver.findElement(By.linkText("Gestion des restaurants")).click();

        int nombreLigne = driver.findElements(By.tagName("tr")).size();

        // 4 | click | linkText=Supprimer | 
        driver.findElement(By.linkText("Supprimer")).click();

        // 5 | click | css=.btn | 
        submitForm();

        System.out.println("Nombre de ligne avant la suppression : " + nombreLigne );
        System.out.println("Nombre de ligne après la suppression : " +  driver.findElements(By.tagName("tr")).size());

        assertThat(driver.findElements(By.tagName("tr")).size(), equalTo(nombreLigne - 1));

    }


    // @Test
    // public void modifyARestaurant(){
    //     // Test name: modifierUnRestaurant
    //     // Step # | name | target | value
    //     // 1 | open | / | 
    //     // 2 | setWindowSize | 1219x736 | 
    //     driver.manage().window().setSize(new Dimension(1219, 736));
    //     // 3 | click | linkText=Gestion des Restaurants | 
    //     driver.findElement(By.linkText("Gestion des Restaurants")).click();

    //     String nomDuPremierRestaurant = driver.findElement(By.cssSelector("body > div > main > table > tbody > tr:nth-child(1) > td:nth-child(1)")).getText();

    //     // 4 | click | linkText=Modifier | 
    //     driver.findElement(By.linkText("Modifier")).click();
    //     // 6 | type | id=nom | Le Restaurant est modifie
    //     driver.findElement(By.id("nom")).clear();

    //     Random random = new Random();

    //     driver.findElement(By.id("nom")).sendKeys("Le Restaurant est modifié " + random.nextInt(200)); //Pour s'assurer que le nom avant et après soient toujours différents 
    //     // 7 | click | css=.btn-success | 
    //     driver.findElement(By.cssSelector(".btn-success")).click();

    //     System.out.println("Avant la modification : " + nomDuPremierRestaurant);
    //     System.out.println("Après la modification : " + driver.findElement(By.cssSelector("body > div > main > table > tbody > tr:nth-child(1) > td:nth-child(1)")).getText());

    //     assertThat(driver.findElement(By.cssSelector("body > div > main > table > tbody > tr:nth-child(1) > td:nth-child(1)")).getText(), not(nomDuPremierRestaurant));
    // }

    @Test
    public void addAGrade(){
        driver.manage().window().setSize(new Dimension(1219, 736));
        driver.findElement(By.linkText("Gestion des notes")).click();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        LocalDate ancienneDate = LocalDate.parse(driver.findElement(By.cssSelector("body > div > main > table > tbody > tr:nth-child(1) > td:nth-child(2)")).getText(), formatter);

        driver.findElement(By.linkText("Noter un restaurant")).click();

        driver.findElement(By.id("Note")).sendKeys("5");
        
        driver.findElement(By.id("DateDerniereVisite")).sendKeys(ancienneDate.plusDays(1).format(formatter));

        driver.findElement(By.id("Commentaire")).sendKeys("c\'est nul");

        submitForm();
        
        String nouvelleDate = driver.findElement(By.cssSelector("body > div > main > table > tbody > tr:nth-child(1) > td:nth-child(2)")).getText();

        System.out.println("Avant la modification : " + ancienneDate);
        System.out.println("Après la modification : " + driver.findElement(By.cssSelector("body > div > main > table > tbody > tr:nth-child(1) > td:nth-child(2)")).getText());

        assertThat(nouvelleDate, not(ancienneDate));

    }

    private void submitForm(){
        
        // WebElement form = driver.findElement(By.tagName("form"));
        // form.findElement(By.className("btn-primary")).click();
        driver.findElement(By.cssSelector("form input[type=submit]")).click();
    }





}
