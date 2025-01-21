package org.lucaslima.domain.ShortUrl;

import java.time.LocalDateTime;

public class ResponseShortUrlDto {
    public String originalUrl;
    public String shortCode;
    public String shortUrl;
    public LocalDateTime createdAt;

    public ResponseShortUrlDto(String originalUrl, String shortCode, String shortUrl, LocalDateTime createdAt) {
        this.originalUrl = originalUrl;
        this.shortCode = shortCode;
        this.shortUrl = shortUrl;
        this.createdAt = createdAt;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }


    public ResponseShortUrlDto() {
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }
}
