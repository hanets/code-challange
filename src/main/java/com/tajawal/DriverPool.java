package com.tajawal;

import com.tajawal.core.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class DriverPool {
    private static InheritableThreadLocal<AppiumDriver<MobileElement>> driver = new InheritableThreadLocal<>();

    public static void createDriver() {
        driver.set(DriverFactory.createDriver());
    }

    public static AppiumDriver<MobileElement> getDriver() {
        return driver.get();
    }

    public static void main(String[] args) {
        createDriver();
    }
}
