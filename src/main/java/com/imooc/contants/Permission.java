package com.imooc.contants;

import com.imooc.model.SysPermission;

import java.util.ArrayList;
import java.util.List;

public class Permission {
    public final static List<SysPermission> list = new ArrayList<>();
    public static List<SysPermission> get(){
//        合作伙伴管理生命周期流程
        if(list.size() == 0) {
            SysPermission sysPermission = new SysPermission();
            sysPermission.setPermissionName("一次性合作伙伴新建");
            sysPermission.setPermissionUrl("/partner/index");
            list.add(sysPermission);

            SysPermission sysPermission1 = new SysPermission();
            sysPermission1.setPermissionName("集团黑灰名单维护");
            sysPermission1.setPermissionUrl("/blackList/index");
            list.add(sysPermission1);

            SysPermission sysPermission2 = new SysPermission();
            sysPermission2.setPermissionName("正式资源池新建");
            sysPermission2.setPermissionUrl("/formalResource/index");
            list.add(sysPermission2);

            SysPermission sysPermission3 = new SysPermission();
            sysPermission3.setPermissionName("临时资源池新建");
            sysPermission3.setPermissionUrl("/tempResource/index");
            list.add(sysPermission3);

            SysPermission sysPermission_partnerAbility = new SysPermission();
            sysPermission_partnerAbility.setPermissionName("合作伙伴考察及沟通");
            sysPermission_partnerAbility.setPermissionUrl("/partnerAbility/index");
            list.add(sysPermission_partnerAbility);

            SysPermission sysPermission_keyEvent = new SysPermission();
            sysPermission_keyEvent.setPermissionName("关键事件管理");
            sysPermission_keyEvent.setPermissionUrl("/keyEvent/index");
            list.add(sysPermission_partnerAbility);

//
//            list1.add(sysPermission_sub2);
//
//            SysPermission sysPermission_sub3 = new SysPermission();
//            sysPermission_sub3.setPermissionName("正式资源池新建及维护流程");
//            sysPermission_sub3.setPermissionUrl("");
//            list1.add(sysPermission_sub3);
//
//            SysPermission sysPermission_sub4 = new SysPermission();
//            sysPermission_sub4.setPermissionName("临时资源池新建及维护流程");
//            sysPermission_sub4.setPermissionUrl("");
//            list1.add(sysPermission_sub4);
//
//            SysPermission sysPermission_sub5 = new SysPermission();
//            sysPermission_sub5.setPermissionName("合作伙伴能力建设与管理");
//            sysPermission_sub5.setPermissionUrl("");
//            list1.add(sysPermission_sub5);
//
//            SysPermission sysPermission_sub6 = new SysPermission();
//            sysPermission_sub6.setPermissionName("合作伙伴评估与管理");
//            sysPermission_sub6.setPermissionUrl("");
//            list1.add(sysPermission_sub6);
//
//            sysPermission.setChildList(list1);

        }
        return list;
    }
}
