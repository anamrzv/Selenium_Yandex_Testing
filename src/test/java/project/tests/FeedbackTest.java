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
import project.pages.FeedbackPage;
import project.pages.ItemPage;

import java.time.Duration;

public class FeedbackTest {
    public static WebDriver driver;
    public static FeedbackPage feedbackPage;

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

        feedbackPage = new FeedbackPage(driver);
    }

//    @AfterEach
//    public void tearDown() {
//        driver.quit();
//    }

    @Test
    public void testAddGoodFeedback() {
        driver.get(ConfProperties.getProperty("feedbackpage"));
        driver.manage().window().maximize();
        feedbackPage.rateAsGood();
        feedbackPage.writeGoodComment("Хороший товар");
        feedbackPage.sendAsAnon();
        feedbackPage.clickNext();
        feedbackPage.setUserExperience("Меньше месяца");
        feedbackPage.sendFeedback();
    }

}
