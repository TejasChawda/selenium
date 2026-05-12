package PageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

import java.io.IOException;
import java.util.List;

public class HomePage extends BaseClass{

    public HomePage(WebDriver driver) throws IOException {
        super(driver);
    }

    public List<WebElement> getSideBarItems() throws IOException {
        String eleLocatorString = utils.getLocatorString("home", "sidebar_menu_items");
        Wait<WebDriver> wait = constructWait("explicit", 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.
                cssSelector(eleLocatorString)));
        return driver.findElements(By.cssSelector(
                eleLocatorString
        ));
    }

    public String getHighlightedMenuItem() throws IOException {
        for(WebElement sideBarItem : getSideBarItems()){
            WebElement link = sideBarItem.findElement(By.tagName("a"));
            String classAttr = link.getAttribute("class");
            if(classAttr != null && classAttr.contains("active")){
                return link.getText();
            }
        }

        return "";
    }

    public void clickOnSideBarMenuItem(String item) throws IOException {
        WebElement menuItem;

        for(WebElement sideBarItem : getSideBarItems()){
            menuItem = sideBarItem.findElement(By.
                            cssSelector(utils.
                                    getLocatorString("home", "sidebar_menu_item_name")));
            String it = menuItem.getText();
            if(it.equalsIgnoreCase(item)){
                menuItem.click();
                break;
            }
        }
    }

    public void scrollDownUsingPixels(int x, int y){
        //We use jsexecutor, because selenium doesn't have the control for scrolling,
        //it is controlled by js and DOM, so in order to use js, we use jsexecutor.
        //Based on the below code, we can understand that we create a jsexecutor object
        //and we induce js code into it using jsobject.executeScript()

        //Here is a reason why selenium doesn't support scrolling.
        //Selenium is only built for DOM interaction, not DOM manipulation
        //Scrolling doesn't mean DOM manipulation, the DOM stays the same
        //But the view port changes whenever there is a scroll involved.
        //This impacts what to be rendered on the screen. Thus selenium doesn't support
        //Scrolling.

        //Similarly we can use scrollIntoView in the same manner with jsexecutor
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("window.scrollBy("+x+","+y+");");
    }

    public void scrollDownRelativeToAnElement(){

    }
}
