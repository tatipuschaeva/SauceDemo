import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;

public class LoginTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void CheckPositiveAuthorization() {
        driver.get("https://www.saucedemo.com/");
        String credentialLoginText = driver.findElement(By.id("login_credentials")).getText();
        String[] credentialsLogin = credentialLoginText.split("\\n");
        String username = credentialsLogin[1].trim(); //многострочный текст
        String credentialPasswordText = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[2]/div/div[2]")).getText();
        String[] credentialsPassword = credentialPasswordText.split("\\n");
        String password = credentialsPassword[1].trim(); //многострочный текст
        WebElement usernameField = driver.findElement(By.id("user-name"));
        usernameField.sendKeys(username);
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys(password);
        driver.findElement(By.id("login-button")).click();
        WebElement pageTitle = driver.findElement(By.xpath("//*[@id='header_container']/div[2]/span"));
        Assert.assertTrue(pageTitle.isDisplayed());
    }

    @Test
    public void CheckNegativeAuthorizationNullLogin() {
        driver.get("https://www.saucedemo.com/");
        String credentialPasswordText = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[2]/div/div[2]")).getText();
        String[] credentialsPassword = credentialPasswordText.split("\\n");
        String password = credentialsPassword[1].trim();
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys(password);
        driver.findElement(By.id("login-button")).click();
        String errorMessage = driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3")).getText();
        Assert.assertEquals(errorMessage, "Epic sadface: Username is required");
    }

    @Test
    public void CheckNegativeAuthorizationNullPassword() {
        driver.get("https://www.saucedemo.com/");
        String credentialLoginText = driver.findElement(By.id("login_credentials")).getText();
        String[] credentialsLogin = credentialLoginText.split("\\n");
        String username = credentialsLogin[1].trim();
        WebElement usernameField = driver.findElement(By.id("user-name"));
        usernameField.sendKeys(username);
        driver.findElement(By.id("login-button")).click();
        String errorMessage = driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3")).getText();
        Assert.assertEquals(errorMessage, "Epic sadface: Password is required");
    }

    @Test
    public void CheckNegativeAuthorizationIncorrectLogin() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("incorrect-login"); //вводим несуществующий логин
        driver.findElement(By.id("password")).sendKeys("incorrect-password"); //вводим несуществующий пароль
        driver.findElement(By.id("login-button")).click();
        String errorMessage = driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3")).getText();
        Assert.assertEquals(errorMessage, "Epic sadface: Username and password do not match any user in this service");
    }

    @AfterMethod(alwaysRun = true)
    public void quit() {
        driver.quit();
    }
}
