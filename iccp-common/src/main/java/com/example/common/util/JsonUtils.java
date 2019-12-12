package com.example.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: yaos
 * @Date: 2019-12-12 21:44
 * @Version：V1.0
 **/
public class JsonUtils {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public JsonUtils() {
    }

    public static String objectToJson(Object data) {
        try {
            String string = MAPPER.writeValueAsString(data);
            return string;
        } catch (JsonProcessingException var2) {
            var2.printStackTrace();
            return null;
        }
    }

    public static <T> T jsonToPojo(String jsonData, Class<T> beanType) {
        try {
            T t = MAPPER.readValue(jsonData, beanType);
            return t;
        } catch (Exception var3) {
            var3.printStackTrace();
            return null;
        }
    }

    public static <T> List<T> jsonToList(String jsonData, Class<T> beanType) {
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, new Class[]{beanType});

        try {
            List<T> list = (List)MAPPER.readValue(jsonData, javaType);
            return list;
        } catch (Exception var4) {
            var4.printStackTrace();
            return null;
        }
    }

    public static <K, V> Map<K, V> jsonToMap(String jsonData) {
        try {
            Map<K, V> map = (Map)MAPPER.readValue(jsonData, HashMap.class);
            return map;
        } catch (Exception var2) {
            var2.printStackTrace();
            return null;
        }
    }
}