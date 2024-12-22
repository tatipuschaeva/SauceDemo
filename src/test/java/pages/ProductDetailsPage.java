package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDetailsPage {

    private static WebDriver driver;

    private final By productNameLocator = By.xpath("//div[@class='inventory_details_name large_size']");
    private final By productDescriptionLocator = By.xpath("//div[@class='inventory_details_desc large_size']");
    private final By productPriceLocator = By.xpath("//div[@class='inventory_details_price']");

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