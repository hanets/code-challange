package com.tajawal.bussiness_object;

import com.tajawal.page_object.FlightsPage;
import com.tajawal.page_object.FlightsResultsPage;
import com.tajawal.page_object.HomePage;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class FlightsSearchBO {
    public static FlightsResultsPage openRandomFlightResultPage() {
        FlightsPage flightsPage = new HomePage().clickFlightButton();
        //todo generate random data
        return flightsPage.waitPageLoad()
                .enterOrigin("DXB", "Dubai International Airport")
                .enterTo("AMM", "Queen Alia International Airport")
                .clickSearch();
    }

    public static void verifyPriceSorted(List<String> priceList) {
        Assert.assertTrue(priceList.size() > 1, "price list size is " + priceList.size());
        SoftAssert softAssert = new SoftAssert();
        for (int i = 0; i < priceList.size(); i++) {
            String price = priceList.get(i);
            softAssert.assertTrue(price.startsWith("AED "), "currency should be AED. actual " + price);
            if (i != priceList.size() - 1) {
                String priceNext = priceList.get(i + 1);
                softAssert.assertTrue(priceToNumber(price) <= priceToNumber(priceNext),
                        "price is not sorted " + price + " is more than " + priceNext);
            }
        }
        softAssert.assertAll();
    }

    private static int priceToNumber(String price) {
        return Integer.parseInt(price.replace("AED ", "").replace(",", ""));
    }
}
