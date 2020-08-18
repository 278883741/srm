package com.imooc.controller;

import com.imooc.utils.RedPacketUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/redPacket")
public class RedPacketController {
    @RequestMapping(value = "/divide",method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    public Object divide(){
        List<Integer> list = RedPacketUtil.divideRedPacket(100,5);
        return new ResponseEntity<List<Integer>>(list, HttpStatus.OK);
    }
}
