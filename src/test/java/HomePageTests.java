import PageObjects.HomePage;
import PageObjects.LoginPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class HomePageTests extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(HomePageTests.class);
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

    @Test
    public void testScrollingInHomepage() throws IOException {
        loginPage.navigateToLoginPage();
        loginPage.enterUserName("Admin");
        loginPage.enterPassword("admin123");
        homePage = loginPage.clickOnLoginButton();
        homePage.scrollDownUsingPixels(0, 2000); //Simple scroll using pixels, x is for left to right,
                                    //y is for up to down
                                    //u can also pass the values in negative to see the opposite
                                    //scroll behaviour

    }
}
