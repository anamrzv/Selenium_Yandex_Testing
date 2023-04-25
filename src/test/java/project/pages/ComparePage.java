package project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ComparePage {

    public WebDriver driver;

    public ComparePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public boolean isItemInCompare(String item) {
        if (driver.findElement(By.xpath("//*[contains(text(), '" + item + "')]"))
                .isDisplayed()) return true;
        else return false;
    }
}
