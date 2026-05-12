package com.lbyqsl.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;
import java.util.Set;

@FeignClient(value = "SMS-cache")
public interface CacheClient {
    @GetMapping("/cache/hgetall/{key}")
    Map hGetAll(@PathVariable(value = "key")String key);

    @GetMapping("/cache/hget/{key}/{field}")
    Object hGet(@PathVariable(value = "key")String key,@PathVariable("field")String field);

    @GetMapping("/cache/hget/{key}/{field}")
    String hGetString(@PathVariable(value = "key")String key,@PathVariable("field")String field);

    @GetMapping("/cache/smember/{key}")
    Set<Map> sMember(@PathVariable(value = "key") String key);
}
