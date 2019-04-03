package com.srm.controller;

import com.srm.contants.Permission;
import com.srm.model.SysPermission;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class formalResourceController {
    @RequestMapping(value = "formalResource/index",method = RequestMethod.GET)
    public String index(Model model, HttpServletRequest request){
        List<SysPermission> permissionList = Permission.get();
        HttpSession session = request.getSession();
        session.setAttribute("permissionList",permissionList);
        return "formalResource/index";
    }
}
