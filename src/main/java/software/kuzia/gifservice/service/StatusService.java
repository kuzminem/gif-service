package software.kuzia.gifservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.kuzia.gifservice.controller.dto.GifData;

@Service
public class StatusService {
    private RatesService ratesService;
    private GiphyService giphyService;

    @Autowired
    public StatusService(RatesService ratesService, GiphyService giphyService) {
        this.ratesService = ratesService;
        this.giphyService = giphyService;
    }

    public GifData getStatusGif(String currency) {
        return giphyService.getGif(
                ratesService.getStatus(currency));
    }
}
