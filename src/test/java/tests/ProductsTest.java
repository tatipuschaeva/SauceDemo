package tests;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;

import java.util.List;

public class ProductsTest extends BaseTest {

    @Test
    public void checkPositiveProductAddInCart() {
        LoginPage.open();
        LoginPage.login("standard_user", "secret_sauce");
        List<WebElement> items = productsPage.getInventoryItems();
        softAssert.assertFalse(items.isEmpty(), "Список товаров пуст");
        WebElement firstItem = items.get(0);
        softAssert.assertTrue(
                productsPage.isAddToCartButtonDisplayed(firstItem),
                "Кнопка 'Add to cart' должна отображаться");
        productsPage.addToCart(firstItem);
        softAssert.assertTrue(
                ProductsPage.isRemoveButtonDisplayed(firstItem),
                "Кнопка 'Remove' должна отображаться");
        softAssert.assertEquals(
                productsPage.getCartBadgeCountNumber(), 1,
                "Счетчик корзины должен быть равен 1");
    }

    @Test
    public void checkPositiveProductRemoveInCart() {
        LoginPage.open();
        LoginPage.login("standard_user", "secret_sauce");
        List<WebElement> items = productsPage.getInventoryItems();
        softAssert.assertFalse(items.isEmpty(), "Список товаров пуст");
        WebElement firstItem = items.get(0);
        softAssert.assertTrue(
                productsPage.isAddToCartButtonDisplayed(firstItem),
                "Кнопка 'Add to cart' должна отображаться");
        productsPage.addToCart(firstItem);
        softAssert.assertTrue(
                ProductsPage.isRemoveButtonDisplayed(firstItem),
                "Кнопка 'Remove' должна отображаться");
        softAssert.assertEquals(
                productsPage.getCartBadgeCountNumber(), 1,
                "Счетчик корзины должен быть равен 1");
        productsPage.remove(firstItem);
        softAssert.assertTrue(
                productsPage.isAddToCartButtonDisplayed(firstItem),
                "Кнопка 'Add to cart' должна отображаться");
        softAssert.assertEquals(
                productsPage.getCartBadgeCountNumber(),
                0, "Счетчик корзины должен быть равен 0");
    }

    @Test
    public void checkOpenProductDetails() {
        LoginPage.open();
        LoginPage.login("standard_user", "secret_sauce");
        // Ищем товар в списке
        List<WebElement> items = productsPage.getInventoryItems();
        softAssert.assertFalse(items.isEmpty(), "Список товаров пуст");
        WebElement firstItem = items.get(0);
        // Получаем наименование, описание и стоимость товара из списка
        String itemName = productsPage.getItemName(firstItem);
        String itemDescription = productsPage.getItemDescription(firstItem);
        String itemPrice = productsPage.getItemPrice(firstItem);
        // Открываем карточку товара
        productsPage.openProductDetails(firstItem);
        // Сравниваем наименование, описание и стоимость товара в карточке
        softAssert.assertEquals(
                productDetailsPage.getProductName(), itemName,
                "Имя товара не совпадает");
        softAssert.assertEquals(
                productDetailsPage.getProductDescription(), itemDescription,
                "Описание товара не совпадает");
        softAssert.assertEquals(
                productDetailsPage.getProductPrice(), itemPrice,
                "Цена товара не совпадает");
    }
}
