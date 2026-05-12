import PageObjects.HomePage;
import PageObjects.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTests extends BaseTest {

    LoginPage loginPage;
    HomePage dashboard;

    @BeforeClass
    public void initialiseObjects() throws IOException {
        loginPage = new LoginPage(driver);
        dashboard = new HomePage(driver);
    }

    @Test
    public void shouldNavigateToLogin() throws IOException {
        loginPage.navigateToLoginPage();
        Assert.assertEquals(driver.getCurrentUrl(), loginPage.loginUrl);
    }

    @Test
    public void shouldLogin() throws IOException {
        loginPage.navigateToLoginPage();
        loginPage.enterUserName("Admin");
        loginPage.enterPassword("admin123");
        dashboard = loginPage.clickOnLoginButton();
    }

}
