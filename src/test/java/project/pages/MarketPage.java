package project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    public String checkUserIcon() {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@src, '//avatars.mds.yandex.net/get-yapic/0/0-0/islands-retina-middle')]/..")));
        if (userIcon != null) {
            userIcon.click();
            if (userMail != null) return userMail.getText();
            else return null;
        }
        else return null;
    }
}
