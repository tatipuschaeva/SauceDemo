package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductsPage {

    // Локаторы элементов
    private static final By inventoryListLocator = By.className("inventory_list");
    private static final By inventoryItemLocator = By.className("inventory_item");
    private static final By itemNameLocator = By.className("inventory_item_name");
    private static final By itemDescriptionLocator = By.className("inventory_item_desc");
    private static final By itemPriceLocator = By.className("inventory_item_price");
    private static final By addToCartButtonLocator = By.cssSelector("button[data-test*='add-to-cart-']");
    private static final By removeButtonLocator = By.cssSelector("button[data-test*='remove-']");
    private static final By cartBadgeLocator = By.className("shopping_cart_badge");
    private static final By shoppingCartLinkLocator = By.id("shopping_cart_container");
    private static WebDriver driver;

    public ProductsPage(WebDriver driver) {
        ProductsPage.driver = driver;
    }

    public static boolean isRemoveButtonDisplayed(WebElement item) {
        try {
            return item.findElement(removeButtonLocator).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public static boolean isShoppingCartButtonDisplayed() {
        try {
            return driver.findElement(shoppingCartLinkLocator).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    // Методы для работы с элементами страницы
    public WebElement getInventoryList() {
        return driver.findElement(inventoryListLocator);
    }

    public List<WebElement> getInventoryItems() {
        return driver.findElements(inventoryItemLocator);
    }

    public int getInventoryItemsCount() {
        return getInventoryItems().size();
    }

    public String getItemName(WebElement item) {
        try {
            return item.findElement(itemNameLocator).getText();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return null;
        }
    }

    public String getItemDescription(WebElement item) {
        try {
            return item.findElement(itemDescriptionLocator).getText();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return null;
        }
    }

    public String getItemPrice(WebElement item) {
        try {
            return item.findElement(itemPriceLocator).getText();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return null;
        }
    }

    public WebElement getAddToCartButton(WebElement item) {
        return item.findElement(addToCartButtonLocator);
    }

    public WebElement getRemoveButton(WebElement item) {
        return item.findElement(removeButtonLocator);
    }

    public void addToCart(WebElement item) {
        getAddToCartButton(item).click();
    }

    public void remove(WebElement item) {
        getRemoveButton(item).click();
    }

    public boolean isAddToCartButtonDisplayed(WebElement item) {
        try {
            return item.findElement(addToCartButtonLocator).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public boolean isCartBadgeDisplayed() {
        List<WebElement> elements = driver.findElements(cartBadgeLocator);
        return elements.size() > 0;
    }

    public String getCartBadgeCount() {
        if (isCartBadgeDisplayed()) {
            return driver.findElement(cartBadgeLocator).getText();
        }
        return null;
    }

    public int getCartBadgeCountNumber() {
        String badgeCount = getCartBadgeCount();
        if (badgeCount != null) {
            return Integer.parseInt(badgeCount);
        } else {
            return 0;
        }
    }

    public WebElement getShoppingCartLink() {
        return driver.findElement(shoppingCartLinkLocator);
    }

    public void goToCart() {
        getShoppingCartLink().click();
    }

    public void openProductDetails(WebElement item) {
        item.findElement(itemNameLocator).click();
    }

}
