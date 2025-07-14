package com.example.vigdigest.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BilibiliLinkExtractorTest {

    private final BilibiliLinkExtractor extractor = new BilibiliLinkExtractor();

    @Test
    void extractFromBvLink() {
        String url = "https://www.bilibili.com/video/BV1ab411c7de?p=1";
        assertEquals("https://www.bilibili.com/video/BV1ab411c7de", extractor.extract(url));
    }

    @Test
    void extractFromShortLink() {
        String url = "https://b23.tv/BV1ab411c7de";
        assertEquals("https://www.bilibili.com/video/BV1ab411c7de", extractor.extract(url));
    }

    @Test
    void invalidLinkThrows() {
        assertThrows(IllegalArgumentException.class, () -> extractor.extract("https://example.com"));
    }
}
