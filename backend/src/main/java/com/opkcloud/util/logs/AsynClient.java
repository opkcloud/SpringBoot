package com.opkcloud.util.logs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class AsynClient {

    private static final Logger logger = LoggerFactory.getLogger(AsynClient.class);

    private static void main(String[] args) throws Exception {}

    @SuppressWarnings("all")
    public static String getURLEncoder(String dest) {
        try {
            return URLEncoder.encode(dest, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("查询条件转码异常：", e);
        }
        return null;
    }

    @SuppressWarnings("all")
    public static String getURLDecoder(String dest) {
        try {
            return URLDecoder.decode(dest, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("查询条件转码异常：", e);
        }
        return null;
    }

}
