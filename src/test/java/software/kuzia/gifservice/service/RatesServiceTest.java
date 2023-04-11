package software.kuzia.gifservice.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;
import software.kuzia.gifservice.client.RatesClient;
import software.kuzia.gifservice.model.RatesModel;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class RatesServiceTest {
    private RatesClient ratesClient = Mockito.mock(RatesClient.class);
    private RatesService ratesService = new RatesService(ratesClient);

    private RatesModel higherRates = new RatesModel();
    private RatesModel lowerRates = new RatesModel();

    @Before
    public void common() {
        ReflectionTestUtils.setField(ratesService, "ratesKey", "b6c9de80f30d48f18cc13748e8d1bd9f");
        ReflectionTestUtils.setField(ratesService, "ratesBase", "USD");

        Map<String, Double> higherMap = new HashMap<>();
        higherMap.put("RUB", 69.0);
        higherRates.setRates(higherMap);

        Map<String, Double> lowerMap = new HashMap<>();
        lowerMap.put("RUB", 66.0);
        lowerRates.setRates(lowerMap);
    }

    @Test
    public void rich() {
        when(ratesClient.getTodayRates(anyString(), anyString(), anyString()))
                .thenReturn(higherRates);
        when(ratesClient.getYesterdayRates(anyString(), anyString(), anyString(), anyString()))
                .thenReturn(lowerRates);

        assertTrue(ratesService.getStatus("RUB") > 0);
    }

    @Test
    public void broke() {
        when(ratesClient.getTodayRates(anyString(), anyString(), anyString()))
                .thenReturn(lowerRates);
        when(ratesClient.getYesterdayRates(anyString(), anyString(), anyString(), anyString()))
                .thenReturn(higherRates);

        assertTrue(ratesService.getStatus("RUB") < 0);
    }

    @Test
    public void constant() {
        when(ratesClient.getTodayRates(anyString(), anyString(), anyString()))
                .thenReturn(lowerRates);
        when(ratesClient.getYesterdayRates(anyString(), anyString(), anyString(), anyString()))
                .thenReturn(lowerRates);

        assertEquals(0, ratesService.getStatus("RUB"), 0.0);
    }
}
