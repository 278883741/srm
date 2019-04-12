package com.imooc.controller;

import com.alibaba.fastjson.JSONObject;
import com.imooc.contants.Contants;
import com.imooc.model.Bgm;
import com.imooc.model.User;
import com.imooc.service.BgmService;
import com.imooc.service.UserService;
import com.imooc.utils.MD5Utils;
import com.imooc.utils.RedisHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

//@Controller
@RestController
@Api(value = "背景音乐接口", tags = {"背景音乐注册接口的controller"})
public class BgmController {
    @Autowired
    BgmService bgmService;

    @RequestMapping(value = "/queryList", method = RequestMethod.GET)
    @ApiOperation(value = "获取背景音乐列表", notes = "获取背景音乐列表的接口")
    public Object register() throws Exception {
        JSONObject jsonObject = new JSONObject();
        Bgm bgm = new Bgm();
        List<Bgm> list = bgmService.getList(bgm);
        jsonObject.put("status", 1);
        jsonObject.put("msg", "查询成功");
        jsonObject.put("data", list);
        return jsonObject;
    }
}
