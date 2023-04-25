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
import project.pages.CartPage;
import project.pages.FavouritesPage;
import project.pages.ItemPage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ItemTest {
    public static WebDriver driver;
    public static ItemPage itemPage;
    public static FavouritesPage favPage;
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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));

        itemPage = new ItemPage(driver);
        favPage = new FavouritesPage(driver);
        cartPage = new CartPage(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testAddAndRemoveFromFav() {
        driver.get(ConfProperties.getProperty("itempage"));
        driver.manage().window().maximize();
        itemPage.addToFav();
        itemPage.goToFav();
        assertEquals("Кукла Barbie Кем быть? Астронавт 29 см, GFX24", favPage.getFirstItem());
        favPage.removeFromFav();
        driver.navigate().refresh();
        assertTrue(favPage.isFavouriteEmpty());
    }

    @Test
    public void testAddAndRemoveToCart() {
        driver.get(ConfProperties.getProperty("itempage"));
        driver.manage().window().maximize();
        assertTrue(itemPage.addToCart());
        itemPage.goToCart();
        assertTrue(cartPage.checkItem("Кукла Barbie к 60-летию Кем быть? Космонавт GFX24"));
        cartPage.removeFromCart();
        driver.navigate().refresh();
        assertTrue(cartPage.isCartEmpty());
    }

    @Test
    public void testAddMoreOrDeleteCart() {
        driver.get(ConfProperties.getProperty("itempage"));
        driver.manage().window().maximize();
        assertTrue(itemPage.addToCart());
        itemPage.continueShopping();
        assertEquals("2", itemPage.addMoreToCart());
        assertEquals("3", itemPage.addMoreToCart());
        assertEquals("2", itemPage.deleteFromCart());
        assertEquals("1", itemPage.deleteFromCart());
        assertEquals("0", itemPage.deleteFromCart());
    }

    @Test
    public void testLikeFeedback() {
        driver.get(ConfProperties.getProperty("itempage"));
        driver.manage().window().maximize();
        assertEquals("Нравится\n3", itemPage.getLikes());
        itemPage.likeFeedback();
        assertEquals("Нравится\n4", itemPage.getLikes());
        itemPage.likeFeedback();
        assertEquals("Нравится\n3", itemPage.getLikes());
    }

    @Test
    public void testFollowPrice() {
        driver.get(ConfProperties.getProperty("itempage"));
        driver.manage().window().maximize();
        assertTrue(itemPage.followPrice());
    }

    @Test
    public void testCompare(){
        driver.get(ConfProperties.getProperty("itempage"));
        driver.manage().window().maximize();
        assertTrue(itemPage.addToCompare());
    }

    @Test
    public void testMakingOrder() {
        driver.get(ConfProperties.getProperty("itempage"));
        driver.manage().window().maximize();
        assertTrue(itemPage.addToCart());
        itemPage.goToCart();
        assertTrue(cartPage.checkItem("Кукла Barbie к 60-летию Кем быть? Космонавт GFX24"));
        cartPage.makeOrder();
        assertTrue(cartPage.checkOrderInfo().matches("Получатель\\sАнастасия Морозова, \\+7911\\d*"));
    }

}
