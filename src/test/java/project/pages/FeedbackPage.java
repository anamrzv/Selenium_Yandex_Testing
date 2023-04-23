package project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class FeedbackPage {
    public WebDriver driver;

    public FeedbackPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//*[@id='scroll-to-first-step']/article/div[1]/div/div/div/div/button[4]")
    private WebElement fourStars;

    @FindBy(xpath = "//*[@id='pro-textfield']")
    private WebElement whatLikedText;

    @FindBy(xpath = "//*[contains(text(), 'Оставить отзыв анонимно')]/..")
    private WebElement anonButton;

    @FindBy(xpath = "//*[contains(text(), 'Дальше')]/..")
    private WebElement nextButton;

    @FindBy(xpath = "//*[contains(text(), 'Готово')]/..")
    private WebElement finishButton;

    public void rateAsGood() {
        fourStars.click();
    }

    public void writeGoodComment(String text) {
        whatLikedText.sendKeys(text);
    }

    public void sendAsAnon() {
        anonButton.click();
    }

    public void setUserExperience(String option) {
        WebElement button = driver.findElement(By.xpath("//*[text()='" + option + "']"));
        button.click();
    }

    public void clickNext() {
        nextButton.click();
    }

    public boolean sendFeedback() {
        new Actions(driver).click(finishButton).pause(Duration.ofSeconds(2)).perform();
        WebElement text = driver.findElement(By.xpath("//*[contains(text(), 'Спасибо за отзыв!')]/.."));
        if (text.isDisplayed()) return true;
        else return false;
    }
}
