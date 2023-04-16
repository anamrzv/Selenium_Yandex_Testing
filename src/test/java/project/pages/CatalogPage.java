package project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CatalogPage {
    public WebDriver driver;

    public CatalogPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//*[contains(@id, 'range-filter-field-glprice_9fctjfcypvq_min')]")
    private WebElement minPrice;

    @FindBy(xpath = "//*[contains(@id, 'range-filter-field-glprice_9fctjfcypvq_max')]")
    private WebElement maxPrice;

    @FindBy(xpath = "//*[contains(text(), 'Нет подходящих товаров')]/..")
    private WebElement noResultField;

    @FindBy(xpath = "//*[contains(text(), 'Сбросить фильтры')]")
    private WebElement resetFilterButton;

    @FindBy(xpath = "//span[@data-auto='filter-found-visible-tooltip']")
    private WebElement filterResult;

    public String filterByDeliveryDate(String dateOption) {
        WebElement button = driver.findElement(By.xpath("//*[text()='"+dateOption+"']"));
        button.click();
        if (filterResult.isDisplayed()) return filterResult.getText();
        else if (noResultField.isDisplayed()) return noResultField.getText();
        else return "";
    }

    public String filterByPriceRange(String from, String to) {
        minPrice.sendKeys(from);
        maxPrice.sendKeys(to);
        if (filterResult.isDisplayed()) return filterResult.getText();
        else if (noResultField.isDisplayed()) return noResultField.getText();
        else return "";
    }

    public String filterByCheckbox(String filter) {
        WebElement checkbox = driver.findElement(By.xpath(String.format("//label[text()='{0}']", filter)));
        if (checkbox.isDisplayed()) {
            checkbox.click();
            if (filterResult.isDisplayed()) return filterResult.getText();
            else if (noResultField.isDisplayed()) return noResultField.getText();
            else return "";
        } else return "";
    }

    public void resetFilters() {
        resetFilterButton.click();
    }

}
