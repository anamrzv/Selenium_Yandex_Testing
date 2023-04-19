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
import project.pages.ItemPage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ItemTest {
    public static WebDriver driver;
    public static ItemPage itemPage;

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
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testAddToFav() {
        driver.get(ConfProperties.getProperty("itempage"));
        driver.manage().window().maximize();
        itemPage.addToFav();
        assertEquals("Кукла Barbie Кем быть? Астронавт 29 см, GFX24", itemPage.goToFavAndCheckItem());
    }

    @Test
    public void testAddToCart() {
        driver.get(ConfProperties.getProperty("itempage"));
        driver.manage().window().maximize();
        assertTrue(itemPage.addToCart());
        assertEquals("Кукла Barbie к 60-летию Кем быть? Космонавт GFX24", itemPage.goToCartAndCheckItem());
    }
}
