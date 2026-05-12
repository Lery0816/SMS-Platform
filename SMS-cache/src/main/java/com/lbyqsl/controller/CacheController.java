package com.lbyqsl.controller;

import com.msb.framework.redis.RedisClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@RestController
@Slf4j
public class CacheController {
    @Autowired
    RedisClient redisClient;

    @PostMapping(value = "/cache/hmset/{key}")
    public void hmset(@PathVariable(value = "key") String key, @RequestBody Map<String, Object> map) {
        log.info("【缓存模块】 hmset方法，存储key = {}，存储value = {}", key, map);
        redisClient.hSet(key, map);
    }

    @PostMapping(value = "/cache/set/{key}")
    public void set(@PathVariable(value = "key") String key, @RequestParam(value = "value") Object value) {
        log.info("【缓存模块】 hmset方法，存储key = {}，存储value = {}", key, value);
        redisClient.set(key, value);

    }

    @PostMapping(value = "/cache/sadd/{key}")
    public void sadd(@PathVariable(value = "key") String key, @RequestBody Map<String, Object>... value) {
        log.info("【缓存模块】 sadd方法，存储key = {}，存储value = {}", key, value);
        redisClient.sAdd(key, value);
    }

    @GetMapping("/cache/hgetall/{key}")
    public Map hGetAll(@PathVariable(value = "key") String key) {
        log.info("【缓存模块】 hGetAll方法，获取key = {}", key);
        Map<String, Object> map = redisClient.hGetAll(key);
        log.info("【缓存模块】 hGetAll方法，获取key = {}，的数据value = {}", key, map);
        return map;
    }

    @GetMapping("/cache/hget/{key}/{field}")
    public Object hGet(@PathVariable(value = "key") String key, @PathVariable("field") String field) {
        log.info("【缓存模块】 hGet方法，获取key = {},获取field = {}", key, field);
        Object o = redisClient.hGet(key, field);
        log.info("【缓存模块】 hGet方法，获取key = {}、field = {}，的数据value = {}", key, field, o);
        return o;
    }

    @GetMapping("/cache/smember/{key}")
    public Set<Object> sMember(@PathVariable(value = "key") String key) {
        log.info("【缓存模块】 smember方法，获取key = {}", key);
        Set<Object> objects = redisClient.sMembers(key);
        log.info("【缓存模块】 smember方法，获取key = {}的数据value = {}", key, objects);
        return objects;
    }
}