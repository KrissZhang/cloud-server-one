package com.self.cloudserver.context;

import java.util.HashMap;
import java.util.Map;

public class SessionContext {

    private SessionContext(){
        super();
    }

    public static final ThreadLocal<Map<String, Object>> THREAD_LOCAL = new ThreadLocal<>();

    public static void set(String key, Object value) {
        Map<String, Object> map = THREAD_LOCAL.get();
        if (map == null) {
            map = new HashMap<>();
            THREAD_LOCAL.set(map);
        }

        map.put(key, value);
    }

    public static Object get(String key) {
        Map<String, Object> map = THREAD_LOCAL.get();
        if (map == null) {
            map = new HashMap<>();
            THREAD_LOCAL.set(map);
        }

        return map.get(key);
    }

    private static String returnStringValue(Object value) {
        return value == null ? null : value.toString();
    }

    public static void remove() {
        THREAD_LOCAL.remove();
    }

}
