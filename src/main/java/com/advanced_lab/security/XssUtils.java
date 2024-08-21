package com.advanced_lab.security;



import org.springframework.web.util.HtmlUtils;

public class XssUtils {
    public static String encodeHtml(String input) {
        return HtmlUtils.htmlEscape(input);
    }
}
