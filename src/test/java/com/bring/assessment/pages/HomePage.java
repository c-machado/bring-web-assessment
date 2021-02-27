package com.bring.assessment.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    private WebDriver driver;
    private String headingFlights = "FLIGHTS";

    @FindBy(css = ".tab-text")
    public WebElement headingHomeFlights;

    @FindBy(css = "button[data-ref='cookie.accept-all']")
    public WebElement accept_cookie_policy;

    @FindBy(id = "input-button__departure")
    public WebElement departure;

    @FindBy(id = "input-button__destination")
    public WebElement destination;

    

    public HomePage(WebDriver _driver){
        this.driver = _driver;
        PageFactory.initElements(_driver, this);
    }

    /**
     * Validates page's title content to confirm the page has been loaded
     * @return boolean
     */
    public boolean isPageOpen() {
        return headingHomeFlights.getText().contains(headingFlights);
    }

    public void clickAcceptCookiePolicy(){
        accept_cookie_policy.click();
    }

    public void enterDepartureAndDestination(String _departure, String _destination){
        departure.sendKeys(_departure);
        destination.sendKeys(_destination);
    }
}
