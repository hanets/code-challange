package com.tajawal.page_object;

import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

public class FlightsPage extends BasePage {
    @FindBy(xpath = "//*[text() = 'From']")
    private MobileElement originInput;

    @FindBy(xpath = "//*[text() = 'To']")
    private MobileElement toInput;

    @FindBy(xpath = "//input")
    private MobileElement input;

    @FindBy(xpath = "//*[text() = 'Search Flights']")
    private MobileElement searchFlightsInput;

    public FlightsPage waitPageLoad() {
        wait(searchFlightsInput, 40);
        return this;
    }

    public FlightsPage enterOrigin(String originCode, String origin) {
        waitAndClick(originInput);
        waitAndEnterText(input, originCode, 60);
        waitAndClick(driver.findElement(By.xpath("//*[text() = '" + origin + "']")));
        return this;
    }

    public FlightsPage enterTo(String originCode, String origin) {
        waitAndClick(toInput);
        waitAndEnterText(input, originCode, 60);
        waitAndClick(driver.findElement(By.xpath("//*[text() = '" + origin + "']")));
        return this;
    }

    public FlightsResultsPage clickSearch() {
        waitAndClick(searchFlightsInput);
        return new FlightsResultsPage();
    }
}
