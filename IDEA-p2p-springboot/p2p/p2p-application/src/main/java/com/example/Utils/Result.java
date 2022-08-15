package com.example.Utils;

import java.util.HashMap;

public class Result extends HashMap<String, Object> {
    public static HashMap<String, Object> success(String msg) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("code",1);
        hashMap.put("message",msg);
        hashMap.put("success",true);
        return hashMap;
    }

    public static HashMap<String, Object> fail(String msg) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("code", 0);
        hashMap.put("message", msg);
        hashMap.put("success", false);
        return hashMap;
    }

    public static HashMap<String, Object> wrongMessageCode(String msg) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("code", -1);
        hashMap.put("message", msg);
        hashMap.put("success", false);
        return hashMap;
    }
}
