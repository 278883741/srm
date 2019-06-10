package com.imooc.controller;

import com.alibaba.fastjson.JSONObject;
import com.imooc.contants.Contants;
import com.imooc.model.SysUser;
import com.imooc.service.UserService;
import com.imooc.utils.MD5Utils;
import com.imooc.utils.RedisHelper;
import io.swagger.annotations.*;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

@RestController
@Api(value = "用户接口", tags = {"用户接口"})
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    RedisHelper redisHelper;

    @Value("${user.workSpace}")
    private String workSpace;

    @RequestMapping(value = "/add", method = RequestMethod.POST, headers = "content-type=multipart/form-data")
    @ApiOperation(value = "添加用户", notes = "添加用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName",value = "用户名",required = true,dataType = "string" ,paramType = "form"),
            @ApiImplicitParam(name = "password",value = "密码",required = true,dataType = "string" ,paramType = "form"),
    })
    public Object add(String userName, String password, @ApiParam(value = "用户头像",required = false) @RequestParam(name = "file") MultipartFile file) throws Exception{
        JSONObject jsonObject = new JSONObject();
        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;
        try {
            SysUser sysUser = new SysUser();
            sysUser.setUsername(userName);
            sysUser.setPassword(MD5Utils.getMD5Str(password));
            sysUser.setDeleteStatus(false);
            sysUser.setOperatorNo("yushu");
            sysUser.setCreateTime(new Date());
            sysUser.setUpdateTime(new Date());
            if (userService.add(sysUser)) {
                Integer userId = sysUser.getId();
                String filePath = workSpace + "/" + userId + "/face/" + file.getOriginalFilename();

                fileOutputStream = new FileOutputStream(filePath);
                inputStream = file.getInputStream();
                IOUtils.copy(inputStream, fileOutputStream);
                sysUser.setHeadimgurl("/" + userId + "/face/" + file.getOriginalFilename());
                userService.update(sysUser);

                jsonObject.put("data", null);
                jsonObject.put("status", 1);
                jsonObject.put("msg", "添加成功");
            } else {
                jsonObject.put("data", null);
                jsonObject.put("status", -1);
                jsonObject.put("msg", "添加失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("data", null);
            jsonObject.put("status", -1);
            jsonObject.put("msg", "添加失败");
        } finally {
            if (fileOutputStream != null) {
                fileOutputStream.flush();
                fileOutputStream.close();
            }
        }
        return jsonObject;
    }

    @RequestMapping(value = "/uploadHeadImg", method = RequestMethod.POST)
    @ApiOperation(value = "用户上传头像", notes = "用户上传头像")
    @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "int", paramType = "query")
    public Object uploadFace(Integer userId, @ApiParam(value = "用户头像",required = true) @RequestParam("file") MultipartFile file) throws Exception {
        JSONObject jsonObject = new JSONObject();
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            SysUser user = new SysUser();
            user.setId(userId);
            user = userService.get(user);
            String imgPath = "/" + userId + "/face/" + file.getOriginalFilename();
            user.setHeadimgurl(imgPath);
            user.setUpdateTime(new Date());
            if (userService.update(user).equals(1)) {
                String filePath = workSpace + "/" + userId + "/face/" + file.getOriginalFilename();

                fileOutputStream = new FileOutputStream(filePath);
                inputStream = file.getInputStream();
                IOUtils.copy(inputStream, fileOutputStream);
                jsonObject.put("status", 1);
                jsonObject.put("imgPath", imgPath);
                jsonObject.put("msg", "上传成功");
            } else {
                jsonObject.put("status", 0);
                jsonObject.put("msg", "上传失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("status", 0);
            jsonObject.put("msg", "上传失败");
        } finally {
            if (fileOutputStream != null) {
                fileOutputStream.flush();
                fileOutputStream.close();
            }
        }
        return jsonObject;
    }

    @RequestMapping(value = "/queryUserInfo", method = RequestMethod.GET)
    @ApiOperation(value = "查询用户信息", notes = "查询用户信息接口")
    @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "int", paramType = "query")
    public Object queryUserInfo(Integer userId) {
        JSONObject jsonObject = new JSONObject();
        SysUser user = new SysUser();
        user.setId(userId);
        user = userService.get(user);
        if (user != null) {
            jsonObject.put("data", user);
            jsonObject.put("status", 1);
            jsonObject.put("msg", "查询成功");
            return jsonObject;
        } else {
            jsonObject.put("data", null);
            jsonObject.put("status", -1);
            jsonObject.put("msg", "查询失败");
            return jsonObject;
        }
    }
}
