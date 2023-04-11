package software.kuzia.gifservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.kuzia.gifservice.client.RatesClient;
import software.kuzia.gifservice.model.RatesModel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class RatesService {
    private RatesClient ratesClient;

    @Autowired
    public RatesService(RatesClient ratesClient) {
        this.ratesClient = ratesClient;
    }

    @Value("${openexchangerates.key}")
    private String ratesKey;

    @Value("${openexchangerates.base}")
    private String ratesBase;

    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public double getStatus(String ratesSymbols) {
        RatesModel todayRates = ratesClient.getTodayRates(ratesKey, ratesBase, ratesSymbols);
        if (todayRates.getRates().isEmpty()) {
            throw new IllegalStateException();
        }
        String yesterday = LocalDateTime.now().minusDays(1).format(formatter);
        RatesModel yesterdayRates = ratesClient.getYesterdayRates(yesterday, ratesKey, ratesBase, ratesSymbols);
        return todayRates.getRates().get(ratesSymbols) - yesterdayRates.getRates().get(ratesSymbols);
    }
}
