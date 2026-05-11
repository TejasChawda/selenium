import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    public WebDriver createDriverObject(String str){
        if(str.equalsIgnoreCase("chrome")){
            return new ChromeDriver();
        }else if(str.equalsIgnoreCase("firefox")){
            return new FirefoxDriver();
        }else{
            throw new IllegalArgumentException("Invalid driver passed" +str);
        }
    }
}
