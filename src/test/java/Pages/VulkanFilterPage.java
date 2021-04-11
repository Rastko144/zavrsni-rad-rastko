package Pages;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class VulkanFilterPage extends BaseHelper
{

    @FindBy (name = "limit")
    WebElement filterDropMenu;
    @FindBy (className = "products-found")
    WebElement counter;
    @FindBy (xpath = "/html/body/div[4]/div[2]/div/div[2]/div[1]/div/form/div/div[2]/ul/li[3]/label")
    WebElement checkBox;

    public List<WebElement> filterSectionOne;
    public List<WebElement> filterSectionTwo;

    public String checkBoxNumber;
    public String trimmedNumber;



    WebDriver driver;
    public VulkanFilterPage (WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    private void navigateToUrl()
    {
        driver.get("https://www.knjizare-vulkan.rs/proizvodi?search=programiranje");
    }

    private void clickOnDropMenu()
    {
        wdWait.until(ExpectedConditions.elementToBeClickable(filterDropMenu));
        filterDropMenu.click();

    }
    private void clickOnValue()
    {
        WebElement dropMenuClick=driver.findElement(By.name("limit"));
        Select dropClick=new Select(dropMenuClick);
        dropClick.selectByValue("12");
        wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("col-md-10")));
        wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("product-item")));
        WebElement productList=driver.findElement(By.className("product-listing-items"));
        WebElement rowList=productList.findElement(By.className("row"));
        filterSectionOne=rowList.findElements(By.className("product-item"));
        System.out.println("Filtered list checking:"+filterSectionOne.size());
    }

    private void filterSection()
    {
        wdWait.until(ExpectedConditions.elementToBeClickable(checkBox));
     /*   js.executeScript("arguments[0].click()",checkBox);*/
        checkBox.click();

    }
    private void filterChecker()
    {
        wdWait.until(ExpectedConditions.visibilityOf(counter));
        wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("col-md-10")));
        wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[4]/div[2]/div/div[2]/div[2]/div[2]/div/div[2]/div/a")));
        checkBoxNumber=counter.getText();
        System.out.println("Checker:"+checkBoxNumber.replaceAll("[^0-9]",""));
        trimmedNumber=checkBoxNumber.replaceAll("[^0-9]","");

    }
    private void filteredResults()
    {
        WebElement listElement=driver.findElement(By.className("col-md-10"));
        WebElement productList=listElement.findElement(By.className("product-listing-items"));
        WebElement listRow=productList.findElement(By.className("row"));
        filterSectionTwo=listRow.findElements(By.className("product-item"));

    }




    public void vulkanFilterPage()
    {
        navigateToUrl();
        clickOnDropMenu();
        clickOnValue();
        filterSection();
        filterChecker();
        filteredResults();


    }
}
