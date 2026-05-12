import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    WebDriver driver;
    DriverFactory factory = new DriverFactory();

    @BeforeTest
    public void setUp(){
        driver = factory.createDriverObject("chrome");
    }

    @AfterTest
    public void tearDown(){
        if(driver != null){
            driver.close();
        }
    }
}
