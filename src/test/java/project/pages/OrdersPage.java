package project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class OrdersPage {

    public WebDriver driver;

    public OrdersPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public boolean isOrdersEmpty() {
        if (driver.findElement(By.xpath("//*[contains(text(), 'Здесь будет храниться история ваших заказов')]"))
                .isDisplayed()) return true;
        else return false;
    }
}
