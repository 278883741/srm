package com.imooc.controller;

import com.alibaba.fastjson.JSONObject;
import com.imooc.model.User;
import com.imooc.service.UserService;
import com.imooc.utils.IMoocJSONResult;
import com.imooc.utils.MD5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "用户登录注册接口",tags = {"注册和登录的controller"})
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ApiOperation(value = "用户注册",notes = "用户注册的接口")
    public JSONObject register (@RequestBody User user) throws Exception{
        JSONObject jsonObject = new JSONObject();
        if(StringUtils.isEmpty(user.getUserName()) || StringUtils.isEmpty(user.getPassword())){
            jsonObject.put("status",-1);
            jsonObject.put("msg","用户名或密码不能为空");
            return jsonObject;
        }

        if(userService.queryUserNameExist(user.getUserName())){
            jsonObject.put("status",-1);
            jsonObject.put("msg","用户名已经存在");
            return jsonObject;
        }

        user.setPassword(MD5Utils.getMD5Str(user.getPassword()));
        user.setNickName(user.getUserName());
        if(userService.add(user)){
            jsonObject.put("status",1);
            jsonObject.put("msg","注册成功");
            jsonObject.put("data",user);
        }else{
            jsonObject.put("status",-1);
            jsonObject.put("msg","注册失败");
        }
        return jsonObject;
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ApiOperation(value = "用户登录",notes = "用户登录的接口")
    public JSONObject login(@RequestBody User user) throws Exception{
        JSONObject jsonObject = new JSONObject();
        if(StringUtils.isEmpty(user.getUserName()) || StringUtils.isEmpty(user.getPassword())){
            jsonObject.put("status",-1);
            jsonObject.put("msg","用户名或密码不能为空");
            return jsonObject;
        }
        user.setPassword(MD5Utils.getMD5Str(user.getPassword()));
        user = userService.get(user);
        if(user != null){
            jsonObject.put("status",1);
            jsonObject.put("msg","登录成功");
            jsonObject.put("data",user);
        }else{
            jsonObject.put("status",-1);
            jsonObject.put("msg","登录失败");
        }
        return jsonObject;
    }
}
