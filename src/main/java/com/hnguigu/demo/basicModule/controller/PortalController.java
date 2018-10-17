package com.hnguigu.demo.basicModule.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: BOS
 * @description: ${description}
 * @author: 徐子楼
 * @create: 2018-09-28 19:39
 **/
@Controller
public class PortalController {

    @RequestMapping("/portal/bug")
    public String portalBug(){
        return "portal/bug";
    }
    @RequestMapping("/portal/daiban")
    public String portalDaiban(){
        return "portal/daiban";
    }
    @RequestMapping("/portal/gonggao")
    public String portalGonggao(){
        return "portal/gonggao";
    }
    @RequestMapping("/portal/yujing")
    public String portalYunjing(){
        return "portal/yujing";
    }
}
