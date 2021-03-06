package com.imooc.controller;


import com.imooc.utils.ueditor.ActionEnter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UeditorController {
    private final static Logger logger = LoggerFactory.getLogger(UeditorController.class);

    /**
     * 富文本编辑器-上传图片
     * @param request
     * @param response
     */
    @RequestMapping(value="/UEditor/config")
    public void config(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        String rootPath = request.getSession().getServletContext().getRealPath("/");
        try {
            String exec = new ActionEnter(request, rootPath).exec();
            PrintWriter writer = response.getWriter();
            writer.write(exec);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public ResponseEntity<Object> delete(String key) {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put("success", "success");
        json.put("key", key);
        return new ResponseEntity<Object>(json, HttpStatus.OK);
    }
}

