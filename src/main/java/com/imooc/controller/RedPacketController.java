package com.imooc.controller;

import com.imooc.model.RedDetail;
import com.imooc.model.RedRecord;
import com.imooc.model.RedRobRecord;
import com.imooc.service.RedDetailService;
import com.imooc.service.RedRecordService;
import com.imooc.service.RedRobRecordService;
import com.imooc.utils.RedPacketUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/redPacket")
@EnableCaching
public class RedPacketController {
    private static final Logger logger = LoggerFactory.getLogger(RedisController.class);

    @Autowired
    private RedRecordService redRecordService;
    @Autowired
    private RedDetailService redDetailService;
    @Autowired
    private RedRobRecordService redRobRecordService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    // 结合redis springEL表达式-- 如果没有则插入缓存，没有则还是查询数据库
    @Cacheable(value = "cache",key = "#userId")
    // 有没有都插入缓存
    // @CachePut(value = "cache",key = "#userId")
    @RequestMapping(value = "/testCache", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    public Object testCache(@RequestParam Integer userId){
        RedRecord redRecord = new RedRecord();
        redRecord.setUserId(userId);
        redRecord = redRecordService.get(redRecord);
        return redRecord;
    }

    // 发红包
    @RequestMapping(value = "/divide", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    public ResponseEntity<List<Integer>> divide() {
        Integer amount = 100;
        Integer totalPeople = 5;

        // 1.获取红包个数及各个红包金额
        List<Integer> list = RedPacketUtil.divideRedPacket(amount, totalPeople);

        // 2.发送红包明细插入数据库
        RedRecord redRecord = new RedRecord();
        redRecord.setUserId(1);
        redRecord.setTotal(totalPeople);
        redRecord.setAmount(amount);
        redRecord.setIsActive(true);
        redRecord.setCreateTime(new Date());
        redRecordService.add(redRecord);

        for (int i=0;i<list.size();i++){
            RedDetail redDetail = new RedDetail();
            redDetail.setRecordId(redRecord.getId());
            redDetail.setAmount(list.get(i));
            redDetail.setIsActive(true);
            redDetail.setCreateTime(new Date());
            redDetailService.add(redDetail);
        }

        // 3.将红包个数及红包明细放入缓存
        redisTemplate.opsForValue().set("redPacket:total", totalPeople.toString());
        List<String> data = new ArrayList<>();
        list.forEach(item ->{
            data.add(item.toString());
        });
        redisTemplate.opsForList().leftPushAll("redPacket:list", data);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // 抢红包
    @RequestMapping(value = "/rob/{userId}/{redPacketId}", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    public String rob(@PathVariable Integer userId,@PathVariable Integer redPacketId){
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();

        String lockKey = userId + "-lock";
        // redis分布式锁
        // redis集群部署的话假设有多个同账户请求同时进来，防止一个账户抢多次
        // 那么setIfAbsent的意思是如果没有key的话才能设置成功，redis是单线程的，相当于此处加锁，而且所有对于redis的操作都要排队
        Boolean locked = valueOperations.setIfAbsent(lockKey, "1");
        if(locked){
            // 判断该用户是否抢过红包
            if (StringUtils.isEmpty(valueOperations.get("redPacket:" + userId.toString()))) {
                if (clickRedPacket()) {
                    String item = redisTemplate.opsForList().rightPop("redPacket:list");
                    if (!StringUtils.isEmpty(item)) {
                        valueOperations.increment("redPacket:total", -1);
                        valueOperations.set("redPacket:" + userId.toString(), "hasRobbed");
                        logger.info("---当前用户抢到了红包：redValue={}", item);

                        RedRobRecord redRobRecord = new RedRobRecord();
                        redRobRecord.setAmount(Integer.parseInt(item));
                        redRobRecord.setRecordId(redPacketId);
                        redRobRecord.setUserId(userId);
                        redRobRecord.setRobTime(new Date());
                        redRobRecordService.add(redRobRecord);
                    }
                    return item;
                }
            }
            else {
                return "同一个红包一人只能抢一次，之前抢的金额为...";
            }
            return "红包枪完了";
        }
        return "同用户的并发请求";
    }

    private boolean clickRedPacket(){
        String total = redisTemplate.opsForValue().get("redPacket:total");
        if(!StringUtils.isEmpty(total) && Integer.parseInt(total)> 0){
            return true;
        }
        return false;
    }
}
