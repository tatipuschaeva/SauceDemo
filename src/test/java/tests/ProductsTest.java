package tests;

import io.qameta.allure.*;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;

import java.util.List;

public class ProductsTest extends BaseTest {

    @Test (testName = "Успешное добавление товара в корзину с главной страницы")
    @Description("Необходимо проверить возможность добавления товара в корзину с главной страницы")
    @Severity(SeverityLevel.NORMAL)
    @Epic("SauceDemo 1.0")
    @Feature("Add to cart in SauceDemo")
    @Story("Добавление в корзину")
    @TmsLink("www.jira.com/TK-8")
    @Issue("www.jira.com/TK-8")
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

    @Test (testName = "Успешное удаление товара из корзины с главной страницы")
    @Description("Необходимо проверить возможность удаления товара из корзины с главной страницы")
    @Severity(SeverityLevel.NORMAL)
    @Epic("SauceDemo 1.0")
    @Feature("Remove in cart in SauceDemo")
    @Story("Удаление в корзины")
    @TmsLink("www.jira.com/TK-9")
    @Issue("www.jira.com/TK-9")
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

    @Test (testName = "Переход в детальную форму товара")
    @Description("Необходимо проверить возможность перехода в детальную форму товара из главной страницы")
    @Severity(SeverityLevel.NORMAL)
    @Epic("SauceDemo 1.0")
    @Feature("Open ProductPage in SauceDemo")
    @Story("Переход в детальную форму")
    @TmsLink("www.jira.com/TK-10")
    @Issue("www.jira.com/TK-10")
    public void checkOpenProductDetails() {
        LoginPage.open();
        LoginPage.login("standard_userrr", "secret_sauce");
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
