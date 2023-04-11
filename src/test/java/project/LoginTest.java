package project;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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
    public static void setup() {
        //определение пути до драйвера и его настройка
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        driver = new ChromeDriver();
        marketPage = new MarketPage(driver);
        loginPage = new LoginPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(ConfProperties.getProperty("marketpage"));
    }

    @Test
    public void loginTest(){
        marketPage.clickLoginBtn();
        loginPage.inputLogin(ConfProperties.getProperty("testlogin"));
        loginPage.clickLoginBtn();
        loginPage.inputPasswd("testpassword");
        loginPage.clickLoginBtn();

        assertEquals(ConfProperties.getProperty("testmail"), marketPage.checkUserIcon());
    }
}
