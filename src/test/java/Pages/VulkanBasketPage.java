package Pages;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class VulkanBasketPage extends BaseHelper
{

    @FindBy(className = "cart-step")
    WebElement basketSectionName;
    @FindBy(id = "order_cart_content")
    WebElement cartContent;

    @FindBy (xpath = "/html/body/div[4]/div[1]/div[2]/form/div[2]/div[1]/div[3]/table/tbody/tr[1]/td[10]/span")
    WebElement price1;
    @FindBy(xpath = "/html/body/div[4]/div[1]/div[2]/form/div[2]/div[1]/div[3]/table/tbody/tr[2]/td[10]/span")
    WebElement price2;


    public List<WebElement> cartList;


    public String basketName;
    public String productPrice1;
    public String productPrice2;

    public String finalPrice;
    public String cena;
    public String finalTotalPrice;




    WebDriver driver;
    public VulkanBasketPage (WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }



    public void basketSectionTitle()
    {
        wdWait.until(ExpectedConditions.visibilityOf(basketSectionName));
        WebElement basketSectionTitle=basketSectionName.findElement(By.xpath("/html/body/div[4]/div[1]/div[2]/form/div[2]/div[1]/div[1]/div/h2/span"));
        System.out.println("Basket section name: "+basketSectionTitle.getText());
        basketName=basketSectionTitle.getText();
    }
    private void basketContent()
    {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("item")));
        WebElement table=cartContent.findElement(By.className("table-responsive"));
        WebElement cartTable=table.findElement(By.className("cart-table"));
        cartList=cartTable.findElements(By.className("item"));
        System.out.println("Number of articles in cart list: "+cartList.size());

    }
    private void price()
    {
       productPrice1=price1.getText();
       System.out.println(productPrice1);
       wdWait.until(ExpectedConditions.visibilityOf(price2));
       productPrice2=price2.getText();
       System.out.println(productPrice2);
    }
    public void trim()
    {
        productPrice1=productPrice1.replace("RSD","").replace(".","").replace(",",".").trim();
        double priceDouble=Double.parseDouble(productPrice1);
        System.out.println(priceDouble);
        productPrice2=productPrice2.replace("RSD","").replace(".","").replace(",",".").trim();
        double priceDouble2=Double.parseDouble(productPrice2);
        System.out.println(priceDouble2);
        Double sum=Double.sum(priceDouble,priceDouble2);
        finalPrice=String.valueOf(sum);
        System.out.println("Price in string:"+finalPrice);
    }
    public void totalPriceTrim()
    {
        WebElement sumPrice=driver.findElement(By.className("text-right"));
        cena=sumPrice.getText();
        System.out.println("Total price:"+cena);
        cena=cena.replace("RSD","").replace(".","").replace(",",".").trim();
        double cenaDouble=Double.parseDouble(cena);
        System.out.println("Cena trim:"+cenaDouble);
        finalTotalPrice=String.valueOf(cenaDouble);

    }





    public void basketSectionPage()
    {
        basketSectionTitle();
        basketContent();
        price();
        trim();
        totalPriceTrim();

    }
}
