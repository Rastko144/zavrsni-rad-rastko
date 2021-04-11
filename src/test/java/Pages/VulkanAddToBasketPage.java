package Pages;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class VulkanAddToBasketPage extends BaseHelper
{
    public List<WebElement> itemsList;

    @FindBy(id = "nb_addToCartButton")
    WebElement addToCart;
    @FindBy (className = "mini-cart-simple")
    WebElement miniCart;


    WebDriver driver;
    public VulkanAddToBasketPage (WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }


    private void navigateToPage()
    {
        driver.get("https://www.knjizare-vulkan.rs/proizvodi/kompjuter-biblioteka/?search=programiranje");
    }

    private void clickOnFirstArticle()
    {
        WebElement products=driver.findElement(By.className("product-listing-items"));
        WebElement row=products.findElement(By.className("row"));
        itemsList=row.findElements(By.className("product-item"));
        WebElement productArticle= driver.findElement(By.className("product-item"));
        Actions hoover=new Actions(driver);
        hoover.moveToElement(productArticle).perform();
        itemsList.get(0).click();

    }
    private void cookieAccept()
    {
        List<WebElement> cookies=driver.findElements(By.className("cookie-agree"));
        if (cookies.size()>0)
        {

            cookies.get(0).click();
        }
    }

    private void addItemToBasket()
    {
        wdWait.until(ExpectedConditions.elementToBeClickable(addToCart));
        js.executeScript("window.scrollBy(0,150)");
        js.executeScript("arguments[0].click();",addToCart);
        wdWait.until(ExpectedConditions.elementToBeClickable(By.className("pull-right")));
        driver.navigate().back();
    }
    private void clickOnFourthArticle()
    {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("product-item")));
        WebElement products=driver.findElement(By.className("product-listing-items"));
        WebElement row=products.findElement(By.className("row"));
        itemsList=row.findElements(By.className("product-item"));
        WebElement productArticle= driver.findElement(By.className("product-item"));
        Actions hoover=new Actions(driver);
        hoover.moveToElement(productArticle).perform();
        itemsList.get(3).click();
    }
    private void addArticleToBasket()
    {
        wdWait.until(ExpectedConditions.elementToBeClickable(addToCart));
        js.executeScript("window.scrollBy(0,150)");
        js.executeScript("arguments[0].click();",addToCart);

    }
    private void clickOnAddToBasket()
    {
        //wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("btn-success")));
        wdWait.until(ExpectedConditions.elementToBeClickable(By.className("pull-right")));

        WebElement basket=miniCart.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div[3]/div[1]/div[1]/a/div[2]"));
        basket.click();
    }




    public void vulkanAddingToBasket()
    {
        navigateToPage();
        clickOnFirstArticle();
        cookieAccept();
        addItemToBasket();
        clickOnFourthArticle();
        addArticleToBasket();
        clickOnAddToBasket();


    }
}
