package com.github.joraclista.kraken.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * Created by Alisa
 * version 1.0.
 */
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class AbstractKrakenResponse implements KrakenResponse {

    private boolean success = true;

    private String message;

    private String id;

    private Integer httpStatusCode;

    private String httpStatusText;

    private String imageOriginalUrl;



    @Data
    public static class MultipleResizeResponseImpl extends AbstractKrakenResponse {

        private Map<String, ResizeResponseItem> results;

    }

    @Data
    public static class SingleResizeResponseImpl extends OptimizeResponseImpl {

        @JsonProperty("kraked_width")
        private int krakedWidth;

        @JsonProperty("kraked_height")
        private int krakedHeight;

    }

    @Data
    public static class OptimizeResponseImpl extends AbstractKrakenResponse {
        @JsonProperty("file_name")
        private String fileName;

        @JsonProperty("original_size")
        private int originalSize;

        @JsonProperty("kraked_size")
        private int krakedSize;

        @JsonProperty("saved_bytes")
        private int savedBytes;

        @JsonProperty("kraked_url")
        private String krakedUrl;

        @JsonProperty("original_width")
        private int originalWidth;

        @JsonProperty("original_height")
        private int originalHeight;
    }

    @Data
    public static class UserStatusResponseImpl extends AbstractKrakenResponse {

        @JsonProperty("plan_name")
        private String planName;

        @JsonProperty("quota_total")
        private long quotaTotal;

        @JsonProperty("quota_used")
        private long quotaUsed;

        @JsonProperty("quota_remaining")
        private long quotaRemaining;

        @JsonProperty("original_width")
        private boolean active;
    }

}
