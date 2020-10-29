package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/ip")
public class IpController {

    @RequestMapping(value = "/getIp")
    public Object t(HttpServletRequest request) {
        return IpUtil.getIp(request);
    }

}
