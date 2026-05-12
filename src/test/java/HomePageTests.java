import PageObjects.HomePage;
import PageObjects.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class HomePageTests extends BaseTest {

    HomePage homePage;
    LoginPage loginPage;

    @BeforeClass
    public void initialiseObject() throws IOException {
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testMenuNavigation() throws IOException {
        loginPage.navigateToLoginPage();
        loginPage.enterUserName("Admin");
        loginPage.enterPassword("admin123");
        homePage = loginPage.clickOnLoginButton();
        homePage.clickOnSideBarMenuItem("admin");

        Assert.assertTrue(driver.getCurrentUrl().contains
                (homePage.getHighlightedMenuItem().toLowerCase()));
    }
}
