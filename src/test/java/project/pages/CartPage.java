package project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {
    public WebDriver driver;

    @FindBy(xpath = "//*[@data-baobab-name='deleteAll']")
    private WebElement deleteButton;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div/main/div[4]/div/div[2]/div/div/div/div/div/div/div[2]/div/div/div/div/div/div/div/div/div[3]/div[2]/div/div[2]/button[2]")
    private WebElement deleteFinallyButton;

    @FindBy(xpath = "//*[@data-baobab-name='checkoutButton']")
    private WebElement goToCheckout;

    public CartPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public boolean isCartEmpty() {
        if (driver.findElement(By.xpath("//*[contains(text(), 'Сложите в корзину нужные товары')]"))
                .isDisplayed()) return true;
        else return false;
    }

    public boolean isCartEmptyOld() {
        if (driver.findElement(By.xpath("//*[contains(text(), 'Может пригодиться')]"))
                .isDisplayed()) return true;
        else return false;
    }

    public boolean checkItem(String name) {
        if (driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/main/div[4]/div/div[2]/div/div/div/div/div/div/div[3]/div/div/div/div[1]/div/div[2]/div/div/div/div/div/div/div/div/section/div/div/div/div/div/div/div/div/div/div/div[3]/div/div[1]/div/span/a"))
                .getText().equals(name)) return true;
        else return false;
    }

    public void removeFromCart() {
        new Actions(driver).pause(Duration.ofSeconds(5)).click(deleteButton).pause(Duration.ofSeconds(2)).perform();
        new Actions(driver).pause(Duration.ofSeconds(5)).click(deleteFinallyButton).pause(Duration.ofSeconds(2)).perform();
    }

    public void makeOrder() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[2]/div/main/div[4]/div/div[2]/div/div/div/div/div/div/div[3]/div/div/div/div[3]/div/div[1]/div/div/div/section/div[2]/div/div/div/span/div/button")));
        new Actions(driver).pause(Duration.ofSeconds(2)).click(goToCheckout).pause(Duration.ofSeconds(1)).perform();
    }

    public String checkOrderInfo() {
        Class<? extends WebDriver> driverClass = driver.getClass();
        if (driverClass.equals(FirefoxDriver.class)) {
            return driver.findElement(By.xpath("//html/body/div[1]/div[2]/div/div[4]/div/div/div/div[1]/div/div/div/div/div[2]/div/div[1]/div[3]/div/div/div/div[1]/div[2]/div[2]/div/button")).getText();
        }
        else return driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[4]/div/div/div/div[1]/div/div/div/div/div[2]/div/div[1]/div[4]/div/div/section/div[2]/ul/li/div/div/div/div[1]/div[2]/div[2]/div[2]/div/button")).getText();
    }
}
