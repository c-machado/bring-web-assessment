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
}
