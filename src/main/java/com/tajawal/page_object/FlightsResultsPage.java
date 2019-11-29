package com.tajawal.page_object;

import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

public class FlightsResultsPage extends BasePage {
    private static final String PRICE_CLASS_NAME = "jss1191";

    @FindBy(className = PRICE_CLASS_NAME)
    private List<MobileElement> prices;

    @FindBy(id = "flight-search-results")
    private MobileElement searchResult;

    @FindBy(xpath = "//input")
    private MobileElement input;

    @FindBy(xpath = "//*[text() = 'Search Flights']")
    private MobileElement searchFlightsInput;

    public FlightsResultsPage waitPageLoad() {
        wait(searchResult, 40);
        return this;
    }

    public List<String> getPriceList() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.numberOfElementsToBeMoreThan(By.className(PRICE_CLASS_NAME), 1));
        return wait(prices, 10).stream()
                .map(RemoteWebElement::getText)
                .collect(Collectors.toList());
    }
}
