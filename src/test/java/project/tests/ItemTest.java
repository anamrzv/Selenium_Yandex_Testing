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

public class ItemTest {

    public List<WebDriver> driverList;

    public static WebDriver chromeDriver;
    public static WebDriver firefoxDriver;
    public static WebDriver driver;
    public static ItemPage itemPage;
    public static FavouritesPage favPage;
    public static CartPage cartPage;
    public static ComparePage comparePage;
    public static LoginPage loginPage;
    public static MarketPage marketPage;

    @BeforeAll
    public static void setupDrivers() {
        WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();
    }

    @BeforeEach
    public void setup() {
        driverList = new ArrayList<>();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--user-data-dir=C:\\Users\\Ana\\AppData\\Local\\Google\\Chrome\\User Data");
        options.addArguments("--profile-directory=Default");
        chromeDriver = new ChromeDriver(options);

        FirefoxOptions firefoxOptions = new FirefoxOptions();
        FirefoxProfile profile = new FirefoxProfile(new File("C:\\Users\\Ana\\AppData\\Local\\Mozilla\\Firefox\\Profiles\\7w7mb3k8.default-release-1682418259766"));
        firefoxOptions.setProfile(profile);
        firefoxDriver = new FirefoxDriver(firefoxOptions);

        chromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
        firefoxDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driverList.add(chromeDriver);
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
    public void testAddAndRemoveFromFav() {
        driverList.forEach(driver -> {
            itemPage = new ItemPage(driver);
            favPage = new FavouritesPage(driver);
            marketPage = new MarketPage(driver);
            loginPage = new LoginPage(driver);

            driver.get(ConfProperties.getProperty("itempage2"));
            driver.manage().window().maximize();
            Class<? extends WebDriver> driverClass = driver.getClass();
            if (driverClass.equals(FirefoxDriver.class)) {
                login();
            }
            itemPage.addToFav();
            itemPage.goToFav();
            if (driverClass.equals(FirefoxDriver.class)) {
                assertTrue(favPage.isItemInFav("Кофе в зернах BUSHIDO Red Katana 1 кг"));
            } else assertTrue(favPage.isItemInFav("Кофе в зернах Bushido Red Katana, 1 кг."));
            favPage.removeFromFav();
            driver.navigate().refresh();
            assertTrue(favPage.isFavouriteEmpty());
            driver.quit();
        });
    }


    @Test //GOOD
    public void testAddAndRemoveToCart() {
        driverList.forEach(driver -> {
            itemPage = new ItemPage(driver);
            cartPage = new CartPage(driver);
            marketPage = new MarketPage(driver);
            loginPage = new LoginPage(driver);

            driver.get(ConfProperties.getProperty("itempage2"));
            driver.manage().window().maximize();
            Class<? extends WebDriver> driverClass = driver.getClass();
            if (driverClass.equals(FirefoxDriver.class)) {
                login();
            }
            assertTrue(itemPage.addToCart());
            itemPage.goToCart();
            if (driverClass.equals(FirefoxDriver.class)) {
                assertTrue(cartPage.checkItem("Кофе в зернах BUSHIDO Red Katana 1 кг"));
            } else assertTrue(cartPage.checkItem("Кофе в зернах Bushido Red Katana, 1 кг."));
            cartPage.removeFromCart();
            driver.navigate().refresh();
            assertTrue(cartPage.isCartEmpty());
            driver.quit();
        });
    }

    @Test //GOOD
    public void testAddMoreOrDeleteCart() {
        driverList.forEach(driver -> {
            itemPage = new ItemPage(driver);
            marketPage = new MarketPage(driver);
            loginPage = new LoginPage(driver);

            driver.get(ConfProperties.getProperty("itempage2"));
            driver.manage().window().maximize();
            Class<? extends WebDriver> driverClass = driver.getClass();
            if (driverClass.equals(FirefoxDriver.class)) {
                login();
            }
            assertTrue(itemPage.addToCart());
            itemPage.continueShopping();
            assertEquals("2", itemPage.addMoreToCart());
            assertEquals("3", itemPage.addMoreToCart());
            assertEquals("2", itemPage.deleteFromCart());
            assertEquals("1", itemPage.deleteFromCart());
            driver.quit();
        });
    }

    @Test //GOOD
    public void testLikeFeedback() {
        driverList.forEach(driver -> {
            itemPage = new ItemPage(driver);

            driver.get(ConfProperties.getProperty("itempage"));
            driver.manage().window().maximize();
            assertEquals("Нравится\n2", itemPage.getLikes());
            itemPage.likeFeedback();
            assertEquals("Нравится\n3", itemPage.getLikes());
            itemPage.likeFeedback();
            assertEquals("Нравится\n2", itemPage.getLikes());
            driver.quit();
        });
    }

    @Test //GOOD
    public void testFollowPrice() {
        driverList.forEach(driver -> {
            itemPage = new ItemPage(driver);
            marketPage = new MarketPage(driver);
            loginPage = new LoginPage(driver);

            driver.get(ConfProperties.getProperty("itempage"));
            driver.manage().window().maximize();
            Class<? extends WebDriver> driverClass = driver.getClass();
            if (driverClass.equals(FirefoxDriver.class)) {
                login();
            }
            assertTrue(itemPage.followPrice());
            driver.quit();
        });
    }

    @Test //GOOD
    public void testCompare() {
        driverList.forEach(driver -> {
            itemPage = new ItemPage(driver);
            comparePage = new ComparePage(driver);

            driver.get(ConfProperties.getProperty("itempage"));
            driver.manage().window().maximize();
            itemPage.addToCompare();
            itemPage.goToCompare();
            assertTrue(comparePage.isItemInCompare("Кукла Barbie \"Кем быть?\" 29 см, GFX23"));
            driver.quit();
        });
    }

    @Test //GOOD
    public void testMakingOrder() {
        driverList.forEach(driver -> {
            itemPage = new ItemPage(driver);
            marketPage = new MarketPage(driver);
            loginPage = new LoginPage(driver);
            cartPage = new CartPage(driver);

            driver.get(ConfProperties.getProperty("itempage2"));
            driver.manage().window().maximize();
            Class<? extends WebDriver> driverClass = driver.getClass();
            if (driverClass.equals(FirefoxDriver.class)) {
                login();
            }
            assertTrue(itemPage.addToCart());
            itemPage.goToCart();
            if (driverClass.equals(FirefoxDriver.class)) {
                assertTrue(cartPage.checkItem("Кофе в зернах BUSHIDO Red Katana 1 кг"));
            } else assertTrue(cartPage.checkItem("Кофе в зернах Bushido Red Katana, 1 кг."));
            cartPage.makeOrder();
            assertTrue(cartPage.checkOrderInfo().matches("Получатель\\sАнастасия Морозова, \\+7911\\d*"));
            driver.quit();
        });
    }

}
