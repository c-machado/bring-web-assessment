package com.bring.assessment.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.util.List;

public class HomePage {

    private WebDriver driver;
    private String headingFlights = "FLIGHTS";


    @FindBy(css = "button[data-ref='cookie.accept-all']")
    public WebElement accept_cookie_policy;

    @FindBy(id = "input-button__departure")
    public WebElement departure;

    @FindBy(id = "input-button__destination")
    public WebElement destination;

    @FindBy(css = ".tab-text")
    public WebElement headingHomeFlights;

    @FindBy(css = "button[aria-label='Search']")
    public WebElement searchCTA;

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

    public void enterDepartureAndDestination(String _departure, String _destination) throws InterruptedException {
        int len_depart_text = departure.getAttribute("value").length();
        for(int i = 0; i<len_depart_text; i++){
            departure.sendKeys(Keys.BACK_SPACE);
        }
        departure.sendKeys("");
        WebDriverWait wait_departure_empty= new WebDriverWait(driver, 10);
        wait_departure_empty.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("#ry-tooltip-1"), 1));
        departure.clear();
        departure.sendKeys(_departure);

        destination.sendKeys("");
        WebDriverWait waitForSpinner= new WebDriverWait(driver, 10);
        waitForSpinner.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("#ry-tooltip-2"), 1));
        destination.clear();
        destination.sendKeys(_destination);
        getAirportByName(_destination).click();
    }

    public WebElement getAirportByName(String _airportName){
        List<WebElement> airports = driver.findElements(By.xpath("//span[@data-ref='airport-item__name']"));
        for(WebElement airportElement: airports){
            if (airportElement.getText().equals(_airportName));
            {
                return airportElement;
            }
        }
        return null;
    }

    public void enterDepartAndReturnDates(String _depart_date, String _return_date) {
        WebElement departure_calendar = driver.findElement(By.cssSelector(".datepicker__calendars calendar:nth-of-type(2)"));
        WebDriverWait wait_until_calendar_is_visible = new WebDriverWait(driver, 10);
        wait_until_calendar_is_visible.until(ExpectedConditions.visibilityOf(departure_calendar));
        WebElement calendar_depart = driver.findElement(By.cssSelector(".datepicker__calendars calendar:nth-of-type(1)"));
        selectDate(_depart_date, calendar_depart);
        WebElement calendar_return = driver.findElement(By.cssSelector(".datepicker__calendars calendar:nth-of-type(2)"));
        selectDate(_return_date, calendar_return);
    }

    public void selectDate(String date, WebElement calendar)  {
        String[] arrDate = date.split(" ");
        String month = arrDate[0];
        String day = arrDate[1].trim();
        String year = arrDate[2];
        WebElement month_name = calendar.findElement(By.cssSelector(".calendar__month-name"));
        String month_date = month + " " + year;
        if (month_name.getText().equals(month_date)) {
            calendar.findElement(By.cssSelector("[data-value='" + day + "']")).click();
        } else {
            driver.findElement(By.cssSelector("[data-ref='calendar-btn-next-month']")).click();
            selectDate(date, calendar);
        }
    }

    public void selectNumberOfPassengers(int number_of_passengers, String passengers){
        WebElement increment_button = driver.findElement(By.cssSelector("[data-ref='passengers-picker__children'] [data-ref='counter.counter__increment']"));
        WebElement passenger_counter = driver.findElement(By.cssSelector("[data-ref='passengers-picker__children'] [data-ref='counter.counter__value']"));
        if(passengers.equals("adults")){
            increment_button = driver.findElement(By.cssSelector("[data-ref='passengers-picker__adults'] [data-ref='counter.counter__increment']"));
            passenger_counter = driver.findElement(By.cssSelector("[data-ref='passengers-picker__adults'] [data-ref='counter.counter__value']"));
        }
        while (number_of_passengers > Integer.parseInt(passenger_counter.getText())){
            increment_button.click();
        }
    }

    public void confirmNumberOfPassengers(){
        driver.findElement(By.cssSelector(".passengers__confirm-button")).click();
    }

    public void selectTermOfUse(){
        driver.findElement(By.cssSelector("[data-ref='terms-of-use__terms-checkbox'] ._container")).click();
    }

    public void clickToSearchFlights(){
        searchCTA.click();
    }



}
