package com.bring.assessment.steps;

import com.bring.assessment.consts.Constants;
import com.bring.assessment.pages.*;
import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class SearchTrip {

    private WebDriver driver;
    private HomePage homePage;
    private FlightFareDetails fareDetails;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }
    @Given("^a user is at the ryanair homepage$")
    public void iMAtTheHomepage() {
        driver.get(Constants.BASE_URL);
        homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isPageOpen());
    }

    @And("^the user accepts the Cookie policy to continue navigating$")
    public void theUserAcceptsTheCookiePolicyToContinueNavigating() {
        homePage.clickAcceptCookiePolicy();
    }

    @When("^the user chooses \"([^\"]*)\" and \"([^\"]*)\" to travel$")
    public void theUserChoosesAndToTravel(String departure, String destination) throws InterruptedException {
        homePage.enterDepartureAndDestination(departure, destination);
    }

    @And("^the user selects \"([^\"]*)\" and \"([^\"]*)\" dates$")
    public void theUserSelectsAndDates(String depart_date, String return_date) {
        homePage.enterDepartAndReturnDates(depart_date, return_date);
    }

    @And("^the user chooses the number of \"([^\"]*)\" and \"([^\"]*)\" passengers$")
    public void theUserChoosesTheNumberOfAndPassengers(String adults, String children) throws Throwable {
        homePage.selectNumberOfPassengers(Integer.parseInt(adults), "adults");
        homePage.selectNumberOfPassengers(Integer.parseInt(children), "children");
        homePage.confirmNumberOfPassengers();
    }

    @And("^the user performs the Search$")
    public void theUserClicksOnTheSearchCTA() {
        homePage.selectTermOfUse();
        homePage.clickToSearchFlights();
    }

    @Then("^the user selects the value fare card$")
    public void theUserSelectsTheValueFareCard() throws InterruptedException {
        fareDetails = new FlightFareDetails(driver);
        fareDetails.selectOutboundFare();
        fareDetails.selectInboundFare();
        fareDetails.clickLoginLater();
    }


    @And("^the user fills out the passengers data$")
    public void theUserFillsOutThePassengersData(DataTable dataTable) {
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        int cont = 0;
        for (Map<String, String> config : maps) {
            fareDetails.fillsOutPassengersData(config.get("name"),config.get("lastname"),config.get("title"), cont);
            cont ++;
        }
    }

    @And("^the user continues with the purchase$")
    public void theUserContinuesWithThePurchase() {
        fareDetails.clickToContinuePurchase();
    }

    @After
    public void close(){
        driver.close();
    }

}
