package software.kuzia.gifservice.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import software.kuzia.gifservice.controller.dto.GifData;
import software.kuzia.gifservice.service.GiphyService;
import software.kuzia.gifservice.service.RatesService;
import software.kuzia.gifservice.service.StatusService;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(StatusController.class)
public class StatusControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StatusService statusService;

    @MockBean
    private RatesService ratesService;

    @MockBean
    private GiphyService giphyService;

    @Test
    public void statusControllerGetTest() throws Exception {
        when(ratesService.getStatus("TST"))
                .thenReturn(1.0);
        when(giphyService.getGif(anyDouble()))
                .thenReturn(new GifData("test", "test"));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/status/TST")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
