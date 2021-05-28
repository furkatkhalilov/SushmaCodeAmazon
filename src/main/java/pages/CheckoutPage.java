package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.HashMap;
import java.util.List;

public class CheckoutPage extends BasePage implements Page {
    private HashMap<String, List<WebElement>> listOfMap = new HashMap<>();

    private HashMap<String, WebElement> map = new HashMap<>();

    public CheckoutPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        initMap();
    }

    @FindBy (id = "ap_email")
    private WebElement emailInput;

    @FindBy (id = "continue")
    private WebElement continueButton;

    @FindBy (id = "ap_password")
    private WebElement passwordInput;


    @FindBy (id = "signInSubmit")
    private WebElement SubmitInput;

    @FindBy (partialLinkText = "Deliver to this address")
    private WebElement DeliverToThisAddressButton;

    @FindBy (className = "a-color-price")
    private WebElement priceInTheCheckout;



    @Override
    public void initMap() {
       map.put("Email Input Field",emailInput);
       map.put("Continue",continueButton);
       map.put("Password Input Field",passwordInput);
       map.put("Sign-In",SubmitInput);
       map.put("Deliver to this address",DeliverToThisAddressButton);
       map.put("Checkout Price",priceInTheCheckout);

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
