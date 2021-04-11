package Pages;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class VulkanSearchPage extends BaseHelper
{

    @FindBy (className = "fa-search")
    WebElement magnifier;
    @FindBy (className = "autocomplete-input")
    WebElement searchBoxExtended;
    @FindBy (className = "products-found")
    WebElement numberOfArticlesFound;
    public List<WebElement> listOfArticles;

    public String articlesNumber;
    public String trim;

    WebDriver driver;
    public VulkanSearchPage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    private void navigateToUrl()
    {
        driver.get("https://www.knjizare-vulkan.rs/");
    }
    private void clickOnMagnifier()
    {
        wdWait.until(ExpectedConditions.elementToBeClickable(magnifier));
        magnifier.click();
    }
    private void enterTerm(String searchTerm)
    {
        wdWait.until(ExpectedConditions.elementToBeClickable(searchBoxExtended));
        searchBoxExtended.sendKeys(searchTerm);
        searchBoxExtended.sendKeys(Keys.ENTER);
    }
    private void results()
    {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("col-lg-10")));
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("product-listing-items")));
        WebElement results=driver.findElement(By.className("product-listing-items"));
        WebElement row=results.findElement(By.className("row"));
        listOfArticles=row.findElements(By.className("product-item"));
        System.out.println("Checking number of products in the list: "+listOfArticles.size());
    }
    public void productsNumber()
    {
        wdWait.until(ExpectedConditions.visibilityOf(numberOfArticlesFound));
        articlesNumber=numberOfArticlesFound.getText();
        System.out.println("Number of articles found trimmed:"+articlesNumber.replaceAll("[^0-9]",""));
        trim=articlesNumber.replaceAll("[^0-9]","");
    }






    public void searchBoxForm(String searchTerm)
    {
        navigateToUrl();
        clickOnMagnifier();
        enterTerm(searchTerm);
        results();
        productsNumber();

    }

}
