package project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
    public WebDriver driver;

    @FindBy(xpath = "//*[@data-baobab-name='deleteAll']")
    private WebElement deleteButton;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div/main/div[4]/div/div[2]/div/div/div/div/div/div/div[2]/div/div/div/div/div/div/div/div/div[3]/div[2]/div/div[2]/button[2]")
    private WebElement deleteFinallyButton;

    public CartPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public boolean isCartEmpty() {
        if (driver.findElement(By.xpath("//*[contains(text(), 'Сложите в корзину нужные товары')]"))
                .isDisplayed()) return true;
        else return false;
    }

    public boolean checkItem(String name){
        if (driver.findElement(By.xpath("//*[contains(text(), '"+name+"')]"))
                .isDisplayed()) return true;
        else return false;
    }

    public void removeFromCart() {
        deleteButton.click();
        deleteFinallyButton.click();
    }
}
