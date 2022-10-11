import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.List;

public class Saucedemo2 {
    public String baseUrl = "https://www.saucedemo.com/";
    public WebDriver driver ;

    @Test(priority = 0)
    public void verifyLoginCredentials() throws InterruptedException {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get(baseUrl);
        WebElement usernameBox = driver.findElement(By.id("user-name"));
        WebElement passwordBox = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

//        Thread.sleep(500);
        usernameBox.sendKeys("standard_user");
//        Thread.sleep(500);
        passwordBox.sendKeys("secret_sauce");
//        Thread.sleep(500);
        loginButton.click();
    }
    @Test(priority = 1)
    public void verifySauceLabsBackpack(){
        WebElement backpackImageLink = driver.findElement(By.xpath("//*[@id=\"item_4_img_link\"]/img"));
        backpackImageLink.click();
        List <WebElement> sauceLabsBackpackTitle = driver.findElements(By.xpath("//*[@id=\"inventory_item_container\"]/div/div/div[2]/div[1]"));
        Assert.assertNotEquals(sauceLabsBackpackTitle.size(), 0);
        List <WebElement> sauceLabsBackpackDescription = driver.findElements(By.xpath("//*[@id=\"inventory_item_container\"]/div/div/div[2]/div[2]"));
        Assert.assertNotEquals(sauceLabsBackpackDescription.size(), 0);
        List <WebElement> sauceLabsBackpackPrice = driver.findElements(By.xpath("//*[@id=\"inventory_item_container\"]/div/div/div[2]/div[3]"));
        Assert.assertNotEquals(sauceLabsBackpackPrice.size(), 0);
        WebElement backpackAddToCart = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        backpackAddToCart.click();
    }
    @Test(priority = 2)
    public void verifySauceLabsBackpackBackToProducts(){
        WebElement backpackBackToProducts = driver.findElement(By.id("back-to-products"));
        backpackBackToProducts.click();
    }
    @Test(priority = 3)
    public void verifySauceLabsFleeceJacketAddToCartFromHomePage(){
        WebElement sauceLabsFleeceJacketAddToCartFromHomePage = driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket"));
        sauceLabsFleeceJacketAddToCartFromHomePage.click();
    }
    @Test(priority = 4)
    public void verifyShoppingCartPage(){
        WebElement shoppingCartPage = driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a"));
        shoppingCartPage.click();
    }
    @Test(priority = 5)
    public void verifyShoppingCartCheckout(){
        WebElement shoppingCartCheckout = driver.findElement(By.id("checkout"));
        shoppingCartCheckout.click();

        WebElement firstNameBox = driver.findElement(By.id("first-name"));
        WebElement lastNameBox = driver.findElement(By.id("last-name"));
        WebElement zipCodeBox = driver.findElement(By.id("postal-code"));
        WebElement continueButton = driver.findElement(By.id("continue"));

        firstNameBox.sendKeys("Firstname");
        lastNameBox.sendKeys("Lastname");
        zipCodeBox.sendKeys("Zipcode");
        continueButton.click();

        WebElement finishButton = driver.findElement(By.id("finish"));
        finishButton.click();

        String expectedMessage = "THANK YOU FOR YOUR ORDER";
        WebElement actualMessage = driver.findElement(By.xpath("//*[@id=\"checkout_complete_container\"]/h2"));
        Assert.assertEquals(actualMessage.getText(), expectedMessage);

    }
    @Test(priority = 6)
    public void verifyLogout(){
        WebElement burgerMenuButton = driver.findElement(By.id("react-burger-menu-btn"));
        burgerMenuButton.click();
        WebElement logoutButton = driver.findElement(By.id("logout_sidebar_link"));
        logoutButton.click();
    }
    @AfterTest
    public void closeSession(){
        driver.close();
    }
}