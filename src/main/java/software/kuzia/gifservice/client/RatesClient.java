package software.kuzia.gifservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import software.kuzia.gifservice.model.RatesModel;

/**
 * Feign-клиент OpenExchangeRates.
 */
@FeignClient(
        name = "RatesFeignClient",
        url = "${openexchangerates.url}")
public interface RatesClient {
    @GetMapping("/latest.json")
    RatesModel getTodayRates(
            @RequestParam("app_id") String key,
            @RequestParam("base") String base,
            @RequestParam("symbols") String symbols);

    @GetMapping("/historical/{date}.json")
    RatesModel getYesterdayRates(
            @PathVariable String date,
            @RequestParam("app_id") String key,
            @RequestParam("base") String base,
            @RequestParam("symbols") String symbols);
}
