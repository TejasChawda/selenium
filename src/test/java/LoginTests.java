import PageObjects.DashboardPage;
import PageObjects.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTests extends BaseClass{

    LoginPage loginPage;
    DashboardPage dashboard;

    {
        try {
            loginPage = new LoginPage(driver);
            dashboard = new DashboardPage(driver);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
