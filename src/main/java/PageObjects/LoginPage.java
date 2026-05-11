package PageObjects;

import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class LoginPage extends BaseClass{

    public String loginUrl = BASE_URL + utils.getUrl("login");

    public LoginPage(WebDriver driver) throws IOException {
        super(driver);
    }

    public void navigateToLoginPage() throws IOException {
        driver.get(loginUrl);
    }

    public void enterUserName(String str) throws IOException {
        String uNameLocator = utils.getLocatorString("login","user_name");
        enter(str, "xpath", uNameLocator, 10000);
    }

    public void enterPassword(String str) throws IOException {
        String passLocator = utils.getLocatorString("login","password");
        enter("admin123", "xpath", passLocator, 10000);
    }

    public DashboardPage clickOnLoginButton() throws IOException {
        String loginButton = utils.getLocatorString("login", "login_button");
        clickButton(loginButton, "css", 10000);

        return new DashboardPage(driver);
    }
}
