package com.web.autoshow.common;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@Scope("prototype")
public class Response {
    private final HashMap<String, Object> response;

    public Response() {
        response = new HashMap<>();
    }

    public HashMap<String, Object> getResponse() {
        return response;
    }

    public void push(String key, Object value) {
        response.put(key, value);
    }
}
