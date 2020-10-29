package com.example.demo.common.utils;

import com.google.common.reflect.TypeToken;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class GSONUtil {

    private static Gson gson;

    static {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setLongSerializationPolicy(LongSerializationPolicy.STRING);
        gsonBuilder.registerTypeAdapter(new TypeToken<Map<String, Object>>() {}.getType(),
                new JsonDeserializer<Map<String, Object>>() {
                    @Override
                    public Map<String, Object> deserialize(
                            JsonElement json, Type typeOfT,
                            JsonDeserializationContext context) throws JsonParseException {

                        Map<String, Object> treeMap = new HashMap<String, Object>();
                        JsonObject jsonObject = json.getAsJsonObject();
                        Set<Map.Entry<String, JsonElement>> entrySet = jsonObject.entrySet();
                        for (Map.Entry<String, JsonElement> entry : entrySet) {
                            treeMap.put(entry.getKey(), entry.getValue());
                        }
                        return treeMap;
                    }
                });
        gson = gsonBuilder.create();
    }

    public static String toJson(Object src) {
        return gson.toJson(src);
    }

    public static <T> T fromJson(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }

}
