import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.List;

public class Saucedemo1 {
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
    public void verifyProductsHeader(){
        String expectedHeader = "PRODUCTS";
        WebElement actualTitle = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span"));
        Assert.assertEquals(actualTitle.getText(), expectedHeader);
    }
    @Test(priority = 1)
    public void verifyShoppingCart(){
        List <WebElement> shoppingCart = driver.findElements(By.xpath("//*[@id=\"shopping_cart_container\"]/a"));
        Assert.assertNotEquals(shoppingCart.size(), 0);
    }
    @Test(priority = 1)
    public void verifyBurgerMenu(){
        List <WebElement> burgerMenu = driver.findElements(By.xpath("//*[@id=\"react-burger-menu-btn\"]"));
        Assert.assertNotEquals(burgerMenu.size(), 0);
    }
    @Test(priority = 1)
    public void verifyTwitterLinks(){
        List <WebElement> twitterLink = driver.findElements(By.xpath("//*[@id=\"page_wrapper\"]/footer/ul/li[1]/a"));
        Assert.assertNotEquals(twitterLink.size(), 0);
    }
    @Test(priority = 1)
    public void verifyFacebookLinks(){
        List <WebElement> facebookLink = driver.findElements(By.xpath("//*[@id=\"page_wrapper\"]/footer/ul/li[2]/a"));
        Assert.assertNotEquals(facebookLink.size(), 0);
    }
    @Test(priority = 1)
    public void verifyLinkedinLinks(){
        List <WebElement> linkedinLink = driver.findElements(By.xpath("//*[@id=\"page_wrapper\"]/footer/ul/li[3]/a"));
        Assert.assertNotEquals(linkedinLink.size(), 0);
    }
    @Test(priority = 1)
    public void verifyLogoutElement(){
        List <WebElement> logoutButton = driver.findElements(By.xpath("//*[@id=\"logout_sidebar_link\"]"));
        Assert.assertNotEquals(logoutButton.size(), 0);
    }
    @AfterTest
    public void closeSession(){
        driver.close();
    }
}