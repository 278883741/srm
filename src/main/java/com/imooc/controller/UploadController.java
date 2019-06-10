package com.imooc.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
public class UploadController {
    /**
     * 单上传文件
     *
     * @param file 文件流
     * @return
     */
    @RequestMapping("/upload")
    public ResponseEntity<JSONObject> upload(@RequestParam("fileInput") MultipartFile file) throws Exception {
        JSONObject jsonObject = new JSONObject();
        List<String> PIC_Format = Arrays.asList(".jpg", ".JPG", ".png", ".PNG", ".gif", ".jpeg", ".JPEG",".doc",".docx",".pdf",".eml");
        if (file.isEmpty()) {
            throw new RuntimeException("上传图片不能为空");
        }
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            // 获取文件扩展名
            String extend =  file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            if (!PIC_Format.contains(extend)) {
                jsonObject.put("success", false);
                jsonObject.put("obj","上传文件格式有误");
                jsonObject.put("code","1");
                return new ResponseEntity<JSONObject>(jsonObject, HttpStatus.OK);
            }
            String fileName = UUID.randomUUID().toString().replace("-","") + ".png";
            String imgPath = "C:/hearthStone/cardImg/" + fileName;
            inputStream = file.getInputStream();
            fileOutputStream = new FileOutputStream(imgPath);
            IOUtils.copy(inputStream, fileOutputStream);
            jsonObject.put("success", true);
            jsonObject.put("fileName", fileName);
            jsonObject.put("originalFilename", file.getOriginalFilename());
        } catch (Exception e) {
            jsonObject.put("success", false);
            e.printStackTrace();
        }finally {
            if (fileOutputStream != null) {
                fileOutputStream.flush();
                fileOutputStream.close();
            }
        }
        return new ResponseEntity<JSONObject>(jsonObject, HttpStatus.OK);
    }
}
