package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    static WebDriver driver;

    static By userField = By.id("user-name");
    static By passwordField = By.id("password");
    static By loginButton = By.id("login-button");
    static By errorMessage = By.cssSelector("[data-test=error]");

    public LoginPage(WebDriver driver) {
        LoginPage.driver = driver;
    }

    public static void open() {
        driver.get("https://www.saucedemo.com/");
    }

    public static void login(String user, String password) {
        driver.findElement(userField).sendKeys(user);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    public static String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }
}
