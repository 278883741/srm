package com.srm.controller;

import com.srm.model.SysPermission;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(Model model){
        List<SysPermission> permissionList = new ArrayList<>();
        return "index/index";
    }
}
