import PageObjects.DashboardPage;
import PageObjects.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseClass {

    WebDriver driver;
    DriverFactory factory = new DriverFactory();

    @BeforeClass
    public void setUp(){
        driver = factory.createDriverObject("chrome");
    }

    @AfterClass
    public void tearDown(){
        if(driver != null){
            driver.close();
        }
    }
}
