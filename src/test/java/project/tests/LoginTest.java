package project.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
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

public class LoginTest {
    public List<WebDriver> driverList;
    public static WebDriver chromeDriver;
    public static WebDriver firefoxDriver;
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
        //options.addArguments("--headless");
        chromeDriver = new ChromeDriver(options);
        firefoxDriver = new FirefoxDriver();

        chromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        firefoxDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));

        driverList.add(firefoxDriver);
        driverList.add(chromeDriver);
    }

    @Test
    public void loginTestReal() {
        driverList.forEach(driver -> {
            marketPage = new MarketPage(driver);
            loginPage = new LoginPage(driver);

            driver.get(ConfProperties.getProperty("loginpage"));
            driver.manage().window().maximize();
            marketPage.clickLoginBtn();
            loginPage.inputLogin(ConfProperties.getProperty("testlogin"));
            loginPage.clickLoginBtn();
            loginPage.inputPasswd(ConfProperties.getProperty("testpassword"));
            loginPage.clickLoginBtn();
            assertEquals("Анастасия Морозова\nanamrzvtest@yandex.ru", marketPage.checkUserInfo());
            driver.quit();
        });
    }
}
