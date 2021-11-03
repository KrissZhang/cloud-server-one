package com.self.cloudserver.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * RequestWrapper
 * @author zp
 */
public class RequestWrapper extends HttpServletRequestWrapper {

    private static final Logger logger = LoggerFactory.getLogger(RequestWrapper.class);

    private String body = "";

    public RequestWrapper(HttpServletRequest request) {
        super(request);

        StringBuilder builder = new StringBuilder();
        try(InputStream inputStream = request.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            char[] bodyCharBuffer = new char[1024];
            int len = 0;
            while((len = reader.read(bodyCharBuffer)) != -1){
                builder.append(new String(bodyCharBuffer, 0, len));
            }
        } catch (IOException e) {
            String errMsg = "wrapper get request body failed: " + e.getMessage();
            logger.error(errMsg);
            body = "";
        }

        this.body = builder.toString();
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body.getBytes());
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {
                //模板代码，无实现
            }

            @Override
            public int read() throws IOException {
                return byteArrayInputStream.read();
            }
        };
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }

    public String getBody() {
        return this.body;
    }

}
