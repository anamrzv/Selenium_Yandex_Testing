package project;

import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class LoginTest {

    @BeforeAll
    public static void setup() {
        //определение пути до драйвера и его настройка
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(ConfProperties.getProperty("marketpage"));
    }
}
