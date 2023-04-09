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

    public void clickLoginBtn() {
        loginBtn.click();
    }
}
