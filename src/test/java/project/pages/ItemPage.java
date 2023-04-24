package project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class ItemPage {
    public WebDriver driver;

    public ItemPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//button[@data-auto='wishlist-button']")
    private WebElement favButton;

    @FindBy(xpath = "//*[contains(text(), 'Оставить отзыв')]/..")
    private WebElement feedbackButton;

    @FindBy(xpath = "//*[@id='scroll-to-reviews-list']/div[1]/div/div[3]/div[4]/div[2]/div/div[1]/div/button")
    private WebElement likeButton;

    @FindBy(xpath = "//*[@data-baobab-name='favorites']")
    private WebElement goToFav;

    public void addToFav() {
        favButton.click();
    }

    public boolean addToCart() {
        Class<? extends WebDriver> driverClass = driver.getClass();
        WebElement cartButton;
        if (driverClass.equals(ChromeDriver.class)) {
            cartButton = driver.findElement(By.xpath("//*[@id='cardAddButton']/div[1]/div/div/div/div/div[4]/div[4]/div[1]/div[1]/button"));
            cartButton.click();
            return driver.findElement(By.xpath("//h2[text()='Товар успешно добавлен в корзину']")).isDisplayed();
        } else if (driverClass.equals(FirefoxDriver.class)) {
            cartButton = driver.findElement(By.xpath("//*[@id='cardAddButton']/div[1]/div/div/div/div/div[4]/div[4]/div/button"));
            cartButton.click();
            return driver.findElement(By.xpath("//h2[text()='Товар успешно добавлен в корзину']")).isDisplayed();
        }
        return false;
    }

    public void continueShopping() {
        WebElement goToCart = driver.findElement(By.xpath("//button[@aria-label='Закрыть']"));
        goToCart.click();
    }

    public String addMoreToCart() {
        Class<? extends WebDriver> driverClass = driver.getClass();
        WebElement moreButton;
        if (driverClass.equals(ChromeDriver.class)) {
            moreButton = driver.findElement(By.xpath("//*[@id='cardAddButton']/div[1]/div/div/div/div/div[4]/div[4]/div[1]/div[1]/div/div[2]/button/span"));
            moreButton.click();
            WebElement amount = driver.findElement(By.xpath("//*[@id='cardAddButton']/div[1]/div/div/div/div/div[4]/div[4]/div[1]/div[1]/div/a"));
            return amount.getText();
        } else if (driverClass.equals(FirefoxDriver.class)) {
            moreButton = driver.findElement(By.xpath("//*[@id='cardAddButton']/div[1]/div/div/div/div/div[4]/div[4]/div[1]/div/div[2]/button/span"));
            moreButton.click();
            WebElement amount = driver.findElement(By.xpath("//*[@id='cardAddButton']/div[1]/div/div/div/div/div[4]/div[4]/div[1]/div/a"));
            return amount.getText();
        }
        return "";
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        //wait.until(ExpectedConditions.elementToBeClickable(more));
        //new Actions(driver).pause(Duration.ofSeconds(2)).moveToElement(more).click(more).perform();
    }

    public String deleteFromCart() {
        Class<? extends WebDriver> driverClass = driver.getClass();
        WebElement lessButton;
        if (driverClass.equals(ChromeDriver.class)) {
            lessButton = driver.findElement(By.xpath("//*[@id='cardAddButton']/div[1]/div/div/div/div/div[4]/div[4]/div[1]/div[1]/div/div[1]/button/span"));
            lessButton.click();
            WebElement amount = driver.findElement(By.xpath("//*[@id='cardAddButton']/div[1]/div/div/div/div/div[4]/div[4]/div[1]/div[1]/div/a"));
            return amount.getText();
        } else if (driverClass.equals(FirefoxDriver.class)) {
            lessButton = driver.findElement(By.xpath("//*[@id='cardAddButton']/div[1]/div/div/div/div/div[4]/div[4]/div[1]/div/div[1]/button/span"));
            lessButton.click();
            WebElement amount = driver.findElement(By.xpath("//*[@id='cardAddButton']/div[1]/div/div/div/div/div[4]/div[4]/div[1]/div/a"));
            return amount.getText();
        }
        return "";
    }

    public void goToFav() {
        goToFav.click();
    }

    public void goToCart() {
        WebElement goToCart = driver.findElement(By.xpath("//div[@data-zone-name='goToCart']/a/span"));
        goToCart.click();
    }

    public void leaveFeedback() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        while (!feedbackButton.isDisplayed()) {
            js.executeScript("window.scrollBy(0, 1000);");
        }
        feedbackButton.click();
    }

    public void likeFeedback() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        while (!likeButton.isDisplayed()) {
            js.executeScript("window.scrollBy(0, 1000);");
        }
        likeButton.click();
    }

    public String getLikes() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        while (!likeButton.isDisplayed()) {
            js.executeScript("window.scrollBy(0, 1000);");
        }
        new Actions(driver).pause(Duration.ofSeconds(2)).perform();
        return likeButton.getText();
    }

}
