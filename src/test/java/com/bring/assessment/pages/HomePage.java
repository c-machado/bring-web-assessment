package com.bring.assessment.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.util.List;

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

    @FindBy(css = ".datepicker__calendars calendar:nth-of-type(2)")
    public WebElement depart_calendar;

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
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Actions action = new Actions(driver);
        int len_depart_text = departure.getText().length();
        System.out.println("TEXTXXXXX "+departure.getAttribute("innerHTML"));
        System.out.println("TEXTXXXXX "+departure.getAttribute("placeholder"));
        System.out.println("LENGTHHHHHHHHH "+len_depart_text);
        for(int i = 0; i<len_depart_text; i++){
            action.sendKeys(Keys.ARROW_LEFT);
        }
        action.build().perform();
        for(int i = 0; i < len_depart_text; i++){
            action.sendKeys(Keys.DELETE);
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        action.build().perform();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        departure.sendKeys(_departure);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        destination.sendKeys(_destination);
    }

    public void enterDepartAndReturnDates(String _depart_date, String _return_date){
        WebDriverWait waitUntilCalendarIsVisible = new WebDriverWait(driver, 10);
        waitUntilCalendarIsVisible.until(ExpectedConditions.visibilityOf(depart_calendar));
        List<WebElement> cols = depart_calendar.findElements(By.tagName("div"));
        for (WebElement cell: cols){
            System.out.println(cell.getText());
        }
    }
}
