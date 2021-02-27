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

    @FindBy(css = ".datepicker__calendars calendar:nth-of-type(2)")
    public WebElement depart_calendar;

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

    public void clickAcceptCookiePolicy(){
        accept_cookie_policy.click();
    }

    public void enterDepartureAndDestination(String _departure, String _destination) throws InterruptedException {
        int len_depart_text = departure.getAttribute("value").length();
        for(int i = 0; i<len_depart_text; i++){
            departure.sendKeys(Keys.BACK_SPACE);
        }
        WebDriverWait wait_departure_empty= new WebDriverWait(driver, 10);
        wait_departure_empty.until(ExpectedConditions.textToBePresentInElement(departure,""));
        departure.sendKeys(_departure);
        destination.sendKeys(_destination);
        WebDriverWait wait_destination_not_empty= new WebDriverWait(driver, 20);
        wait_destination_not_empty.until(ExpectedConditions.attributeToBeNotEmpty(destination,"value"));
        getAirportByName(_destination).click();
    }

    public WebElement getAirportByName(String _airportName){
        List<WebElement> airports = driver.findElements(By.xpath("//span[@data-ref='airport-item__name']"));
        for(WebElement airportElement: airports){
            System.out.println("element "+airportElement.getText());
            if (airportElement.getText().equals(_airportName));
            {
                return airportElement;
            }
        }
        return null;
    }

    public void enterDepartAndReturnDates(String _depart_date, String _return_date){
        WebElement departure_calendar = driver.findElement(By.cssSelector(".datepicker__calendars calendar:nth-of-type(2)"));
        WebDriverWait wait_until_calendar_is_visible = new WebDriverWait(driver, 10);
        wait_until_calendar_is_visible.until(ExpectedConditions.visibilityOf(departure_calendar));
        selectDate(_depart_date);
    }

    public void selectDate(String date){
        WebElement calendar = driver.findElement(By.cssSelector(".datepicker__calendars calendar:nth-of-type(2)"));
        String[] arrDate = date.split(" ");
        String month = arrDate[0];
        String day = arrDate[1].trim();
        String year = arrDate[2];
        WebElement month_name = driver.findElement(By.cssSelector(".calendar__month-name"));
        String month_date = month.concat(year);
        System.out.println("month date " + month_date);
        if (month_name.getText().equals(month_date))
            calendar.findElement(By.cssSelector("[data-value='" + day +"']")).click();
        else {
            //driver.findElement(By.cssSelector(".datepicker__arrow-wrap")).click();
            selectDate(date);
        }
    }





}
