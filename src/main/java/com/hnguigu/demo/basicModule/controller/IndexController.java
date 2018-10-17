package com.hnguigu.demo.basicModule.controller;

import com.hnguigu.demo.basicModule.domain.ZTree;
import com.hnguigu.demo.basicModule.service.ZTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @program: BOS
 * @description: ${description}
 * @author: 徐子楼
 * @create: 2018-09-28 19:07
 **/
@Controller
public class IndexController {

    @Autowired
    private ZTreeService zTreeService;

    @RequestMapping("/index")
    public String index() {
        return "common/index";
    }

    @RequestMapping("/home")
    public String home() {
        return "common/home";
    }

    @RequestMapping("/systemsManagement")
    @ResponseBody
    public List<ZTree> systemsManagement() {
        return this.zTreeService.systemsManagement();
    }

    @RequestMapping("/basicFunction")
    @ResponseBody
    public List<ZTree> basicFunction() {
        return this.zTreeService.basicFunction();
    }
}
