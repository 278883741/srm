package com.imooc.controller;

import com.google.common.collect.Lists;
import com.imooc.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;

// https://repo1.maven.org/maven2/
@RestController
@RequestMapping(value = "string")
public class RedisController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @RequestMapping(value = "add",method = RequestMethod.POST)
    public Object add(@RequestBody @Validated Card card, BindingResult bindingResult){
        if(!bindingResult.hasErrors()){
            boolean result = stringRedisTemplate.hasKey("");
        }
        stringRedisTemplate.opsForValue().set("name","xiaoming");
        String name = stringRedisTemplate.opsForValue().get("name");
        return name;
    }

    // list -- 有序的
    public void testList(){
        ListOperations<String, String> listOperations = redisTemplate.opsForList();

        final String key = "list";
        List<String> list = Lists.newArrayList("c","d","e");
        listOperations.leftPush(key,"a");
        listOperations.leftPush(key,"b");
        listOperations.leftPushAll(key,list);
        // 因为是lpush，现在的数据是e d c b a
        long length = listOperations.size(key);
        // 取数据
        List<String> data = listOperations.range(key,0,100);
        // 下标为4的数据
        String dataItem = listOperations.index(key,4);
        // 对下标为4的数据进行更新
        listOperations.set(key,4,"e");
        String dataItem1 = listOperations.rightPop(key);
    }

    @PostConstruct
    // set -- 无序的，唯一的
    public void testSet(){
        SetOperations<String, String> setOperations = redisTemplate.opsForSet();

        final String key1 = "SpringBootRedis:Set:10010";
        final String key2 = "SpringBootRedis:Set:10011";

        setOperations.add(key1,"a","b","c");
        setOperations.add(key2,"c","d","e");

        // 所有元素
        setOperations.members(key1);

        setOperations.randomMember(key1);
        // 随机取出两个元素，但是这两个元素可能是重复的，下面的是不重复的
        setOperations.randomMembers(key1,2);
        setOperations.distinctRandomMembers(key1,2);

        setOperations.size(key1);
        setOperations.isMember(key1,"a");

        // 差集
        setOperations.difference(key1, key2);
        // 交集
        setOperations.intersect(key1, key2);
        // 并集
        setOperations.union(key1, key2);

        // 随机取出一个元素
        setOperations.pop(key1);
        setOperations.remove(key1, "c");
    }
}
