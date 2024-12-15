package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDetailsPage {

    private static WebDriver driver;

    private final By productNameLocator = By.className("inventory_details_name");
    private final By productDescriptionLocator = By.className("inventory_details_desc");
    private final By productPriceLocator = By.className("inventory_details_price");

    public ProductDetailsPage(WebDriver driver) {
        ProductDetailsPage.driver = driver;
    }

    public String getProductName() {
        return driver.findElement(productNameLocator).getText();
    }

    public String getProductDescription() {
        return driver.findElement(productDescriptionLocator).getText();
    }

    public String getProductPrice() {
        return driver.findElement(productPriceLocator).getText();
    }

}