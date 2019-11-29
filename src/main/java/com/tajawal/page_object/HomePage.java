package com.tajawal.page_object;

import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
    @FindBy(id = "mobile-home-homeScreen-flight-cta")
    private MobileElement flightButton;

    public FlightsPage clickFlightButton() {
        waitAndClick(flightButton);
        return new FlightsPage();
    }
}
