package com.bring.assessment.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class FlightFareDetails {

    private WebDriver driver;


    @FindBy(css = "[data-ref='outbound'] flight-card:first-of-type .card-header")
    public WebElement outboundFlightCard;

    @FindBy(css = "[data-ref='inbound'] flight-card:first-of-type .card-header")
    public WebElement inboundFlightCard;

    public FlightFareDetails(WebDriver _driver){
        this.driver = _driver;
        PageFactory.initElements(_driver, this);
    }
    public void selectInboundFare(){
        inboundFlightCard.click();
        WebDriverWait wait_until_card_container_visible = new WebDriverWait(driver, 20);
        wait_until_card_container_visible.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-ref='inbound'] .fare-card-container")));
        driver.findElement(By.cssSelector("[data-ref='inbound'] div[data-e2e='fare-card--standard'] button")).click();
    }

    public void selectOutboundFare(){
        outboundFlightCard.click();
        WebDriverWait wait_until_card_container_visible = new WebDriverWait(driver, 20);
        wait_until_card_container_visible.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-ref='outbound'] .fare-card-container")));
        driver.findElement(By.cssSelector("[data-ref='outbound'] div[data-e2e='fare-card--standard'] button")).click();
    }

    public void clickLoginLater(){
        WebDriverWait wait_until_card_container_visible = new WebDriverWait(driver, 20);
        wait_until_card_container_visible.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".login-touchpoint__expansion-bar")));
        driver.findElement(By.cssSelector(".login-touchpoint__expansion-bar")).click();
    }

    public void fillsOutPassengersData(String _name, String _lastname, String title, int cont){
        List<WebElement> passengers_cards = driver.findElements(By.cssSelector("pax-passenger"));
        WebElement passenger_card = passengers_cards.get(cont);
        passenger_card.findElement(By.cssSelector("[name*='.name']")).sendKeys(_name);
        passenger_card.findElement(By.cssSelector("[name*='.surname']")).sendKeys(_lastname);
        if(!title.equals("")) {
            passenger_card.findElement(By.cssSelector(".dropdown__toggle")).click();
            passenger_card.findElement(By.xpath("//div[text()='" + title + "']")).click();
        }
    }

    public void clickToContinuePurchase(){
        driver.findElement(By.cssSelector(".continue-flow__button"));
    }



}
