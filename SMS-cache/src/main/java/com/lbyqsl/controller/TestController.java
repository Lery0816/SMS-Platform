//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.lbyqsl.controller;

import java.util.Map;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.msb.framework.redis.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class TestController {
//    @Autowired
//    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private RedisClient redisClient;

//    用了飞马框架
    @PostMapping({"/test/set/{key}"})
    public String set(@PathVariable String key, @RequestBody Map map) {
        redisClient.hSet(key,map);
        return "OK";
    }

    @GetMapping({"test/get/{key}"})
    public Map get(@PathVariable String key) {
        Map<String, Object> map = redisClient.hGetAll(key);
        return map;
    }


//    使用redisTemplate


//    @PostMapping({"/test/set/{key}"})
//    public String set(@PathVariable String key, @RequestBody Map map) {
//        this.redisTemplate.opsForHash().putAll(key, map);
//        return "OK";
//    }
//
//    @GetMapping({"test/get/{key}"})
//    public Map get(@PathVariable String key) {
//        Map<Object, Object> map = this.redisTemplate.opsForHash().entries(key);
//        return map;
//    }


}
