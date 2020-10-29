package com.example.demo.po;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Data
public class RequestVo {
    private boolean permission;
    private Map<String, Map<String, Object>> contactsBookSPhonesMap;
    private ArrayList<Map<String, Object>> tgUserList;
    private List<Map<String, Object>> publicGroupidList;
    private List<String> privGroupMsgs;


}
