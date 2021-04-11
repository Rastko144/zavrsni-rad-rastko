package Pages;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class VulkanHomePage extends BaseHelper
{

    @FindBy (className = "login-btn")
    WebElement loginButton;
    @FindBy (name = "login_email")
    WebElement emailForm;
    @FindBy (name = "login_password")
    WebElement passwordForm;
    @FindBy(className = "btn-success")
    WebElement prijavaButton;

    WebDriver driver;
    public VulkanHomePage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    private void navigateToUrl()
    {
        driver.get("https://www.knjizare-vulkan.rs/");
    }
    private void clickOnPrijaviSeButton()
    {
        wdWait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();

    }
    private void enterEmail(String email)
    {
        wdWait.until(ExpectedConditions.elementToBeClickable(emailForm));
        emailForm.sendKeys(email);
    }
    private void enterPassword(String password)
    {
        passwordForm.sendKeys(password);
    }
    private void clickOnLoginButton()
    {
        prijavaButton.click();
    }




    public void vulkanLogin(String email, String password)
    {
        navigateToUrl();
        clickOnPrijaviSeButton();
        enterEmail(email);
        enterPassword(password);
        clickOnLoginButton();

    }
}
