package com.opkcloud.filter;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opkcloud.CommonRes;
import com.opkcloud.util.RSAUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
@WebFilter(urlPatterns = "/*", filterName = "HttpServletRequestReaderFilter")
public class HttpRequestWrapperFilter implements Filter {

    @Value("${security.data-encrypt.public-key}")
    private String publicKey;

    @Value("${security.data-encrypt.private-key}")
    private String privateKey;

    @Value("${security.data-encrypt.enable}")
    private boolean encryptionEnable;

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        log.info("servletPath", req.getServletPath());
        log.info("method", req.getMethod());

        /*if (req.getServletPath().contains(ApiPath.UPLOAD) || req.getMethod().equals(HttpMethod.OPTIONS.name())) {
            chain.doFilter(request, response);
            return;
        }*/

        ServletRequest requestWrapper = null;
        if (encryptionEnable) {
            try {
                requestWrapper = new HttpServletRequestReader(req, decryptBody(req), decryptParams(req));
            } catch (Exception ex) {
                log.error("Fail to wrap request", ex);
            }
            HttpServletResponseReader responseWrapper = new HttpServletResponseReader((HttpServletResponse) response);
            chain.doFilter(requestWrapper, responseWrapper);

            if (responseWrapper != null) {
                try {
                    ObjectMapper om = new ObjectMapper();
                    responseWrapper.setCharacterEncoding(StandardCharsets.UTF_8.toString());
                    CommonRes cr = om.readValue(responseWrapper.getCaptureAsString(), CommonRes.class);
                    String encrypted = encryptStr(JSONObject.toJSONString(cr.getData()));
                    cr.setData(encrypted);
                    response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
                    response.getOutputStream().write(JSONObject.toJSONString(cr).getBytes("UTF-8"));
                } catch (Exception ex) {
                    log.error("返回数据加密出错", ex);
                }
            }
        } else {
            try {
                requestWrapper = new HttpServletRequestReader(req, getByInputStream(req), req.getParameterMap());
            } catch (IOException ex) {
                log.error("Fail to wrap request", ex);
            }
            if (requestWrapper == null) {
                requestWrapper = request;
            }
            chain.doFilter(requestWrapper, response);
        }

    }

    private Map<String, String[]> decryptParams(HttpServletRequest request) throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {
        if (!encryptionEnable) {
            return request.getParameterMap();
        }

        Map<String, String[]> reqMap = request.getParameterMap();
        Map<String, Object> tmap = new HashMap<>();
        final Map<String, String[]> rmap = new HashMap<>();
        if (reqMap != null && reqMap.get("params") != null) {
            String encrypted = reqMap.get("params")[0];
            String jsonStr = dectyprStr(encrypted);
            ObjectMapper om = new ObjectMapper();
            tmap = om.readValue(jsonStr, Map.class);

            tmap.forEach((k, v) -> {
                if (v instanceof ArrayList) {
                    List<String> t = (List<String>) v;
                    rmap.put(k, t.toArray(new String[0]));
                } else {
                    rmap.put(k, new String[]{ String.valueOf(v) });
                }
            });
        }
        return reqMap;
    }

    private byte[] decryptBody(HttpServletRequest request) throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {
        if (request.getMethod().equals(HttpMethod.POST.name())) {
            InputStream in = request.getInputStream();
            String bodyStr = IOUtils.toString(in, StandardCharsets.UTF_8.toString());
            if (!StringUtils.isEmpty(bodyStr)) {
                return getByInputStream(IOUtils.toInputStream(dectyprStr(bodyStr), StandardCharsets.UTF_8.toString()));
            }
        }
        return getByInputStream(request);
    }

    private byte[] getByInputStream(HttpServletRequest request) throws IOException {
        return getByInputStream(request.getInputStream());
    }

    private byte[] getByInputStream(InputStream input) {
        byte[] bts = new byte[0];
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = input.read(buffer)) > 0) {
                baos.write(buffer, 0, length);
            }
            bts = baos.toByteArray();
            input.close();
            baos.close();
        } catch (IOException ex) {
            log.error("Fail to inputStream", ex);
        }
        return bts;
    }

    private String dectyprStr(String encrypted) throws InvalidKeySpecException, NoSuchAlgorithmException, UnsupportedEncodingException {
        return URLDecoder.decode(RSAUtils.privateDecrypt(encrypted, RSAUtils.getPrivateKey(privateKey)), StandardCharsets.UTF_8.toString());
    }

    private String encryptStr(String str) throws InvalidKeySpecException, NoSuchAlgorithmException, UnsupportedEncodingException {
        return RSAUtils.publicEncrypt(URLEncoder.encode(str, StandardCharsets.UTF_8.toString()), RSAUtils.getPublicKey(publicKey));
    }

    @Override
    public void destroy() {

    }

}
