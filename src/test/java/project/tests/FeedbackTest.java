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

public class FeedbackTest {
    public List<WebDriver> driverList;
    public static WebDriver chromeDriver;
    public static WebDriver firefoxDriver;
    public static FeedbackPage feedbackPage;
    public static MarketPage marketPage;
    public static LoginPage loginPage;

    @BeforeAll
    public static void setupDrivers() {
        WebDriverManager.chromedriver().setup();
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

    @Test
    public void testAddGoodFeedback() {
        driverList.forEach(driver -> {
            marketPage = new MarketPage(driver);
            loginPage = new LoginPage(driver);

            driver.get(ConfProperties.getProperty("marketpage"));
            driver.manage().window().maximize();
            Class<? extends WebDriver> driverClass = driver.getClass();
            if (driverClass.equals(FirefoxDriver.class)) {
                login();
            }
            driver.get(ConfProperties.getProperty("feedbackpage"));
            driver.manage().window().maximize();
            feedbackPage.rateAsGood();
            feedbackPage.writeGoodComment("Хороший товар");
            feedbackPage.sendAsAnon();
            feedbackPage.clickNext();
            feedbackPage.setUserExperience("Меньше месяца");
            assertTrue(feedbackPage.sendFeedback());
            driver.quit();
        });
    }

}
