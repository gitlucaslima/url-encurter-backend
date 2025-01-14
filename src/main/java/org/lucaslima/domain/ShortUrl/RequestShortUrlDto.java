package org.lucaslima.domain.ShortUrl;

public class RequestShortUrlDto {
    public String originalUrl;

    public RequestShortUrlDto() {
    }

    public RequestShortUrlDto(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }
}
