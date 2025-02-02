package tests;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;

public class LoginTest extends BaseTest {

    @Test(testName = "Успешная авторизация пользователя в системе",
            description = "Необходимо проверить возможность успешной авторизации пользователя")
    @Severity(SeverityLevel.BLOCKER)
    @Epic("SauceDemo 1.0")
    @Feature("Authorization in SauceDemo")
    @Story("Успешная авторизация")
    @TmsLink("www.jira.com/TK-1")
    @Issue("www.jira.com/TK-1")
    public void CheckPositiveAuthorization() {
        LoginPage.open();
        LoginPage.login("standard_user", "secret_sauce");
        Assert.assertTrue(ProductsPage.isShoppingCartButtonDisplayed());
    }

    @DataProvider(name = "LoginData")
    public Object[][] loginData() {
        return new Object[][]{
                {"standard_user", "123456", "Epic sadface: Username and password do not match any user in this service"},
                {"notCorrectUserMame", "secret_sauce", "Epic sadface: Username and password do not match any user in this service"},
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"standard_user", "", "Epic sadface: Password is required"}
        };
    }

    @Test(testName = "Невозможность успешной авторизации при вводе невалидных  данных",
            description = "Необходимо проверить невозможность авторизации при вводе невалидных логина и пароля", dataProvider = "LoginData")
    @Severity(SeverityLevel.CRITICAL)
    @Epic("SauceDemo 1.0")
    @Feature("Authorization in SauceDemo")
    @Story("Неуспешная авторизация")
    @TmsLink("www.jira.com/TK-5")
    @Issue("www.jira.com/TK-5")
    public void checkNegativeLogin(String user, String password, String message) {
        LoginPage.open();
        LoginPage.login(user, password);
        Assert.assertEquals(
                LoginPage.getErrorMessage(),
                message,
                "Отсутствует сообщение об ошибке");
    }
}
