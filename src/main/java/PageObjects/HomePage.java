package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
}
