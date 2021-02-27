package com.bring.assessment.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FlightFareDetails {

    private WebDriver driver;


    @FindBy(css = "[data-ref='outbound'] flight-card:first-of-type")
    public WebElement outboundFlightCard;

    @FindBy(css = "[data-ref='inbound'] flight-card:first-of-type")
    public WebElement inboundFlightCard;

    public FlightFareDetails(WebDriver _driver){
        this.driver = _driver;
        PageFactory.initElements(_driver, this);
    }
    public void selectInboundFare(){
        inboundFlightCard.click();
        WebDriverWait wait_until_card_container_visible = new WebDriverWait(driver, 10);
        wait_until_card_container_visible.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-ref='inbound'] .fare-card-container")));
        driver.findElement(By.cssSelector("div[data-e2e='fare-card--standard'] button")).click();
    }

    public void selectOutboundFare(){
        outboundFlightCard.click();
        WebDriverWait wait_until_card_container_visible = new WebDriverWait(driver, 10);
        wait_until_card_container_visible.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-ref='outbound'] .fare-card-container")));
        driver.findElement(By.cssSelector("div[data-e2e='fare-card--standard'] button")).click();
    }



}
