package com.lbyqsl.controller;

import com.msb.framework.redis.RedisClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@Slf4j
public class CacheController {
    @Autowired
    private RedisClient redisClient;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

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

    @PostMapping(value = "/cache/saddstr/{key}")
    public void saddstr(@PathVariable(value = "key") String key, @RequestBody List<String> value) {
        log.info("【缓存模块】 saddstr方法，存储key = {}，存储value = {}", key, value);
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
    public Set sMember(@PathVariable(value = "key") String key) {
        log.info("【缓存模块】 smember方法，获取key = {}", key);
        Set<Object> objects = redisClient.sMembers(key);
        log.info("【缓存模块】 smember方法，获取key = {}的数据value = {}", key, objects);
        return objects;
    }

    @PostMapping("/cache/pipeline/string")
    public void pipelineString(@RequestBody Map<String,String> map){
        log.info("【缓存模块】 pipelineString方法，获取的数据的长度= {}", map.size());
        log.info("【缓存模块】 pipelineString方法，添加数据到redis中");
        redisClient.pipelined(operations->{
            for (Map.Entry<String, String> entry : map.entrySet()) {
                operations.opsForValue().set(entry.getKey(),entry.getValue());
            }
        });
    }

    @GetMapping("/cache/get/{key}")
    public Object get(@PathVariable(value = "key") String key) {
        log.info("【缓存模块】 get方法，获取key = {}", key);
        Object o = redisClient.get(key);
        log.info("【缓存模块】 get方法，获取key = {},的数据value = {}", key, o);
        return o;
    }

    @PostMapping(value = "/cache/sinterstr/{key}/{sinterKey}")
    public Set<Object> sinterStr(@PathVariable(value = "key")String key, @PathVariable String sinterKey,@RequestBody List<String> value){
        log.info("【缓存模块】 sinterStr的交集方法，存储key = {}，sinterKey = {}，存储value = {}", key, sinterKey,value);
        //1、 存储数据到set集合
        redisClient.sAdd(key,value);
        //2、 需要将key和sinterKey做交集操作，并拿到返回的set
        Set<Object> result = redisTemplate.opsForSet().intersect(key, sinterKey);
        //3、 将key删除
        redisClient.delete(key);
        //4、 返回交集结果
        return result;
    }

    @PostMapping(value = "/cache/zadd/{key}/{score}/{member}")
    public Boolean zadd(@PathVariable(value = "key")String key,
                        @PathVariable(value = "score")Long score,
                        @PathVariable(value = "member")Object member){
        log.info("【缓存模块】 zaddLong方法，存储key = {}，存储score = {}，存储value = {}", key,score, member);
        Boolean result = redisClient.zAdd(key, member, score);
        return result;
    }

    @GetMapping(value = "/cache/zrangebyscorecount/{key}/{start}/{end}")
    public int zRangeByScoreCount(@PathVariable(value = "key") String key,
                                  @PathVariable(value = "start") Double start,
                                  @PathVariable(value = "end") Double end) {
        log.info("【缓存模块】 zRangeByScoreCount方法，查询key = {},start = {},end = {}", key,start,end);
        Set<ZSetOperations.TypedTuple<Object>> values = redisTemplate.opsForZSet().rangeByScoreWithScores(key, start, end);
        if(values != null){
            return values.size();
        }
        return 0;
    }

    @DeleteMapping(value = "/cache/zremove/{key}/{member}")
    public void zRemove(@PathVariable(value = "key") String key,@PathVariable(value = "member") String member) {
        log.info("【缓存模块】 zRemove方法，删除key = {},member = {}", key,member);
        redisClient.zRemove(key,member);
    }
}