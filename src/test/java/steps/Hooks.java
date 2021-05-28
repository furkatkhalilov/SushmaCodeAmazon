package steps;

import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utils.DriverWrapper;
import utils.DriverFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class Hooks {
    private static final ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();
    public static final ThreadLocal<String> browserName = new ThreadLocal<>();

    private WebDriver driver;
    private DriverWrapper driverWrapper;

    public Hooks(DriverWrapper driver) {
        this.driverWrapper = driver;
    }

    @Before
    public void start(){
        threadDriver.set(DriverFactory.createInstance(browserName.get()));
        driverWrapper.setDriver(threadDriver.get());
        driver = threadDriver.get();
        driver.get(readProperties("gnc.url"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS) ;
    }

    @After
    public void quit(Scenario scenario){

        if(scenario.isFailed()){
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }

        tearDown();
    }

    private String readProperties(String key) {
        Properties prop = new Properties();
        String propValue = "";
        URL resourcePath = Hooks.class.getClassLoader().getResource("config.properties");
        System.out.println("resourcePath = " + resourcePath);

        try(InputStream input = new FileInputStream(resourcePath.getPath())){
            prop.load(input);
            propValue =  prop.getProperty(key);
        }catch(IOException ex){
        }
        return propValue;
    }

    public static void tearDown() {

        if (threadDriver.get() != null) {
            threadDriver.get().quit();
            threadDriver.remove();
        }
    }

}
