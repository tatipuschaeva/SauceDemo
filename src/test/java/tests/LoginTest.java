package tests;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;

public class LoginTest extends BaseTest {

    @Test
    public void CheckPositiveAuthorization() {
        LoginPage.open();
        LoginPage.login("standard_user", "secret_sauce");
        Assert.assertTrue(ProductsPage.isShoppingCartButtonDisplayed());
    }

    @Test
    public void CheckLoginWithWrongPassword() {
        LoginPage.open();
        LoginPage.login("standard_user", "123456");
        Assert.assertEquals(
                LoginPage.getErrorMessage(),
                "Epic sadface: Username and password do not match any user in this service",
                "Отсутствует сообщение об ошибке");
    }

    @Test
    public void CheckLoginWithWrongUserName() {
        LoginPage.open();
        LoginPage.login("notCorrectUserMame", "secret_sauce");
        Assert.assertEquals(
                LoginPage.getErrorMessage(),
                "Epic sadface: Username and password do not match any user in this service",
                "Отсутствует сообщение об ошибке");
    }

    @Test
    public void CheckLoginWithUserNameNull() {
        LoginPage.open();
        LoginPage.login("", "secret_sauce");
        Assert.assertEquals(
                LoginPage.getErrorMessage(),
                "Epic sadface: Username is required",
                "Отсутствует сообщение об ошибке");
    }

    @Test
    public void CheckLoginWithPasswordNull() {
        LoginPage.open();
        LoginPage.login("standard_user", "");
        Assert.assertEquals(
                LoginPage.getErrorMessage(),
                "Epic sadface: Password is required",
                "Отсутствует сообщение об ошибке");
    }

}
