package com.imooc.controller;

import com.google.common.collect.Lists;
import com.imooc.model.Card;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Set;

// https://repo1.maven.org/maven2/
@RestController
@RequestMapping(value = "string")
public class RedisController {
    private static final Logger log = LoggerFactory.getLogger(RedisController.class);

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

    // @PostConstruct -- 该注解被用来修饰一个非静态的void（）方法。被@PostConstruct修饰的方法会在服务器加载Servlet的时候运行，并且只会被服务器执行一次
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
        // 指定删除元素
        setOperations.remove(key1, "c");
    }

    // zSet是set和list的结合，元素唯一，每个元素有一个分数，默认是按分数的从小到大排列的
    public void testZset(){
        ZSetOperations<String, String> zSetOperations = redisTemplate.opsForZSet();
        final String key = "SpringBootRedis:zSet";
        zSetOperations.add(key,"a",1.0);
        zSetOperations.add(key,"c",2.0);
        zSetOperations.add(key,"h",4.5);
        zSetOperations.add(key,"l",1.0);

        Long count = zSetOperations.size(key);
        // 按正序取数据 - index的start，个数
        Set<String> set = zSetOperations.range(key,0,10);
        set = zSetOperations.reverseRange(key,0,10);

        // 取元素分数
        Double score = zSetOperations.score(key,"a");
        // 给元素增加分数
        zSetOperations.incrementScore(key,"c",1.5);
        // 按分数区间取数据
        zSetOperations.rangeByScore(key,1.5,3.0);
        // 取出带分数的数据
        Set<ZSetOperations.TypedTuple<String>> typedTuples = zSetOperations.rangeByScoreWithScores(key, 0, 10);
        typedTuples.forEach(item -> log.info(item.getValue() + "" + item.getScore()));
    }
}
