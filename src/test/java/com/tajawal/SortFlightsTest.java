package com.tajawal;

import com.tajawal.bussiness_object.FlightsSearchBO;
import com.tajawal.page_object.FlightsResultsPage;
import org.testng.annotations.Test;

import java.util.List;

public class SortFlightsTest extends BaseTest {
    @Test
    public void sortFlightsTest() {
        FlightsResultsPage flightsResultsPage = FlightsSearchBO.openRandomFlightResultPage();

        List<String> priceList = flightsResultsPage.waitPageLoad()
                .getPriceList();

        FlightsSearchBO.verifyPriceSorted(priceList);
    }
}
