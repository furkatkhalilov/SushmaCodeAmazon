package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.List;

public class OrderPage extends BasePage implements Page {
    private HashMap<String, List<WebElement>> listOfMap = new HashMap<>();

    private HashMap<String, WebElement> map = new HashMap<>();

    public OrderPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        initMap();
    }

    @FindBy (id = "newBuyBoxPrice")
    private WebElement priceInTheOrderPage;

    @FindBy (xpath = "//div[contains(@id, 'addToCart')]")
    private WebElement addToCartButton;

    @FindBy (partialLinkText = "Proceed to checkout (1 item)")
    private WebElement proceedToCheckOutButton;


    @Override
    public void initMap() {
        map.put("Buy new price",priceInTheOrderPage);
        map.put("Add to Cart", addToCartButton);
        map.put("Proceed to checkout", proceedToCheckOutButton);
    }

    @Override
    public void findElementAndClickFunction(String element) {
        clickFunction(getWebElement(map,element));
    }

    @Override
    public void findElementAndSendKeyFunction(String element, String text) {
        sendKeysFunction(getWebElement(map, element), text);
    }

    @Override
    public WebElement getItemFromTheListByIndex(String ListOfWebElement, int index) {
        return getItemFromTheListByIndex(getListOfWebElement(listOfMap, ListOfWebElement), index);
    }

    @Override
    public WebElement getWebElement(String webElement) {
        return getWebElement(map,webElement);
    }


}
