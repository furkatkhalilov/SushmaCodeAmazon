package utils;

import org.openqa.selenium.WebDriver;
import pages.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;

public class BaseClass {

    private WebDriver webdriver;

    private List<Page> list = new ArrayList<>();

    public BaseClass(WebDriver driver) {
        this.webdriver = driver;
        initPageList();

    }

    public Page getPage(String pageName){
        Predicate<Page> predicate = obj -> obj.getClass().toString().toUpperCase(Locale.ROOT).contains(pageName.toUpperCase(Locale.ROOT));
        Page page = list.stream().filter(predicate).findFirst().orElse(null);
        if (page == null) {
            throw new RuntimeException("Page not found " + pageName);
        }
        return page;
    }

    public void initPageList(){
        list.add(new MainPage(webdriver));
        list.add(new OrderPage(webdriver));
        list.add(new CheckoutPage(webdriver));

    }


}
