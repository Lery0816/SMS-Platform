package com.lbyqsl.strategy.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Set;

@FeignClient(value = "SMS-cache")
public interface CacheClient {
    @GetMapping("/cache/hget/{key}/{field}")
    String hGet(@PathVariable(value = "key") String key, @PathVariable("field") String field);

    @GetMapping("/cache/hget/{key}/{field}")
    Integer hgetInteger(@PathVariable(value = "key")String key, @PathVariable(value = "field")String field);

    @GetMapping("/cache/get/{key}")
    String get(@PathVariable(value = "key") String key);

    @PostMapping(value = "/cache/sinterstr/{key}/{sinterKey}")
    Set<Object> sinterStr(@PathVariable(value = "key")String key, @PathVariable String sinterKey, @RequestBody List<String> value);

    @GetMapping("/cache/smember/{key}")
    Set sMember(@PathVariable(value = "key") String key);
}
