package software.kuzia.gifservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Объект для JSON, полученного от Giphy (Data.Images.Original.Url).
 */
@Getter
@Setter
@NoArgsConstructor
public class GiphyModel {
    private Data data;

    @Getter
    @Setter
    @NoArgsConstructor
    public class Data {
        private Images images;

        @Getter
        @Setter
        @NoArgsConstructor
        public class Images {
            private Original original;

            @Getter
            @Setter
            @NoArgsConstructor
            public class Original {
                private String url;
            }
        }
    }
}
