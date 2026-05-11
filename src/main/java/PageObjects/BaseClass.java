package PageObjects;

import UTILS.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.io.IOException;
import java.time.Duration;

public class BaseClass {

    protected WebDriver driver;
    protected Utils utils;
    protected String BASE_URL;

    public BaseClass(WebDriver driver) throws IOException {
        this.driver = driver;
        this.utils = new Utils();
        this.BASE_URL = utils.getUrl("base");
    }

    public Wait<WebDriver> doFluentWait(int timeout, int interval){
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofSeconds(interval))
                .ignoring(NoSuchElementException.class);
    }

    public void enter(String str, String locatorType, String ele, int millisecond){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(millisecond));
        WebElement element = null;

        switch (locatorType){
            case "xpath":
                element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ele)));
                break;
            case "id":
                element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(ele)));
                break;
            case "class":
                element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(ele)));
                break;
            case "css":
                element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(ele)));
                break;
            default:
                System.out.println("Wrong locator type");
                break;
        }

        if(element != null){
            element.sendKeys(str);
        }
    }

    public void clickButton(String ele, String locatorType, int millisecond){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(millisecond));
        WebElement element = null;

        switch (locatorType){
            case "xpath":
                element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ele)));
                break;
            case "id":
                element = wait.until(ExpectedConditions.elementToBeClickable(By.id(ele)));
                break;
            case "class":
                element = wait.until(ExpectedConditions.elementToBeClickable(By.className(ele)));
                break;
            case "css":
                element = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(ele)));
                break;
            default:
                System.out.println("Wrong locator type");
                break;
        }

        if(element != null){
            element.click();
        }
    }
}
