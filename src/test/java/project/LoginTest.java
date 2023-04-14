package project;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import project.pages.LoginPage;
import project.pages.MarketPage;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest {

    public static WebDriver driver;
    public static MarketPage marketPage;
    public static LoginPage loginPage;

    @BeforeAll
    public static void setup2() {
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

        marketPage = new MarketPage(driver);
        loginPage = new LoginPage(driver);
    }

    @Test
    public void loginTest(){
        driver.get(ConfProperties.getProperty("marketpage"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        assertEquals("Анастасия Морозова\nanamrzvtest@yandex.ru", marketPage.checkUserIcon());
    }
}
