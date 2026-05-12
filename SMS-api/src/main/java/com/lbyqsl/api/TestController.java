package com.lbyqsl.api;

import com.lbyqsl.api.client.CacheClient;
import com.lbyqsl.api.filter.CheckFilterContext;
import com.lbyqsl.common.model.StandardSubmit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@Controller
public class TestController {

    @Autowired
    private CheckFilterContext checkFilterContext;

    @Autowired
    private CacheClient cacheClient;

    @GetMapping("/api/test")
    public void test(){
        System.out.println("====================================");
        checkFilterContext.check(new StandardSubmit());
    }
    @GetMapping("/api/test/{apikey}")
    public void test1(@PathVariable(value = "apikey")String apikey){
        System.out.println("====================================");

        Map map = cacheClient.hGetAll(apikey);
        System.out.println(map.toString());
    }
}
