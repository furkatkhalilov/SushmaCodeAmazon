package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.HashMap;
import java.util.List;

public class MainPage extends BasePage implements Page {
    private HashMap<String, List<WebElement>> listOfMap = new HashMap<>();

    private HashMap<String, WebElement> map = new HashMap<>();

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        initMap();
    }

    @FindBy(id = "twotabsearchtextbox")
    private WebElement SearchInput;

    @FindBy(id = "nav-search-submit-button")
    private WebElement SearchButton;


    @FindBy(css = "div[data-component-type='s-search-result'] span[class='a-price']")
    private List<WebElement> ListOfProducts;


    @Override
    public void initMap() {
        map.put("Search Field",SearchInput);
        map.put("Search Button",SearchButton);
        listOfMap.put("Search output list",ListOfProducts);
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
    public WebElement getWebElement(String WebElement) {
        return null;
    }


}
