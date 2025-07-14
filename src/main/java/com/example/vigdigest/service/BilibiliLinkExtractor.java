package com.example.vigdigest.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Extracts canonical Bilibili video URLs from various link formats.
 */
public class BilibiliLinkExtractor {

    private static final Pattern BV_PATTERN = Pattern.compile("(BV[\w]+)");
    private static final Pattern AV_PATTERN = Pattern.compile("av(\d+)");

    /**
     * Extracts the canonical playback URL from a Bilibili page or share link.
     *
     * @param url input url
     * @return canonical url such as https://www.bilibili.com/video/BVxxxx
     */
    public String extract(String url) {
        if (url == null || url.isBlank()) {
            throw new IllegalArgumentException("url required");
        }
        Matcher bv = BV_PATTERN.matcher(url);
        if (bv.find()) {
            return "https://www.bilibili.com/video/" + bv.group(1);
        }
        Matcher av = AV_PATTERN.matcher(url);
        if (av.find()) {
            return "https://www.bilibili.com/video/av" + av.group(1);
        }
        throw new IllegalArgumentException("unable to parse bilibili id");
    }
}
