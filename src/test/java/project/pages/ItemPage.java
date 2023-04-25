package project.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ItemPage {
    public WebDriver driver;

    public ItemPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//div[@data-baobab-name='wishlist']/button")
    private WebElement favButton;

    @FindBy(xpath = "//*[contains(text(), 'Оставить отзыв')]/..")
    private WebElement feedbackButton;

    @FindBy(xpath = "//*[@id='scroll-to-reviews-list']/div[1]/div/div[3]/div[4]/div[2]/div/div[1]/div/button")
    private WebElement likeButton;

    @FindBy(xpath = "/html/body/div[1]/div[2]/main/div[4]/div/div/div[3]/div/div/div[3]/a")
    private WebElement goToFav;

    @FindBy(xpath = "//*[@data-baobab-name='comparison']")
    private WebElement compare;

    @FindBy(xpath = "//*[@data-baobab-name='priceSubscribe']")
    private WebElement followPrice;

    public void addToFav() {
        new Actions(driver).pause(Duration.ofSeconds(2)).click(favButton).pause(Duration.ofSeconds(2)).perform();
    }

    public void goToFav() {
        Class<? extends WebDriver> driverClass = driver.getClass();
        if (driverClass.equals(ChromeDriver.class)) {
            WebElement button = driver.findElement(By.xpath("/html/body/div[1]/div[2]/main/div[4]/div/div/div[3]/div/div/div[3]/a"));
            button.click();
        } else goToFav.click();
    }

    public boolean addToCart() {
        Class<? extends WebDriver> driverClass = driver.getClass();
        WebElement cartButton;
        if (driverClass.equals(ChromeDriver.class)) {
            cartButton = driver.findElement(By.xpath("//*[@id=\"cardAddButton\"]/div[1]/div/div/div/div/div[4]/div[4]/div[1]/div[1]/button"));
            cartButton.click();
            return driver.findElement(By.xpath("//h2[text()='Товар успешно добавлен в корзину']")).isDisplayed();
        } else if (driverClass.equals(FirefoxDriver.class)) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[2]/main/div[5]/div/div[2]/section/div[1]/div/div/div/div/div[4]/div[4]/div[1]/div[1]/button")));
                cartButton = driver.findElement(By.xpath("/html/body/div[1]/div[2]/main/div[5]/div/div[2]/section/div[1]/div/div/div/div/div[4]/div[4]/div[1]/div[1]/button"));
                new Actions(driver).pause(Duration.ofSeconds(2)).click(cartButton).pause(Duration.ofSeconds(2)).perform();
                return driver.findElement(By.xpath("//h2[text()='Товар успешно добавлен в корзину']")).isDisplayed();
            } catch (TimeoutException e) {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[2]/main/div[5]/div/div[2]/section/div[1]/div/div/div/div/div[4]/div[4]/div/button")));
                cartButton = driver.findElement(By.xpath("/html/body/div[1]/div[2]/main/div[5]/div/div[2]/section/div[1]/div/div/div/div/div[4]/div[4]/div/button"));
                new Actions(driver).pause(Duration.ofSeconds(2)).click(cartButton).pause(Duration.ofSeconds(2)).perform();
                return driver.findElement(By.xpath("//h2[text()='Товар успешно добавлен в корзину']")).isDisplayed();
            }
        }
        return false;
    }

    public void continueShopping() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@aria-label='Закрыть']")));
        WebElement goToCart = driver.findElement(By.xpath("//button[@aria-label='Закрыть']"));
        new Actions(driver).pause(Duration.ofSeconds(2)).click(goToCart).pause(Duration.ofSeconds(2)).perform();
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
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='cardAddButton']/div[1]/div/div/div/div/div[4]/div[4]/div[1]/div[1]/div/div[2]/button")));
            moreButton = driver.findElement(By.xpath("//*[@id='cardAddButton']/div[1]/div/div/div/div/div[4]/div[4]/div[1]/div[1]/div/div[2]/button"));
            new Actions(driver).pause(Duration.ofSeconds(2)).click(moreButton).pause(Duration.ofSeconds(2)).perform();
            WebElement amount = driver.findElement(By.xpath("//*[@id='cardAddButton']/div[1]/div/div/div/div/div[4]/div[4]/div[1]/div[1]/div/a"));
            return amount.getText();
        }
        return "";
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
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='cardAddButton']/div[1]/div/div/div/div/div[4]/div[4]/div[1]/div[1]/div/div[1]/button")));
            lessButton = driver.findElement(By.xpath("//*[@id='cardAddButton']/div[1]/div/div/div/div/div[4]/div[4]/div[1]/div[1]/div/div[1]/button"));
            new Actions(driver).pause(Duration.ofSeconds(2)).click(lessButton).pause(Duration.ofSeconds(2)).perform();
            WebElement amount = driver.findElement(By.xpath("//*[@id='cardAddButton']/div[1]/div/div/div/div/div[4]/div[4]/div[1]/div[1]/div/a"));
            return amount.getText();
        }
        return "";
    }

    public void goToCart() {
        WebElement goToCart = driver.findElement(By.xpath("//div[@data-zone-name='goToCart']/a/span"));
        new Actions(driver).pause(Duration.ofSeconds(2)).click(goToCart).pause(Duration.ofSeconds(5)).perform();
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

    public boolean followPrice() {
        new Actions(driver).pause(Duration.ofSeconds(2)).click(followPrice).pause(Duration.ofSeconds(1)).perform();
        if (driver.findElement(By.xpath("//*[contains(text(), 'Оставьте свой адрес — как только цена на товар снизится, вы сразу об этом узнаете')]"))
                .isDisplayed()) return true;
        else return false;
    }

    public void addToCompare() {
        new Actions(driver).pause(Duration.ofSeconds(2)).click(compare).pause(Duration.ofSeconds(1)).perform();
    }

    public void goToCompare() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[2]/main/div[4]/div/div/div[3]/div/div/div[3]/a")));
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/main/div[4]/div/div/div[3]/div/div/div[3]/a")).click();
    }
}
