package com.imooc.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.imooc.model.SysPermission;
import com.imooc.model.SysRolePermission;
import com.imooc.model.TreeItem;
import com.imooc.service.SysPermissionService;
import com.imooc.service.SysRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/permission")
public class PermissionController {
    @Autowired
    SysPermissionService sysPermissionService;
    @Autowired
    SysRolePermissionService sysRolePermissionService;

    @RequestMapping(value = "/edit/{roleId}",method = RequestMethod.GET)
    public String list(Model model, @PathVariable Integer roleId){
        model.addAttribute("roleId",roleId);
        return "permission/tree";
    }

    @RequestMapping(value = "/getTreeByRoleId",method = RequestMethod.POST)
    @ResponseBody
    public TreeItem getTreeByRoleId(Integer parentId, Integer level, Integer roleId) {
        TreeItem treeItem = new TreeItem();

        List<JSONObject> list = new ArrayList<>();
        SysPermission sysPermission = new SysPermission();

        if(parentId == null || parentId.equals(-1)){
            sysPermission.setParentId(-1);
            sysPermission.setLevel(1);
        }else{
            sysPermission.setParentId(parentId);
            sysPermission.setLevel(level);
        }

        // 1.查询所有权限
        List<SysPermission> list_permission = sysPermissionService.getList(sysPermission);

        // 2.查询角色拥有的权限
        List<SysRolePermission> list_permissionByRoleId = sysRolePermissionService.getByRoleId(roleId);
        List<Integer> list_permissionByRoleId_Integer = new ArrayList<>();
        for (SysRolePermission item:list_permissionByRoleId) {
            list_permissionByRoleId_Integer.add(item.getPermissionId());
        }

        // 3.再所有权限列表中对于角色拥有的权限增加checked = true
        List<SysPermission> list_result = new ArrayList<>();
        for (SysPermission item:list_permission) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", item.getId());
            jsonObject.put("name", item.getPermissionName());
            // jsonObject.put("level",item.getLevel());
            jsonObject.put("pId", item.getParentId());
            if(list_permissionByRoleId_Integer.contains(item.getId())){
                jsonObject.put("checked", true);
            }

            // 4.判断treeNode是否有子元素
            SysPermission sysPermissionChild = new SysPermission();
            sysPermissionChild.setParentId(item.getId());
            List<SysPermission> listTemp = sysPermissionService.getList(sysPermissionChild);
            if(listTemp != null && listTemp.size() >0){
                jsonObject.put("isParent", true);
            }
            else{
                jsonObject.put("isParent", false);
            }
            list.add(jsonObject);
        }
        treeItem.setSuccess(true);
        treeItem.setMsg(list.toString());
        return treeItem;
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public void save(Integer roleId,String permissionIds){
        sysRolePermissionService.deleteByRoleId(roleId);
        List<Integer> list_permissionIds = JSON.parseArray(permissionIds,Integer.class);
        for (Integer item:list_permissionIds) {
            SysRolePermission sysRolePermission = new SysRolePermission();
            sysRolePermission.setRoleId(roleId);
            sysRolePermission.setPermissionId(item);
            sysRolePermission.setCreated(new Date());
            sysRolePermissionService.add(sysRolePermission);
        }
    }
}
