package project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ItemPage {
    public WebDriver driver;

    public ItemPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//button[@data-auto='wishlist-button']")
    private WebElement favButton;

    //@FindBy(xpath = "//button[contains(normalize-space(.), 'корзину')]")
    @FindBy(xpath="//*[@id='cardAddButton']/div[1]/div/div/div/div/div[4]/div[4]/div[1]/div[1]/button")
    private WebElement cartButton;
    public void addToFav() {
        favButton.click();
    }

    public boolean addToCart() {
        cartButton.click();
        return driver.findElement(By.xpath("//h2[text()='Товар успешно добавлен в корзину']")).isDisplayed();
    }

    public String goToFavAndCheckItem() {
        WebElement goToFav = driver.findElement(By.xpath("//*[@data-baobab-name='favorites']"));
        goToFav.click();
        WebElement firstItem = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[1]/div[1]/section/div/article/div[2]/h3/a/span"));
        return firstItem.getText();
    }

    public String goToCartAndCheckItem() {
        WebElement goToCart = driver.findElement(By.xpath("/html/body/div[22]/div/div/div/div[2]/div/div/div[1]/div/div[2]/div[2]/a/span"));
        //WebElement goToCart = driver.findElement(By.xpath("//a[@href='/my/cart']/span"));
        goToCart.click();
        WebElement firstItem = driver.findElement(By.xpath("//*[@data-auto='CartOffer']/div/div[3]/div/div[1]"));
        return firstItem.getText();
    }

}
