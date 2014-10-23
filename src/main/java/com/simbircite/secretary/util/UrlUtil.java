package com.simbircite.secretary.util;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

/** Служебный класс, кодирующий URL для перенаправления */
public class UrlUtil {

    public static String encodeUrlPathSegment(
            String pathSegment, HttpServletRequest httpServletRequest) {
        String enc = httpServletRequest.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        String result;
        try {
            result = UriUtils.encodePathSegment(pathSegment, enc);
        } catch (UnsupportedEncodingException ignore) {
            result = pathSegment;
        }
        return result;
    }
    
}
