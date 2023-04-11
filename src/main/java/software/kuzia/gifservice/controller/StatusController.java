package software.kuzia.gifservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import software.kuzia.gifservice.controller.dto.GifData;
import software.kuzia.gifservice.controller.dto.Response;
import software.kuzia.gifservice.service.StatusService;

@RestController()
public class StatusController {
    private StatusService statusService;

    @Autowired
    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping("/status/{ratesSymbols}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Response<GifData> getStatusGif(@PathVariable String ratesSymbols) {
        return new Response<>(
                HttpStatus.OK.value(),
                statusService.getStatusGif(ratesSymbols));
    }
}
