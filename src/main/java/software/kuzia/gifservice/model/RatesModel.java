package software.kuzia.gifservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

/**
 * Объект для JSON, полученного от OpenExchangeRates.
 */
@Getter
@Setter
@NoArgsConstructor
public class RatesModel {
    private Map<String, Double> rates;
}
