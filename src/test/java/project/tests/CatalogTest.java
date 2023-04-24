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

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CatalogTest {
    public static WebDriver driver;
    public static CatalogPage catalogPage;

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

        catalogPage = new CatalogPage(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testDeliveryDateFilter() {
        driver.get(ConfProperties.getProperty("catalogpage"));
        driver.manage().window().maximize();
        assertTrue(catalogPage.filterByDeliveryDate("До 5 дней").matches("^Найдено [1-9]+[0-9]* товар.*$"));
    }

    @Test
    public void testPriceFilter() {
        driver.get(ConfProperties.getProperty("catalogpage"));
        driver.manage().window().maximize();
        assertTrue(catalogPage.filterByPriceRange("100000", "1200000").matches("^Найдено [1-9]+[0-9]* товар.*$"));
        assertEquals("Товаров нет", catalogPage.filterByPriceRange("", "10"));
    }

    @Test
    public void testCheckboxFilter() {
        driver.get(ConfProperties.getProperty("catalogpage"));
        driver.manage().window().maximize();
        assertTrue(catalogPage.filterByCheckbox("для девочек").matches("^Найдено [1-9]+[0-9]* товар.*$"));
        assertTrue(catalogPage.filterByCheckbox("Barbie").matches("^Найдено [1-9]+[0-9]* товар.*$"));
        assertTrue(catalogPage.filterByCheckbox("Ресейл").matches("^Найдено [1-9]+[0-9]* товар.*$"));
        catalogPage.sortByPrice();
        assertEquals("Кукла Barbie \"Кем быть?\" 29 см, GFX23 космонавт", catalogPage.returnFirstItemName());
    }


}
