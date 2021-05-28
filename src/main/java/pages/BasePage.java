package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.HashMap;
import java.util.List;


public class BasePage {


    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver webdriver) {
        this.driver = webdriver;
        wait = new WebDriverWait(driver, 15);
    }

    public WebElement getWebElement(HashMap<String, WebElement> map, String mapKey){
        if(!map.containsKey(mapKey)){
            throw new RuntimeException("Element not found " + mapKey);
        }
        return map.get(mapKey);
    }

    public List<WebElement> getListOfWebElement(HashMap<String, List<WebElement>> listMap, String listMapKey){
        if(!listMap.containsKey(listMapKey)){
            throw new RuntimeException("List of Web Element not found " + listMapKey);

        }
        return listMap.get(listMapKey);
    }

    protected void clickFunction(WebElement element){
        waitUntilClickable(element);
        scrollToElement(element);
        element.click();

    }

    protected void sendKeysFunction(WebElement element , String value){

        waitUntilVisible(element);
        scrollToElement(element);
        element.clear();
        element.sendKeys(value);

    }

    public void waitUntilVisible(WebElement elementToWait){

        wait.until(ExpectedConditions.visibilityOf(elementToWait));

    }

    protected void waitUntilClickable(WebElement elmentToWait){
        wait.until(ExpectedConditions.elementToBeClickable(elmentToWait));
    }

    protected void scrollToElement(WebElement elementToScroll){
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("arguments[0].scrollIntoView(true);", elementToScroll);
    }

    public WebElement getItemFromTheListByIndex(List<WebElement> list, int index) {
        return list.get(index);
    }

}
