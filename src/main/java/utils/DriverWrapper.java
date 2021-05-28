package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverWrapper {
    private WebDriver driver;

    public WebDriver getDriver(){
      return driver;
    }

    public void setDriver(WebDriver driver){
        this.driver = driver;
    }
}
