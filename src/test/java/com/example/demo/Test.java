package com.example.demo;

import com.example.demo.common.utils.GSONUtil;
import com.example.demo.po.RequestVo;
import com.google.common.reflect.TypeToken;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.*;


public class Test {

    @org.junit.jupiter.api.Test
    void contextLoads() {
        Gson gson=new Gson();
        RequestVo requestVo=new RequestVo();
        Map<String, Map<String, Object>> contactsBookSPhonesMap=new HashMap<>();
        Map<String, Object> objectMap=new HashMap<>();
        objectMap.put("objectMap","123");
        contactsBookSPhonesMap.put("key_1",objectMap);
        requestVo.setContactsBookSPhonesMap(contactsBookSPhonesMap);
        ArrayList<Map<String, Object>> tgUserList=new ArrayList<>();
        Map<String, Object> tgUserListMap=new HashMap<>();
        tgUserListMap.put("tgUserListMap","123");
        tgUserList.add(tgUserListMap);
        requestVo.setTgUserList(tgUserList);
        List<Map<String, Object>> publicGroupidList=new ArrayList<>();
        Map<String, Object> publicGroupidMap=new HashMap<>();
        publicGroupidMap.put("publicGroupidMap","123");
        publicGroupidList.add(publicGroupidMap);
        requestVo.setPublicGroupidList(publicGroupidList);
        List<String> privGroupMsgs=new ArrayList<>();
        privGroupMsgs.add("1");
        requestVo.setPrivGroupMsgs(privGroupMsgs);
        System.out.println(gson.toJson(requestVo));
        String json="{\"permission\":\"\",\"contactsBookSPhonesMap\":{\"key_1\":{\"objectMap\":\"123\"}},\"tgUserList\":[{\"tgUserListMap\":\"123\"}],\"publicGroupidList\":[{\"publicGroupidMap\":\"123\"}],\"privGroupMsgs\":[\"1\"]}";
        System.out.println(json);
        RequestVo requestVo1=gson.fromJson(json,RequestVo.class);
        requestVo1.getPrivGroupMsgs().stream().forEach(s->{
            System.out.println(s);
        });
    }
    @org.junit.jupiter.api.Test
    public void st(){
       // Gson gson=GsonUtils.getGson();
 /*       GsonBuilder gsonBuilder = new GsonBuilder();
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
        Gson gson = gsonBuilder.create();*/

        Gson gson=new Gson();
        RequestVo requestVo=new RequestVo();
        /*requestVo.set*/
        Map<String, Map<String, Object>> contactsBookSPhonesMap=new HashMap<>();
        Map<String, Object> objectMap=new HashMap<>();
        objectMap.put("objectMap",123);
        contactsBookSPhonesMap.put("key_1",objectMap);
        requestVo.setContactsBookSPhonesMap(contactsBookSPhonesMap);
        ArrayList<Map<String, Object>> tgUserList=new ArrayList<>();
        Map<String, Object> tgUserListMap=new HashMap<>();
        tgUserListMap.put("tgUserListMap","123");
        long tin=1563315645465464546L;
        tgUserListMap.put("uploadTime",tin);
        Integer type=1;
        Double d=new Double(1);
        tgUserListMap.put("d",d);
        tgUserListMap.put("type",type);
        tgUserList.add(tgUserListMap);
        requestVo.setTgUserList(tgUserList);
        List<Map<String, Object>> publicGroupidList=new ArrayList<>();
        Map<String, Object> publicGroupidMap=new HashMap<>();
        publicGroupidMap.put("publicGroupidMap","123");
        publicGroupidList.add(publicGroupidMap);
        requestVo.setPublicGroupidList(publicGroupidList);
        List<String> privGroupMsgs=new ArrayList<>();
        privGroupMsgs.add("1");
        requestVo.setPrivGroupMsgs(privGroupMsgs);
        System.out.println(GSONUtil.toJson(requestVo));
        /*String json="{\"permission\":\"\",\"contactsBookSPhonesMap\":{\"key_1\":{\"objectMap\":\"123\"}},\"tgUserList\":[{\"tgUserListMap\":\"123\"}],\"publicGroupidList\":[{\"publicGroupidMap\":\"123\"}],\"privGroupMsgs\":[\"1\"]}";
        System.out.println(json);*/
        RequestVo requestVo1=GSONUtil.fromJson(GSONUtil.toJson(requestVo),RequestVo.class);
        requestVo1.getPrivGroupMsgs().stream().forEach(s->{
            System.out.println(s);
        });
        requestVo1.getTgUserList().stream().forEach((v)->{
            v.put("ip","127.0.0.1");
            System.out.println(GSONUtil.toJson(v));
        });
        System.out.println(gson.toJson(requestVo1));
        System.out.println(GSONUtil.toJson(requestVo1));

    }
    @org.junit.jupiter.api.Test
    public void s(){
        System.out.println(Math.round(-1.5));
        String a="i";
        String b=new String("i");
        System.out.println(a.equals(b));
        System.out.println(a==b);
    }

    private static   String get(){
        try{
            int i=1/0;
            return "1";
        }catch (Exception e){
            return  "2";
        }finally {
            System.out.println("finally");
            //return  "3";
        }
    }

    public static void main(String[] args) {
        System.out.println(get());
    }
}
