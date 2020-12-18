package io.github.kumaraditya303.blog.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ApiResponse {
    private Map<Object, Object> data = new HashMap<>();

    public ApiResponse() {
        this.data.put("error", null);
    }

    public void setData(Object key, Object value) {
        this.data.put(key, value);
    }

    public void setError(Object value) {
        this.data.put("error", value);
    }

    public void setError(Object key, Object value) {
        Map<Object, Object> temp = new HashMap<>();
        temp.put(key, value);
        this.data.put("error", temp);
    }

    public Map<Object, Object> getData() {
        this.data.put("timestamp", new Date());
        return this.data;
    }
}
