package com.imooc.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.imooc.model.*;
import com.imooc.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/role")
public class RoleController {
    @Autowired
    RoleService roleService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(Model model){
        return "role/list";
    }

    /**
     * 分页获取角色数据
     */
    @RequestMapping(value = "/list/query",method = RequestMethod.GET)
    @ResponseBody
    public BasePageModel get( PageParam pageParam,BasePageModel basePageModel){
        String[] str = new String[]{"id", "role_name", "create_time","update_time"};
        Page<?> page = PageHelper.startPage(pageParam.getiDisplayStart() / pageParam.getiDisplayLength() + 1, pageParam.getiDisplayLength(),str[pageParam.getiSortCol_0()] + " " + pageParam.getsSortDir_0());

        SysRole sysRole = new SysRole();
        List<SysRole> list = roleService.getList(sysRole);

        basePageModel.setAaData(page);
        basePageModel.setiTotalDisplayRecords((int) page.getTotal());
        basePageModel.setiTotalRecords((int) page.getTotal());

        PageInfo<SysRole> pageInfo = new PageInfo<>(list);
        basePageModel.setPageTotal(pageInfo.getPages());
        basePageModel.setRecords((int)pageInfo.getTotal());
        return basePageModel;
    }
}
