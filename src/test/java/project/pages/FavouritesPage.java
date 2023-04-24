package project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FavouritesPage {
    public WebDriver driver;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div[1]/div[1]/section/div/article/div[2]/h3/a/span")
    private WebElement firstItem;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div[1]/div[1]/section/div/article[1]/div[4]/div")
    private WebElement firstItemHeart;

    public FavouritesPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public boolean isFavouriteEmpty() {
        if (driver.findElement(By.xpath("//*[contains(text(), 'Ещё не готовы к покупке?')]"))
                .isDisplayed()) return true;
        else return false;
    }

    public String getFirstItem(){
        return firstItem.getText();
    }

    public void removeFromFav() {
        firstItemHeart.click();
    }
}
