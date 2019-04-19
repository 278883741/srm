package com.imooc.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.imooc.model.BasePageModel;
import com.imooc.model.PageParam;
import com.imooc.model.User;
import com.imooc.model.Video;
import com.imooc.service.UserService;
import com.imooc.service.VideoService;
import io.swagger.annotations.*;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

@RestController
@Api(value = "视频接口", tags = {"视频接口"})
@RequestMapping(value = "/video")
public class VideoController {
    @Autowired
    VideoService videoService;
    @Autowired
    UserService userService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST, headers = "content-type=multipart/form-data")
    @ApiOperation(value = "用户上传视频", notes = "用户上传视频")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "int", paramType = "form"),
            @ApiImplicitParam(name = "seconds", value = "视频秒数", required = true, dataType = "float", paramType = "form"),
            @ApiImplicitParam(name = "width", value = "视频宽度", required = true, dataType = "int", paramType = "form"),
            @ApiImplicitParam(name = "height", value = "视频高度", required = true, dataType = "int", paramType = "form")
    })
    public Object upload(Integer userId, Float seconds, Integer width, Integer height
            , @ApiParam(value = "短视频", required = true) @RequestParam("file") MultipartFile file) throws Exception {
        JSONObject jsonObject = new JSONObject();

        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;
        try {
            String workSpace = "/Users/zhaojianfei/Desktop/project/imooc/";
            String filePath = workSpace + "/" + userId + "/video/" + file.getOriginalFilename();

            fileOutputStream = new FileOutputStream(filePath);
            inputStream = file.getInputStream();
            IOUtils.copy(inputStream, fileOutputStream);

            Video video = new Video();
            video.setUserId(userId);
            video.setVideoPath("/" + userId + "/video/" + file.getOriginalFilename());
            video.setVideoSeconds(seconds);
            video.setVideoWidth(width);
            video.setVideoHeight(height);
            video.setStatus(1);
            videoService.add(video);
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

    @RequestMapping(value = "/queryList", method = RequestMethod.GET)
    @ApiOperation(value = "获取用户上传的视频", notes = "获取用户上传的视频")
    @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "int", paramType = "query")
    public Object queryList(Integer userId) {
        JSONObject jsonObject = new JSONObject();

        Video video = new Video();
        video.setUserId(userId);

        jsonObject.put("status",1);
        jsonObject.put("msg","操作成功");
        jsonObject.put("data",videoService.getList(video));
        return jsonObject;
    }

    @RequestMapping(value = "/queryListByaPage", method = RequestMethod.GET)
    @ApiOperation(value = "获取用户上传的视频", notes = "获取用户上传的视频")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "pageNum", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "pageSize", required = true, dataType = "int", paramType = "query")
    })

    public Object queryListByaPage(Integer userId, Integer pageNum, Integer pageSize) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status",1);
        jsonObject.put("msg","操作成功");



        JSONObject jsonObject1 = new JSONObject();
        Video video = new Video();
        video.setUserId(userId);

        Page<?> page = PageHelper.startPage(pageNum, pageSize);
        String[] str = new String[]{"id", "student_no", "visa_way", "visa_type", "send_date", "visa_comment", "create_time", "delete_time", "delete_status", "operator_no", "apply_audit_status"};

        List<Video> list = videoService.getList(video);

        PageInfo<Video> pageInfo = new PageInfo<>(list);
        BasePageModel basePageModel = new BasePageModel();
        basePageModel.setPage(pageNum);
        basePageModel.setPageTotal(pageInfo.getPages());
        basePageModel.setAaData(page);
        basePageModel.setRecords((int)pageInfo.getTotal());

        jsonObject.put("data",list);

        return jsonObject;
    }
}
