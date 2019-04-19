package com.imooc.controller;

import com.alibaba.fastjson.JSONObject;
import com.imooc.contants.Contants;
import com.imooc.model.User;
import com.imooc.service.UserService;
import com.imooc.utils.MD5Utils;
import com.imooc.utils.RedisHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.UUID;

@RestController
@Api(value = "用户接口", tags = {"用户接口"})
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    RedisHelper redisHelper;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ApiOperation(value = "用户注册", notes = "用户注册")
    public Object register(@RequestBody User user) throws Exception {
        JSONObject jsonObject = new JSONObject();
        if (StringUtils.isEmpty(user.getUserName()) || StringUtils.isEmpty(user.getPassword())) {
            jsonObject.put("status", -1);
            jsonObject.put("msg", "用户名或密码不能为空");
            return jsonObject;
        }

        if (userService.queryUserNameExist(user.getUserName())) {
            jsonObject.put("status", -1);
            jsonObject.put("msg", "用户名已经存在");
            return jsonObject;
        }

        user.setPassword(MD5Utils.getMD5Str(user.getPassword()));
        user.setNickName(user.getUserName());
        String uniqueToken = UUID.randomUUID().toString();

        if (userService.add(user)) {
            redisHelper.set(Contants.USER_REDIS_SESSION + ":" + user.getId(), uniqueToken, 1000 * 60 * 30);
            jsonObject.put("status", 1);
            jsonObject.put("msg", "注册成功");
            jsonObject.put("data", user);
        } else {
            jsonObject.put("status", -1);
            jsonObject.put("msg", "注册失败");
        }
        return jsonObject;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "用户登录", notes = "用户登录")
    public Object login(@RequestBody User user) throws Exception {
        JSONObject jsonObject = new JSONObject();
        if (StringUtils.isEmpty(user.getUserName()) || StringUtils.isEmpty(user.getPassword())) {
            jsonObject.put("status", -1);
            jsonObject.put("msg", "用户名或密码不能为空");
            return jsonObject;
        }
        user.setPassword(MD5Utils.getMD5Str(user.getPassword()));
        user = userService.get(user);
        if (user != null) {
            String uniqueToken = UUID.randomUUID().toString();
//            redisHelper.set(Contants.USER_REDIS_SESSION + ":" + user.getId(), uniqueToken, 1 * 60 * 30);
            jsonObject.put("status", 1);
            jsonObject.put("msg", "登录成功");
            jsonObject.put("data", user);
        } else {
            jsonObject.put("status", -1);
            jsonObject.put("msg", "登录失败");
        }
        return jsonObject;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "int", paramType = "query")
    @ApiOperation(value = "用户注销", notes = "用户注销")
    public Object logout(String userId) throws Exception {
        JSONObject jsonObject = new JSONObject();
        String key = Contants.USER_REDIS_SESSION + ":" + userId;
//        redisHelper.del(key);
        jsonObject.put("status", 1);
        jsonObject.put("msg", "注销成功");
        return jsonObject;
    }

    @RequestMapping(value = "/uploadFace", method = RequestMethod.POST)
    @ApiOperation(value = "用户上传头像", notes = "用户上传头像")
    @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "int", paramType = "query")
    public Object uploadFace(Integer userId, @RequestParam("files") MultipartFile[] files) throws Exception {
        JSONObject jsonObject = new JSONObject();
        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;
        try {
            if (files != null && files.length > 0) {
                MultipartFile file = files[0];
                String workSpace = "/Users/zhaojianfei/Desktop/project/imooc/";
                String filePath = workSpace + "/" + userId + "/face/" + file.getOriginalFilename();

                fileOutputStream = new FileOutputStream(filePath);
                inputStream = file.getInputStream();
                IOUtils.copy(inputStream, fileOutputStream);

                User user = new User();
                user.setId(userId);
                user = userService.get(user);
                if (user != null) {
                    String imgPath = "/" + userId + "/face/" + file.getOriginalFilename();
                    user.setFaceImage(imgPath);
                    if (userService.update(user).equals(1)) {
                        jsonObject.put("status", 1);
                        jsonObject.put("imgPath", imgPath);
                        jsonObject.put("msg", "上传成功");
                    } else {
                        jsonObject.put("status", 0);
                        jsonObject.put("msg", "上传失败");
                    }
                } else {
                    jsonObject.put("status", 0);
                    jsonObject.put("msg", "上传失败");
                }


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
        User user = new User();
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

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String Test() {
        return "keyEvent/index";
    }
}
