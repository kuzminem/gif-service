package software.kuzia.gifservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.kuzia.gifservice.client.GiphyClient;
import software.kuzia.gifservice.controller.dto.GifData;

@Service
public class GiphyService {
    private GiphyClient giphyClient;

    @Autowired
    public GiphyService(GiphyClient giphyClient) {
        this.giphyClient = giphyClient;
    }

    @Value("${giphy.key}")
    private String giphyKey;

    @Value("${giphy.rating}")
    private String giphyRating;

    @Value("${giphy.tag.rich}")
    private String giphyTagRich;

    @Value("${giphy.tag.broke}")
    private String giphyTagBroke;

    @Value("${giphy.tag.constant}")
    private String giphyTagConstant;

    public GifData getGif(double status) {
        GifData gifData = new GifData();

        if (status > 0) {
            gifData.setTag(giphyTagRich);
        } else if (status < 0) {
            gifData.setTag(giphyTagBroke);
        } else {
            gifData.setTag(giphyTagConstant);
        }
        gifData.setUrl(giphyClient.getGif(giphyKey, gifData.getTag(), giphyRating)
                .getData().getImages().getOriginal().getUrl());

        return gifData;
    }
}
