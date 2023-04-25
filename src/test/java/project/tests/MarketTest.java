package project.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import project.ConfProperties;
import project.pages.*;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MarketTest {

    public List<WebDriver> driverList;
    public static WebDriver chromeDriver;
    public static WebDriver firefoxDriver;
    public static MarketPage marketPage;
    public static FavouritesPage favPage;
    public static OrdersPage ordersPage;
    public static CartPage cartPage;
    public static LoginPage loginPage;

    @BeforeAll
    public static void setupDrivers() {
        WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();
    }

    @BeforeEach
    public void setup() {
        driverList = new ArrayList<>();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--user-data-dir=C:\\Users\\Ana\\AppData\\Local\\Google\\Chrome\\User Data");
        options.addArguments("--profile-directory=Default");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        //options.addArguments("--headless");
        chromeDriver = new ChromeDriver(options);

        FirefoxOptions firefoxOptions = new FirefoxOptions();
        FirefoxProfile profile = new FirefoxProfile(new File("C:\\Users\\Ana\\AppData\\Local\\Mozilla\\Firefox\\Profiles\\7w7mb3k8.default-release-1682418259766"));
        firefoxOptions.setProfile(profile);
        firefoxDriver = new FirefoxDriver(firefoxOptions);

        chromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        firefoxDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));

        //driverList.add(chromeDriver);
        driverList.add(firefoxDriver);
    }

    public void login() {
        marketPage.clickLoginBtn();
        loginPage.inputLogin(ConfProperties.getProperty("testlogin"));
        loginPage.clickLoginBtn();
        loginPage.inputPasswd(ConfProperties.getProperty("testpassword"));
        loginPage.clickLoginBtn();
    }

    @Test //GOOD
    public void loginTest() {
        driverList.forEach(driver -> {
            favPage = new FavouritesPage(driver);
            marketPage = new MarketPage(driver);
            loginPage = new LoginPage(driver);
            cartPage = new CartPage(driver);

            driver.get(ConfProperties.getProperty("marketpage"));
            driver.manage().window().maximize();
            Class<? extends WebDriver> driverClass = driver.getClass();
            if (driverClass.equals(FirefoxDriver.class)) {
                login();
            }
            marketPage.goToFavourites();
            assertTrue(favPage.isFavouriteEmpty());
            marketPage.goToCart();
            assertTrue(cartPage.isCartEmpty());
            marketPage.goToOrders();
            assertEquals("Анастасия Морозова\nanamrzvtest@yandex.ru", marketPage.checkUserInfo());
            driver.quit();
        });
    }

    @Test //GOOD
    public void testNavigationToCatalog() {
        driverList.forEach(driver -> {
            marketPage = new MarketPage(driver);
            loginPage = new LoginPage(driver);

            driver.get(ConfProperties.getProperty("marketpage"));
            driver.manage().window().maximize();
            Class<? extends WebDriver> driverClass = driver.getClass();
            if (driverClass.equals(FirefoxDriver.class)) {
                login();
            }
            assertEquals("Детские игрушки и игры", marketPage.chooseThemeInCatalog("Игрушки и игры"));
            driver.quit();
        });
    }

    @Test //GOOD
    public void testSearchBar() {
        driverList.forEach(driver -> {
            marketPage = new MarketPage(driver);
            loginPage = new LoginPage(driver);

            driver.get(ConfProperties.getProperty("marketpage"));
            driver.manage().window().maximize();
            Class<? extends WebDriver> driverClass = driver.getClass();
            if (driverClass.equals(FirefoxDriver.class)) {
                login();
            }
            marketPage.searchByString("Самокат");
            assertEquals("Самокат", marketPage.getHeader());
            driver.quit();
        });
    }

    @Test //GOOD
    public void testNewLocation() {
        driverList.forEach(driver -> {
            marketPage = new MarketPage(driver);
            loginPage = new LoginPage(driver);

            driver.get(ConfProperties.getProperty("marketpage"));
            driver.manage().window().maximize();
            Class<? extends WebDriver> driverClass = driver.getClass();
            if (driverClass.equals(FirefoxDriver.class)) {
                login();
            }
            marketPage.clickOnLocation();
            marketPage.inputNewLocation("Москва");
            if (driverClass.equals(ChromeDriver.class)) assertEquals("Пресненская набережная, \nд. 4с1", marketPage.getLocation());
            if (driverClass.equals(FirefoxDriver.class)) assertEquals("Московский проспект, \nд. 148", marketPage.getLocation());
        });
    }

}
