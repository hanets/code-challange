package com.tajawal.core;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class DriverFactory {
    private final static Logger LOGGER = Logger.getLogger(DriverFactory.class);

    public static AppiumDriver<MobileElement> createDriver() {
        AppuimServer appuimServer = new AppuimServer();
        URL url = appuimServer.startServer();//todo add close server
        LOGGER.info("server started");
        DesiredCapabilities androidWebCapabilities = getAndroidWebCapabilities();
        LOGGER.info("start appium driver");
        return new AndroidDriver<>(url, androidWebCapabilities);
    }

    private static DesiredCapabilities getAndroidWebCapabilities() {
        String emulatorName = "Nexus_6";//todo move device name to property

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.0");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, emulatorName);
        capabilities.setCapability(AndroidMobileCapabilityType.AVD, emulatorName);
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 300);
        return capabilities;
    }


}
