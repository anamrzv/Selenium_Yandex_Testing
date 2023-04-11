package project.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    public void clickLoginBtn() {
        loginBtn.click();
    }

    public String checkUserIcon() {
        if (userIcon != null && userMail != null) return userMail.getText();
        else return null;
    }
}
