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
import project.pages.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest {
    public static WebDriver driver;
    public static LoginPage loginPage;
    public static MarketPage marketPage;

    @BeforeAll
    public static void setupDrivers() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        //options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        loginPage = new LoginPage(driver);
        marketPage = new MarketPage(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void loginTestReal() {
        driver.get(ConfProperties.getProperty("loginpage"));
        driver.manage().window().maximize();
        marketPage.clickLoginBtn();
        loginPage.inputLogin(ConfProperties.getProperty("testlogin"));
        loginPage.clickLoginBtn();
        loginPage.inputPasswd(ConfProperties.getProperty("testpassword"));
        loginPage.clickLoginBtn();
        assertEquals("Анастасия Морозова\nanamrzvtest@yandex.ru", marketPage.checkUserInfo());
    }
}
