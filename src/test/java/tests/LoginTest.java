package tests;
import io.qameta.allure.*;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;

public class LoginTest extends BaseTest {

    @Test (testName = "Успешная авторизация пользователя в системе")
    @Description("Необходимо проверить возможность успешной авторизации пользователя")
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

    @Test (testName = "Неуспешная авторизация при вводе несуществующего пароля")
    @Description("Необходимо проверить, что авторизация невозможна при вводе некорректного пароля")
    @Severity(SeverityLevel.CRITICAL)
    @Epic("SauceDemo 1.0")
    @Feature("Authorization in SauceDemo")
    @Story("Неуспешная авторизация")
    @TmsLink("www.jira.com/TK-2")
    @Issue("www.jira.com/TK-2")
    public void CheckLoginWithWrongPassword() {
        LoginPage.open();
        LoginPage.login("standard_user", "123456");
        Assert.assertEquals(
                LoginPage.getErrorMessage(),
                "Epic sadface: Username and password do not match any user in this service",
                "Отсутствует сообщение об ошибке");
    }

    @Test (testName = "Неуспешная авторизация при вводе несуществующего логина")
    @Description("Необходимо проверить, что авторизация невозможна при вводе некорректного логина")
    @Severity(SeverityLevel.CRITICAL)
    @Epic("SauceDemo 1.0")
    @Feature("Authorization in SauceDemo")
    @Story("Неуспешная авторизация")
    @TmsLink("www.jira.com/TK-3")
    @Issue("www.jira.com/TK-3")
    public void CheckLoginWithWrongUserName() {
        LoginPage.open();
        LoginPage.login("notCorrectUserMame", "secret_sauce");
        Assert.assertEquals(
                LoginPage.getErrorMessage(),
                "Epic sadface: Username and password do not match any user in this service",
                "Отсутствует сообщение об ошибке");
    }

    @Test (testName = "Невозможность авторизации без ввода логина")
    @Description("Необходимо проверить, что авторизация невозможна если не введен логин")
    @Severity(SeverityLevel.CRITICAL)
    @Epic("SauceDemo 1.0")
    @Feature("Authorization in SauceDemo")
    @Story("Неуспешная авторизация")
    @TmsLink("www.jira.com/TK-4")
    @Issue("www.jira.com/TK-4")
    public void CheckLoginWithUserNameNull() {
        LoginPage.open();
        LoginPage.login("", "secret_sauce");
        Assert.assertEquals(
                LoginPage.getErrorMessage(),
                "Epic sadface: Username is required",
                "Отсутствует сообщение об ошибке");
    }

    @Test (testName = "Невозможность авторизации без ввода пароля")
    @Description("Необходимо проверить, что авторизация невозможна если не введен пароль")
    @Severity(SeverityLevel.CRITICAL)
    @Epic("SauceDemo 1.0")
    @Feature("Authorization in SauceDemo")
    @Story("Неуспешная авторизация")
    @TmsLink("www.jira.com/TK-5")
    @Issue("www.jira.com/TK-5")
    public void CheckLoginWithPasswordNull() {
        LoginPage.open();
        LoginPage.login("standard_user", "");
        Assert.assertEquals(
                LoginPage.getErrorMessage(),
                "Epic sadface: Password is required",
                "Отсутствует сообщение об ошибке");
    }
}
