package project.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import project.ConfProperties;
import project.pages.CatalogPage;
import project.pages.MarketPage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MarketTest {

    public static WebDriver driver;
    public static MarketPage marketPage;
    public static CatalogPage loginPage;

    @BeforeAll
    public static void setupDrivers() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--user-data-dir=C:\\Users\\Ana\\AppData\\Local\\Google\\Chrome\\User Data");
        options.addArguments("--profile-directory=Default");
        options.addArguments("--remote-allow-origins=*");
        // options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        marketPage = new MarketPage(driver);
        loginPage = new CatalogPage(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void loginTest() {
        driver.get(ConfProperties.getProperty("marketpage"));
        driver.manage().window().maximize();
        assertEquals("Анастасия Морозова\nanamrzvtest@yandex.ru", marketPage.checkUserInfo());
        assertTrue(marketPage.isCartEmpty());
        assertTrue(marketPage.isFavouriteEmpty());
        assertTrue(marketPage.isOrdersEmpty());
    }

    @Test
    public void testNavigationToCatalog() {
        driver.get(ConfProperties.getProperty("marketpage"));
        driver.manage().window().maximize();
        assertEquals("Детские игрушки и игры", marketPage.chooseThemeInCatalog("Игрушки и игры"));
    }

}
