package software.kuzia.gifservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import software.kuzia.gifservice.model.GiphyModel;

/**
 * Feign-клиент Giphy.
 */
@FeignClient(
        name = "GiphyFeignClient",
        url = "${giphy.url}")
public interface GiphyClient {
    @GetMapping("/random")
    GiphyModel getGif(
            @RequestParam("api_key") String key,
            @RequestParam("tag") String tag,
            @RequestParam("rating") String rating);
}
