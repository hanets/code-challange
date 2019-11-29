package com.tajawal;

import com.tajawal.core.DriverPool;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    @BeforeMethod
    public void beforeMethod() {
        DriverPool.createDriver();
        DriverPool.getDriver().get("https://www.tajawal.com/");
    }

    @AfterMethod
    public void afterMethod() {
        //todo close driver
    }
}
