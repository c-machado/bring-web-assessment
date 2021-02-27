package com.bring.assessment.steps;

import com.bring.assessment.consts.Constants;
import com.bring.assessment.pages.*;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
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
    @Given("^I'm at the ryanair homepage$")
    public void iMAtTheHomepage() {
        driver.get(Constants.BASE_URL);
        homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isPageOpen());
    }

    @After
    public void close(){
        driver.close();
    }
}
