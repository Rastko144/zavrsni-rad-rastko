package tests;


import Pages.*;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class VulkanTest extends BaseTest
{
    @Test
    public void vulkanPositiveLoginTest()
    {
        VulkanHomePage loginPage=new VulkanHomePage(driver);
        loginPage.vulkanLogin("rastkostankovic@yahoo.com","123456789rastko");
        wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("item-logout")));
        WebElement logoutButton=driver.findElement(By.className("item-logout"));
        Assert.assertTrue("Logout button is not visible!",logoutButton.isDisplayed());
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div[3]/nav/ul/li[1]/a/span")));
        WebElement userNameTag=driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div[3]/nav/ul/li[1]/a/span"));
        Assert.assertTrue("Username element is not present!",userNameTag.isDisplayed());
        String userRastko=userNameTag.getText();
        System.out.println("Checker: "+userRastko);
        Assert.assertTrue("Username is not visible!",userRastko.contains("Rastko Stankovic"));
    }

    @Test
    public void vulkanNegativeLoginTest()
    {
        VulkanHomePage loginPage=new VulkanHomePage(driver);
        loginPage.vulkanLogin("rastkostankovic@yahoo.com","0123456789.rastko");
        wdWait.until(ExpectedConditions.invisibilityOfElementWithText(By.className("alert-danger"),""));
        WebElement alert=driver.findElement(By.className("alert-danger"));
        System.out.println("Provera poruke: "+alert.getText());
        Assert.assertTrue("Alert message is not visible!", alert.getText().contains("Pogrešna email adresa ili lozinka. Molimo Vas pokušajte ponovo!"));

    }
    @Test
    public void vulkanSearchTest()
    {
        VulkanSearchPage searchBox=new VulkanSearchPage(driver);
        searchBox.searchBoxForm("programiranje");
        WebElement givenTerm=driver.findElement(By.className("product-listing-items"));
        System.out.println("Present terms: "+givenTerm.getText());
        Assert.assertTrue("No articles containing term is found!",givenTerm.getText().contains("programiranje"));
        searchBox.productsNumber();
        int resultsNumber=Integer.parseInt(searchBox.trim);
        System.out.println(resultsNumber);
        List<WebElement> listOfBooks=searchBox.listOfArticles;
        Assert.assertEquals("Number of products in list and displayed number does not match! ",resultsNumber,listOfBooks.size());
    }

    @Test
    public void vulkanFilterTest()
    {
        VulkanFilterPage filterTest=new VulkanFilterPage(driver);
        filterTest.vulkanFilterPage();
        List<WebElement> listWithFilterOne=filterTest.filterSectionOne;
        Assert.assertEquals("Filtered numbers in list does not match!",listWithFilterOne.size(),12);
        int numberTrimmed=Integer.parseInt(filterTest.trimmedNumber);
        List<WebElement> listWithResults=filterTest.filterSectionTwo;
        Assert.assertEquals("Number does not match!", numberTrimmed,listWithResults.size());

    }

    @Test
    public void vulkanBasketTest()
    {
        VulkanAddToBasketPage addingArticles=new VulkanAddToBasketPage(driver);
        addingArticles.vulkanAddingToBasket();

        VulkanBasketPage basketSection=new VulkanBasketPage(driver);
        basketSection.basketSectionPage();
        String nameOfBasketSection=basketSection.basketName;
        System.out.println(nameOfBasketSection);
        Assert.assertTrue("Section does not contain necessary text!",nameOfBasketSection.contains("VAŠA KORPA"));
        List<WebElement> basketList=basketSection.cartList;
        Assert.assertEquals("Number of selected articles does not match number of articles in cart!",basketList.size(),2);
        double cena=Double.parseDouble(basketSection.finalPrice);
        System.out.println("Final price in double:"+cena);
        double cenaDbl=Double.parseDouble(basketSection.finalTotalPrice);
        System.out.println("Final total price:"+cenaDbl);
        String priceInString=String.valueOf(cena);
        String priceInString2=String.valueOf(cenaDbl);
        Assert.assertEquals("No match!",priceInString,priceInString2);

    }




}
