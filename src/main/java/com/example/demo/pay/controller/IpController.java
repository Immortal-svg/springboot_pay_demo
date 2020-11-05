package com.example.demo.pay.controller;

import com.example.demo.common.utils.IpUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class IpController {

    @RequestMapping(value = "/ip")
    public Object t(HttpServletRequest request) {
        return IpUtil.getIp(request);
    }

}
