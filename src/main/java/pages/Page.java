package pages;

import org.openqa.selenium.WebElement;

public interface Page {

    void findElementAndClickFunction(String element);

    void findElementAndSendKeyFunction(String element, String text);

    public void initMap();

    public WebElement getItemFromTheListByIndex (String ListOfWebElement, int index);

    public WebElement getWebElement (String WebElement);
}
