package project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MarketPage {
    public WebDriver driver;

    public MarketPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//*[contains(text(), 'Войти')]/..")
    private WebElement loginBtn;
    @FindBy(xpath = "//*[contains(@src, '//avatars.mds.yandex.net/get-yapic/0/0-0/islands-retina-middle')]/..")
    private WebElement userIcon;
    @FindBy(xpath = "//*[contains(@class, '_3ckxI _3L43M')]/..")
    private WebElement userMail;

    @FindBy(xpath = "//*[contains(@data-auto, 'public-user-info')]")
    private WebElement userName;

    @FindBy(xpath = "//*[contains(@title, 'Корзина')]")
    private WebElement cart;

    @FindBy(xpath = "//*[contains(@title, 'Избранное')]")
    private WebElement favourite;

    @FindBy(xpath = "//*[contains(@title, 'Заказы')]")
    private WebElement orders;

    @FindBy(xpath = "//span[text()='Каталог']")
    private WebElement catalog;

    public String checkUserInfo() {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@src, '//avatars.mds.yandex.net/get-yapic/0/0-0/islands-retina-middle')]/..")));
        if (userIcon != null) {
            userIcon.click();
            if (userMail != null) return userMail.getText();
            else return null;
        } else return null;
    }

    public boolean isCartEmpty() {
        cart.click();
        if (driver.findElement(By.xpath("//*[contains(text(), 'Сложите в корзину нужные товары')]"))
                .isDisplayed()) return true;
        else return false;
    }

    public boolean isFavouriteEmpty() {
        favourite.click();
        if (driver.findElement(By.xpath("//*[contains(text(), 'Ещё не готовы к покупке?')]"))
                .isDisplayed()) return true;
        else return false;
    }

    public boolean isOrdersEmpty() {
        orders.click();
        if (driver.findElement(By.xpath("//*[contains(text(), 'Здесь будет храниться история ваших заказов')]"))
                .isDisplayed()) return true;
        else return false;
    }

    public String chooseThemeInCatalog(String theme) {
        catalog.click();
        driver.findElement(By.xpath("//a[text()='" + theme + "']")).click();
        WebElement header = driver.findElement(By.xpath("//h1[@data-auto='title']"));
        return header.getText();
    }

}
