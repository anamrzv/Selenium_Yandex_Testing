package project.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CatalogPage {
    public WebDriver driver;

    public CatalogPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//input[starts-with(@id, 'range-filter-field-glprice_') and contains(@id, '_min')]")
    private WebElement minPrice;

    @FindBy(xpath = "//input[starts-with(@id, 'range-filter-field-glprice_') and contains(@id, '_max')]")
    private WebElement maxPrice;

    @FindBy(xpath = "//*[@id=\"serpTop\"]/div/div/div[1]/div/div/noindex/div/button[2]")
    private WebElement sortByPrice;

    @FindBy(xpath = "//*[text()='Сбросить фильтры']")
    private WebElement resetFilterButton;

    @FindBy(xpath = "//span[@data-auto='filter-found-visible-tooltip']")
    private WebElement filterResult;

    public String filterByDeliveryDate(String dateOption) {
        WebElement button = driver.findElement(By.xpath("//*[text()='" + dateOption + "']"));
        button.click();
        if (filterResult.isDisplayed()) return filterResult.getText();
        else return "";
    }

    public String filterByPriceRange(String from, String to) {
        minPrice.clear();
        minPrice.sendKeys(from);
        maxPrice.clear();
        maxPrice.sendKeys(to);
        if (filterResult.isDisplayed()) return filterResult.getText();
        else return "";
    }

    public String filterByCheckbox(String filter) {
        WebElement checkbox = driver.findElement(By.xpath(String.format("//span[contains(text(),'" + filter + "')]/ancestor::label")));
        checkbox.click();
        if (filterResult.isDisplayed())
            return filterResult.getText();
        else return "";
    }

    public void sortByPrice() {
        sortByPrice.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath("//div[@data-test-id='virtuoso-item-list']"))));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-test-id='virtuoso-item-list']")));
    }


    public String returnFirstItemName() {
        WebElement firstItem = driver.findElement(By.xpath("//*[starts-with(@id, 'page-')]/div/div/div/div/div/div[1]/div/div/div[1]/article/div[2]/h3/a/span"));
        return firstItem.getText();
    }

}
