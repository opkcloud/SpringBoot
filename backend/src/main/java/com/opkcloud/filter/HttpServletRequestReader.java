package com.opkcloud.filter;

import lombok.extern.slf4j.Slf4j;
import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;

@Slf4j
public class HttpServletRequestReader extends HttpServletRequestWrapper {

    private final byte[] body;
    private final Map<String, String[]> parameterMap;

    public HttpServletRequestReader(HttpServletRequest request, byte[] body, Map<String, String[]> parameterMap) {
        super(request);
        this.body = body;
        this.parameterMap = parameterMap;
    }

    public byte[] getBody() {
        return body;
    }

    @Override
    public String getParameter(String name) {
        return this.parameterMap.get(name) == null ? null : this.parameterMap.get(name)[0];
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return this.parameterMap;
    }

    @Override
    public Enumeration<String> getParameterNames() {
        return Collections.enumeration(this.parameterMap.keySet());
    }

    @Override
    public String[] getParameterValues(String name) {
        return this.parameterMap.get(name);
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream bais = new ByteArrayInputStream(body);
        ServletInputStream sis = new ServletInputStream() {
            private boolean finished = false;

            @Override
            public int read() throws IOException {
                int data = bais.read();
                if (data == -1) {
                    this.finished = true;
                }
                return data;
            }

            @Override
            public int available() throws IOException {
                return bais.available();
            }

            @Override
            public void close() throws IOException {
                super.close();
                bais.close();
            }

            @Override
            public boolean isFinished() {
                return this.finished;
            }

            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setReadListener(ReadListener readListener) {
                throw new UnsupportedOperationException();
            }
        };
        return super.getInputStream();
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }
}
