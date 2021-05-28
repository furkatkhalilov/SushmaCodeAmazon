package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pages.BasePage;
import utils.DriverWrapper;
import utils.BaseClass;

import java.util.List;

public class Steps {
    private WebDriver driver;
    private BaseClass baseClass;
    private double priceInTheMainPage;

    public void setPriceInTheMainPage(double priceInTheMainPage) {
        this.priceInTheMainPage = priceInTheMainPage;
    }

    public Steps(DriverWrapper driver) {
        this.driver = driver.getDriver();
        baseClass = new BaseClass(this.driver);
    }

    @And("User clicks in the {string}")
    public void userClicksInThe(String page, DataTable dataTable ) {
        List<String> AllElementsInTheList = dataTable.asList(String.class);

        for (int i = 0; i < AllElementsInTheList.size(); i++) {
            baseClass.getPage(page).findElementAndClickFunction(AllElementsInTheList.get(i));
        }
    }


    @And("User sends text in the {string}")
    public void userSendsTextInThe(String page, DataTable dataTable) {
        List<List<String>> AllElementsInTheList = dataTable.asLists(String.class);
        for (int i = 0; i < AllElementsInTheList.size(); i++) {
            baseClass.getPage(page).findElementAndSendKeyFunction(AllElementsInTheList.get(i).get(0), AllElementsInTheList.get(i).get(1));
        }
    }



    @And("In the {string} user clicks on the item from the list by index")
    public void inTheUserClicksOnTheItemFromTheListByIndex(String page, DataTable dataTable) {
        List<List<String>> AllElementsInTheList = dataTable.asLists(String.class);
        for (int i = 0; i < AllElementsInTheList.size(); i++) {
            WebElement itemFromTheListByIndex = baseClass.getPage(page).getItemFromTheListByIndex(AllElementsInTheList.get(i).get(0), Integer.parseInt(AllElementsInTheList.get(i).get(1)));
            itemFromTheListByIndex.click();
        }
    }


    @Then("User verifies an item from the list in the {string} has the price")
    public void userVerifiesAnItemFromTheListInTheHasThePrice(String page, DataTable dataTable) {
        List<List<String>> AllElementsInTheList = dataTable.asLists(String.class);
        for (int i = 0; i < AllElementsInTheList.size(); i++) {
            WebElement itemFromTheListByIndex = baseClass.getPage(page).getItemFromTheListByIndex(AllElementsInTheList.get(i).get(0), Integer.parseInt(AllElementsInTheList.get(i).get(1)));
            String innerHTML = itemFromTheListByIndex.findElement(By.className("a-offscreen")).getAttribute("innerHTML");
            Assert.assertFalse(innerHTML.isEmpty());
            double price = Double.parseDouble(innerHTML.replaceAll("[$]",""));
            setPriceInTheMainPage(price);
            System.out.println(priceInTheMainPage);
        }
    }


    @Then("User verifies price in the {string}")
    public void userVerifiesPriceInThe(String page, DataTable dataTable) {
        List<String> AllElementsInTheList = dataTable.asList(String.class);
        for (int i = 0; i < AllElementsInTheList.size(); i++) {
            WebElement webElement = baseClass.getPage(page).getWebElement(AllElementsInTheList.get(0));
            String price = webElement.getText();
            double priceInTheOrderPage = Double.parseDouble(price.replaceAll("[$]",""));
            Assert.assertEquals(priceInTheMainPage,priceInTheOrderPage);
        }
    }
}
