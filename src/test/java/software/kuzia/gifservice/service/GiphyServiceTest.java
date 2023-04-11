package software.kuzia.gifservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;
import software.kuzia.gifservice.client.GiphyClient;
import software.kuzia.gifservice.model.GiphyModel;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class GiphyServiceTest {
    private GiphyClient giphyClient = Mockito.mock(GiphyClient.class);
    private GiphyService giphyService = new GiphyService(giphyClient);

    @Before
    public void common() throws JsonProcessingException {
        ReflectionTestUtils.setField(giphyService, "giphyKey", "Wx0UxlLYsf8ZsDT9SeYUIFq8Mlrwt0LC");
        ReflectionTestUtils.setField(giphyService, "giphyTagRich", "rich");
        ReflectionTestUtils.setField(giphyService, "giphyTagBroke", "broke");
        ReflectionTestUtils.setField(giphyService, "giphyTagConstant", "constant");
        ReflectionTestUtils.setField(giphyService, "giphyRating", "g");

        ObjectMapper mapper = new ObjectMapper();

        GiphyModel richGiphyModel = mapper.readValue(
                "{\"data\":{\"images\":{\"original\":{\"url\":\"rich\"}}}}",
                GiphyModel.class);
        when(giphyClient.getGif(anyString(), eq("rich"), anyString()))
                .thenReturn(richGiphyModel);

        GiphyModel brokeGiphyModel = mapper.readValue(
                "{\"data\":{\"images\":{\"original\":{\"url\":\"broke\"}}}}}",
                GiphyModel.class);
        when(giphyClient.getGif(anyString(), eq("broke"), anyString()))
                .thenReturn(brokeGiphyModel);

        GiphyModel constantGiphyModel = mapper.readValue(
                "{\"data\":{\"images\":{\"original\":{\"url\":\"constant\"}}}}",
                GiphyModel.class);
        when(giphyClient.getGif(anyString(), eq("constant"), anyString()))
                .thenReturn(constantGiphyModel);
    }

    @Test
    public void rich() {
        Assert.assertEquals("rich", giphyService.getGif(1).getUrl());
    }

    @Test
    public void broke() {
        Assert.assertEquals("broke", giphyService.getGif(-1).getUrl());
    }

    @Test
    public void constant() {
        Assert.assertEquals("constant", giphyService.getGif(0).getUrl());
    }
}
