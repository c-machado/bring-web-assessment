package com.bring.assessment.steps;

import com.bring.assessment.consts.Constants;
import com.bring.assessment.pages.*;
import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class SearchTrip {

    private WebDriver driver;
    private HomePage homePage;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
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

    @After
    public void close(){
        driver.close();
    }


}
