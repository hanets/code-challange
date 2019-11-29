package com.tajawal.page_object;

import com.tajawal.DriverPool;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BasePage {
    private Logger LOGGER = Logger.getLogger(this.getClass());
    protected AppiumDriver<MobileElement> driver;

    public BasePage() {
        this.driver = DriverPool.getDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    protected void waitAndClick(MobileElement mobileElement) {
        wait(mobileElement).click();
        LOGGER.info("clicked " + mobileElement);
    }

    protected void waitAndEnterText(MobileElement mobileElement, String text, int timeoutSeconds) {
        wait(mobileElement, timeoutSeconds).sendKeys(text);
        driver.hideKeyboard();
        LOGGER.info("entered '" + text + "' into " + mobileElement);
    }

    protected MobileElement wait(MobileElement mobileElement) {
        return wait(mobileElement, 20);
    }

    protected MobileElement wait(MobileElement mobileElement, int timeoutSeconds) {
        LOGGER.info("wait " + mobileElement);
        new WebDriverWait(driver, timeoutSeconds)
                .until(ExpectedConditions.visibilityOf(mobileElement));
        return mobileElement;
    }

    protected List<MobileElement> wait(List<MobileElement> mobileElements, int timeoutSeconds) {
        LOGGER.info("wait " + mobileElements);
        new WebDriverWait(driver, timeoutSeconds)
                .until(ExpectedConditions.visibilityOfAllElements(mobileElements.toArray(new WebElement[]{})));
        return mobileElements;
    }
}
