package com.lbyqsl.gateway.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author lbyqsl
 * @description
 */
@FeignClient(value = "SMS-cache")
public interface CacheClient {

    @GetMapping("/cache/hget/{key}/{field}")
    Integer hgetInteger(@PathVariable(value = "key") String key, @PathVariable(value = "field") String field);

    @GetMapping("/cache/hget/{key}/{field}")
    String hget(@PathVariable(value = "key")String key, @PathVariable(value = "field")String field);

}
