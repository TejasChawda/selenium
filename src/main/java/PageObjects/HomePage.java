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
        /*
         * We use JavascriptExecutor because Selenium itself does not provide
         * direct control over scrolling behavior.
         *
         * Scrolling is handled internally by the browser using JavaScript
         * and viewport rendering mechanisms.
         *
         * So, in Selenium, whenever we want to perform scrolling,
         * we execute JavaScript code through JavascriptExecutor.
         *
         * Example:
         *
         * JavascriptExecutor js = (JavascriptExecutor) driver;
         * js.executeScript("window.scrollBy(0,500)");
         *
         * Here:
         * 1. We create a JavascriptExecutor reference
         * 2. We inject JavaScript code into the browser using executeScript()
         *
         * ------------------------------------------------------------
         *
         * Why Selenium does not natively support scrolling:
         *
         * Selenium is mainly designed for browser automation and DOM interaction,
         * not direct browser rendering or viewport manipulation.
         *
         * During scrolling:
         * - The DOM structure usually remains exactly the same
         * - Only the viewport position changes
         *
         * In other words:
         * - The webpage/document does not move
         * - The visible area (viewport) moves over the document
         *
         * Since scrolling affects rendering and viewport state rather than
         * the actual DOM structure, Selenium delegates scrolling control
         * to the browser's JavaScript engine.
         *
         * ------------------------------------------------------------
         *
         * Similarly, methods like scrollIntoView() are also JavaScript methods.
         *
         * Example:
         *
         * js.executeScript(
         *      "arguments[0].scrollIntoView(true);",
         *      element
         * );
         *
         * Here the browser executes:
         *
         *      element.scrollIntoView(true)
         *
         * through JavaScript inside the page.
         */
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("window.scrollBy("+x+","+y+");");
    }

    public void scrollDownRelativeToAnElement(){

    }
}
