package project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import project.ConfProperties;

import java.time.Duration;

public class MarketPage {
    public WebDriver driver;

    public MarketPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//*[contains(text(), 'Войти')]/..")
    private WebElement loginBtn;
    @FindBy(xpath = "/html/body/div[1]/header/div/div/div/noindex[2]/nav/ul/li[5]/div/div/div/div[1]/div/button/div/img")
    private WebElement userIcon;
    @FindBy(xpath = "//*[contains(@class, '_3ckxI _3L43M')]/..")
    private WebElement userMail;

    @FindBy(xpath = "//*[contains(@data-auto, 'public-user-info')]")
    private WebElement userName;

    @FindBy(xpath = "//*[contains(@title, 'Корзина')]")
    private WebElement cart;

    @FindBy(xpath = "//*[contains(@title, 'Избранное')]")
    private WebElement favourite;

    @FindBy(xpath = "//*[@data-baobab-name='orders']")
    private WebElement orders;

    @FindBy(xpath = "//span[text()='Каталог']")
    private WebElement catalog;

    @FindBy(xpath = "//*[contains(text(), 'Искать товары')]/..")
    private WebElement searchField;

    @FindBy(xpath = "//*[contains(text(), 'Найти')]/..")
    private WebElement searchButton;

    @FindBy(xpath = "//*[@id='hyperlocation-unified-dialog-anchor']/button")
    private WebElement location;

    @FindBy(xpath ="//*[@aria-owns='react-autowhatever-address']/div/div/div/div/input")
    private WebElement locationField;

    public String checkUserInfo() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/header/div/div/div/noindex[2]/nav/ul/li[5]/div/div/div/div[1]/div/button/div/img")));
        if (userIcon != null) {
            new Actions(driver).pause(Duration.ofSeconds(2)).click(userIcon).pause(Duration.ofSeconds(1)).perform();
            if (userMail != null) return userMail.getText();
            else return null;
        } else return null;
    }

    public void goToCart() {
        cart.click();
    }

    public void goToFavourites() {
        favourite.click();
    }

    public void goToOrders() {
        orders.click();
    }

    public String chooseThemeInCatalog(String theme) {
        catalog.click();
        driver.findElement(By.xpath("//a[text()='" + theme + "']")).click();
        WebElement header = driver.findElement(By.xpath("//h1[@data-auto='title']"));
        return header.getText();
    }

    public String searchByString(String description) {
        searchField.sendKeys(description);
        searchButton.click();
        WebElement header = driver.findElement(By.xpath("//h1[@data-auto='title']"));
        return header.getText();
    }

    public void clickOnLocation() {
        location.click();
    }

    public void inputNewLocation(String newLocation) {
        driver.findElement(By.xpath("//*[contains(text(), 'Добавить новый адрес')]")).click();
        locationField.clear();
        locationField.sendKeys(newLocation);
        driver.findElement(By.xpath("//*[@id='react-autowhatever-address--item-1']")).click();
        WebElement here = driver.findElement(By.xpath("//*[contains(text(), 'Привезти сюда')]"));
        new Actions(driver).click(here).pause(Duration.ofSeconds(2)).perform();
    }

    public String getLocation() {
        return location.getText();
    }

    public void clickLoginBtn() {
        loginBtn.click();
    }

}
