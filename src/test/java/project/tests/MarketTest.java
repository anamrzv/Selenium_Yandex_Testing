package project.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import project.ConfProperties;
import project.pages.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MarketTest {

    public static WebDriver driver;
    public static MarketPage marketPage;
    public static FavouritesPage favPage;
    public static OrdersPage ordersPage;

    public static CartPage cartPage;

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
        favPage = new FavouritesPage(driver);
        ordersPage = new OrdersPage(driver);
        cartPage = new CartPage(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void loginTest() {
        driver.get(ConfProperties.getProperty("marketpage"));
        driver.manage().window().maximize();
        marketPage.goToFavourites();
        assertTrue(favPage.isFavouriteEmpty());
        marketPage.goToCart();
        assertTrue(cartPage.isCartEmpty());
        marketPage.goToOrders();
        assertTrue(ordersPage.isOrdersEmpty());
        assertEquals("Анастасия Морозова\nanamrzvtest@yandex.ru", marketPage.checkUserInfo());
    }

    @Test
    public void testNavigationToCatalog() {
        driver.get(ConfProperties.getProperty("marketpage"));
        driver.manage().window().maximize();
        assertEquals("Детские игрушки и игры", marketPage.chooseThemeInCatalog("Игрушки и игры"));
    }

    @Test
    public void testSearchBar() {
        driver.get(ConfProperties.getProperty("marketpage"));
        driver.manage().window().maximize();
        marketPage.searchByString("Самкат");
        assertEquals("Самокат", marketPage.chooseThemeInCatalog("Игрушки и игры"));
    }

    @Test
    public void testNewLocation(){
        driver.get(ConfProperties.getProperty("marketpage"));
        driver.manage().window().maximize();
        marketPage.clickOnLocation();
        marketPage.inputNewLocation("Москва");
        assertEquals("Москва, \nд. 12", marketPage.getLocation());
    }

}
